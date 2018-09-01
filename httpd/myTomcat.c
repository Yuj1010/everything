#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <fcntl.h>
#include <pthread.h>
#include <sys/sendfile.h>
#include <signal.h>

#define MAX 1024

int getLine(int sock, char line[], int num)
{
	char c = 'x';
	int i = 0;
	while(c != '\n' && i < num - 1){
		ssize_t s = recv(sock, &c, 1, 0);
		if(s > 0){
			//\r\n, \n, \r -> \n
			if(c == '\r'){ // \r\n, \r
				recv(sock, &c, 1, MSG_PEEK);
				if(c == '\n'){
					recv(sock, &c, 1, 0);
				}
				else{
					c = '\n';
				}
			} //\n

			line[i++] = c;
		}
		else{
			break;
		}
	}
	line[i] = 0;
	return i;
}

void clearHeader(int sock)
{
	char line[MAX];
	do{
		getLine(sock, line, MAX);
	}while(strcmp(line, "\n") != 0);
}

void show_404(int sock)
{
	char line[MAX];
	sprintf(line, "http/1.0 200 OK\r\n");
	send(sock, line, strlen(line), 0);
	sprintf(line, "Content-Type: text/html\r\n");
	send(sock, line, strlen(line), 0);
	sprintf(line, "\r\n");
	send(sock, line, strlen(line), 0);

	int fd = open("web/404.html", O_RDONLY);
	struct stat st;
	stat("web/404.html", &st);
	sendfile(sock, fd, NULL, st.st_size);

	close(fd);
}
void echoError(int sock, int statusCode)
{
	switch(statusCode){
		case 404:
			show_404(sock);
			break;
		default:
			break;
	}
}

int echoHtml(int sock, char path[], int size)
{
	char line[MAX];
	clearHeader(sock);
	int fd = open(path, O_RDONLY);
	if(fd < 0){
		return 500;
	}

	char *stuff = path + strlen(path) - 1;
	while(*stuff != '.'){
		stuff--;
	}
	sprintf(line, "http/1.0 200 OK\r\n");
	send(sock, line, strlen(line), 0);
	if(0 == strcmp(stuff, ".html")){
		sprintf(line, "Content-Type: text/html\r\n");
	}
	else if(0 == strcmp(stuff, ".css")){
		sprintf(line, "Content-Type: text/css\r\n");
	}else if(0 == strcmp(stuff, ".js")){
		sprintf(line, "Content-Type: application/x-javascript\r\n");
	}else{
		sprintf(line, "Content-Type: text/html\r\n");
	}
	send(sock, line, strlen(line), 0);

	sprintf(line, "Content-Length: %d\r\n", size);
	send(sock, line, strlen(line), 0);
	sprintf(line, "\r\n");
	send(sock, line, strlen(line), 0);

	sendfile(sock, fd, NULL, size);

	close(fd);
}


int exeCgi(int sock, char *method, char *path, char *query_string)
{
	printf("CGI: \n");
	char line[MAX];
	char arg[MAX];
	int content_length = -1;
	if(strcmp(method, "GET") == 0){
		clearHeader(sock);
		strcpy(arg, query_string);
	}else{
		do{
			getLine(sock, line, MAX);
			if(strncmp(line, "Content-Length: ", 16) == 0){
				content_length = atoi(line + 16);
			}
		}while(strcmp(line, "\n") != 0);
		if(content_length == -1){
			return 400;
		}
		int i = 0;
		char c;
		for(; i < content_length; i++){
			recv(sock, &c, 1, 0);
			arg[i] = c;
		}
		arg[i] = '\0';
	}
	///////////////////////////////////////
	
	sprintf(line, "http/1.0 200 OK\r\n");
	send(sock, line, strlen(line), 0);
	sprintf(line, "Content-Type: text/html\r\n");
	send(sock, line, strlen(line), 0);
	sprintf(line, "\r\n");
	send(sock, line, strlen(line), 0);

	//x=100&b=200
	int x, y;
	sscanf(arg, "x=%d&y=%d", &x, &y);
	sprintf(line, "<html><h1>%d+%d=%d</h1></html>\r\n", x, y, x + y);
	send(sock, line, strlen(line), 0);
	return 200;
}

void *handlerRequest(void *arg)
{
	int sock = (int)arg;
	char line[MAX];
	char method[MAX/16];
	char url[MAX];
	char path[MAX];
	int i = 0;
	int j = 0;
	int statusCode = 200;
	int cgi = 0;
	char *query_string = NULL;

	getLine(sock, line, MAX);

	while(i < sizeof(method)-1 && j < sizeof(line) && !isspace(line[j])){
		method[i] = line[j];
		i++, j++;
	}
	method[i] = '\0';

	if(strcmp(method, "GET") == 0){
	}
	else if(strcmp(method, "POST") == 0){
		cgi = 1;
	}
	else{
		clearHeader(sock);
		statusCode = 400;
		goto end;
	}

	while(j < sizeof(line) && isspace(line[j])){
		j++;
	}

	i = 0;
	while(i < sizeof(url) - 1 && j < sizeof(line) && !isspace(line[j])){
		url[i] = line[j];
		i++, j++;
	}
	url[i] = '\0';


	if(strcasecmp(method, "GET") == 0){
		query_string = url;
		while(*query_string != '\0'){
			if(*query_string == '?'){
				cgi = 1;
				*query_string = '\0'; // /a/b/c.html\0a=100&b=200
				query_string++;
				break;
			}
			query_string++;
		}
	}
	//method, url, query_string
	sprintf(path, "web%s", url); // /
	if(path[strlen(path)-1] == '/'){
		strcat(path, "index.html");
	}

	printf("method: %s, url: %s, query_string: %s\n",\
			method, url, query_string);

	struct stat st;
	if(stat(path, &st) < 0){
		clearHeader(sock);
		statusCode = 404;
		goto end;
	}
	else{
		if(S_ISDIR(st.st_mode)){
			strcat(path, "/index.html");
		}

		if(cgi){
			statusCode = exeCgi(sock, method, path, query_string);
		}
		else{
			statusCode = echoHtml(sock, path, st.st_size);
		}
	}

end:
	if(statusCode != 200){
		echoError(sock, statusCode);
	}

	close(sock);
}

int startup(char *ip, int port)
{
	int sock = socket(AF_INET, SOCK_STREAM, 0);
	if(sock < 0){
		perror("socket");
		exit(2);
	}

	int opt = 1;
	setsockopt(sock, SOL_SOCKET, SO_REUSEADDR, &opt, sizeof(opt));

	struct sockaddr_in local;
	local.sin_family = AF_INET;
	local.sin_addr.s_addr = inet_addr(ip);
	local.sin_port = htons(port);

	if(bind(sock, (struct sockaddr*)&local, sizeof(local)) < 0){
		perror("bind");
		exit(3);
	}

	if(listen(sock, 5) < 0){
		perror("listen");
		exit(4);
	}

	return sock;
}

void usage(const char *proc)
{
	printf("Usage: %s [ip] [port]\n", proc);
}

// ./myTomcat ip 8080
int main(int argc, char *argv[])
{
	if(argc != 3){
		usage(argv[0]);
		return 1;
	}
	signal(SIGPIPE, SIG_IGN);
	int listen_sock = startup(argv[1], atoi(argv[2])); //ip, port

	for(;;){
		struct sockaddr_in peer;
		socklen_t len = sizeof(peer);
		int sock = accept(listen_sock, (struct sockaddr*)&peer, &len);
		if(sock < 0){
			continue;
		}
		printf("get a new link... create thread handler!\n");
		pthread_t tid;
		pthread_create(&tid, NULL, handlerRequest, (void *)sock);
		pthread_detach(tid);
	}
}












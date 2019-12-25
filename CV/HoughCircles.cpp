#include "opencv2/highgui/highgui.hpp"
#include "opencv2/imgproc/imgproc.hpp"
#include <iostream>
#include <stdio.h>
#include<vector>
#include <fstream>
#include<Windows.h>

using namespace cv;
using namespace std;

//霍夫圆变化
int main()
{
//批量处理
	string ImgName;
	ifstream finPos("D:\\temp\\img.txt");
	vector<Vec3f> circles;
	char name[20];
	for (int i = 0; i<101 && getline(finPos, ImgName); i++){
		ImgName = "D:\\temp\\" + ImgName;
		Mat img = imread(ImgName);
		Mat gray;
		cvtColor(img, gray, COLOR_BGR2GRAY);
		GaussianBlur(gray, gray, Size(9, 9), 2, 2);
		HoughCircles(gray, circles, HOUGH_GRADIENT, 2, gray.rows / 1, 100, 100, 265);
		for (size_t i = 0; i < circles.size(); i++){
			Point center(cvRound(circles[i][0]), cvRound(circles[i][1]));
			int radius = cvRound(circles[i][2]);
			// draw the circle center
			circle(img, center, 3, Scalar(0, 255, 0), -1, 8, 0);
			//draw the circle outline
			circle(img, center, radius, Scalar(0, 0, 255), 1, 8, 0);
		}
		sprintf_s(name, "%s%d%s", "D:\\out\\", i + 900, ".jpg");
		string str(name);
		imwrite(str, img);
		for (const Vec3f& k : circles)
			cout << k [1]<< "\n";
		
	}
	cout << endl;


    //单个处理#############################################################
	//Mat img, gray;
	//img = imread("D:\\mask\\900.png");
	//cvtColor(img, gray, COLOR_BGR2GRAY);
	//// smooth it, otherwise a lot of false circles may be detected
	////GaussianBlur(gray, gray, Size(9, 9), 2, 2);
	//vector<Vec3f> circles;
	//HoughCircles(gray, circles, HOUGH_GRADIENT,
	//	2, gray.rows /1, 100, 70);
	//for (size_t i = 0; i < circles.size(); i++)
	//{
	//	Point center(cvRound(circles[i][0]), cvRound(circles[i][1]));
	//	int radius = cvRound(circles[i][2]);
	//	// draw the circle center
	//	circle(img, center, 3, Scalar(0, 255, 0), -1, 8, 0);
	//	 //draw the circle outline
	//	circle(img, center, radius, Scalar(0, 0, 255), 1, 8, 0);
	//}
	//namedWindow("circles", 1);
	//imshow("circles", img);
	//waitKey(0);
	////imwrite("out_1.jpg", img);
	//for (const  Vec3f& k : circles)
	//	cout << k << " ";
	//cout << endl;




	system("pause");
	return 0;
}

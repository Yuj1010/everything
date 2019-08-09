#include <iostream>  
#include <opencv2/core/core.hpp>  
#include <opencv2/highgui/highgui.hpp>  
#include <opencv2/imgproc/imgproc.hpp>

using namespace cv;
using namespace std;

Mat setPx(int x, int y){
	Mat img(Size(x, y), CV_8UC1, Scalar(255));

	int n = 2;
	int sum = n * 10;
	for (int j = 0; j < img.cols; j += n){
		for (int i = 0; i < img.rows; i++){
			for (int t = j; t < (j + (n / 2)) && t<img.cols; t++){
				img.at<uchar>(i, t) = 0;
			}

		}

		if (j == sum){
			n += 2;
			if ((sum + n)>img.cols){

			}
			sum = sum + (n * 10);

		}
	}
	return img;
}
Mat setCircle(int x, int y){
	Mat img(Size(x, y), CV_8UC1, Scalar(255));
	int r = 5;
	int count = 0;
	for (int i = 5; i < img.cols; i += (2 * r)){
		for (int j = r; j < img.rows; j += (2 * r)){
			circle(img, cv::Point(i, j), r, cv::Scalar(0, 255, 0), -1);
		}
		count++;
		if (count == 10){
			count = 0;
			r += 5;
		}
	}
	return img;
}





int main(){
	//Mat img = setPx(2560, 1440);
	Mat img = setCircle(1000, 1000);

	imshow("Ô²µã", img);
	imwrite("Ô²µã.png", img);
	waitKey(0);

	
}



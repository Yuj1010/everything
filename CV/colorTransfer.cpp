#include "opencv2/highgui/highgui.hpp"
#include "opencv2/imgproc/imgproc.hpp"
#include <opencv2/core/core.hpp> 
#include<vector>
#include <math.h>

using namespace std;
using namespace cv;

//RGB转Lab
Mat RGBToLab(Mat img){

	Mat lab = img;

	cvtColor(img, lab, CV_BGR2Lab);

	return lab;

}

//Lab转RGB
Mat LabToRGB(Mat img){


	Mat rgb = img;
	cvtColor(img, rgb, CV_Lab2BGR);

	return rgb;

}


//计算均值
vector<double> computeMeans(Mat img){
	Mat_<Vec3f> temp = img;
	vector<double> means;
	means.resize(3);
	double sum[3] = { 0 };
	int count = (temp.rows)*(temp.cols);

	for (int i = 0; i < temp.rows; i++){
		for (int j = 0; j < temp.cols; j++){
			for (int k = 0; k < 3; k++){
				sum[k] += temp(i, j)[k];
			}
		}
	}

	for (int i = 0; i < 3; i++){
		means[i] = sum[i] / count;
	}

	return means;
}


//计算方差
vector<double> computeVariances(Mat img, vector<double> means){
	Mat_<Vec3f> temp = img;
	vector<double> variances;
	variances.resize(3);
	double sum[3] = { 0 };
	int count = (temp.rows)*(temp.cols);

	for (int i = 0; i < temp.rows; i++){
		for (int j = 0; j < temp.cols; j++){
			for (int k = 0; k < 3; k++){
				//sum[k] += abs(temp(i, j)[k] - means[k]);
				sum[k] += pow(temp(i, j)[k] - means[k], 2);
			}
		}
	}

	for (int i = 0; i < 3; i++){
		variances[i] = sqrt(sum[i] / count);
		
	}

	return variances;


}


Mat colorTransfer(Mat src, Mat target){

	Mat resultImg;

	Mat srcImg_32F;
	Mat targetImg_32F;

	Mat srcImg_Lab;
	Mat targetImg_Lab;

	vector<double> srcMeans;
	vector<double> targetMeans;
	vector<double> srcVariances;
	vector<double> targetVariances;

	src.convertTo(srcImg_32F, CV_32FC3, 1.0f / 255.0f);  //将图像转为32位
	target.convertTo(targetImg_32F, CV_32FC3, 1.0f / 255.0f);
	resultImg = srcImg_32F;

	srcImg_Lab = RGBToLab(srcImg_32F);
	targetImg_Lab = RGBToLab(targetImg_32F);
	srcMeans = computeMeans(srcImg_Lab);
	targetMeans = computeMeans(targetImg_Lab);
	srcVariances = computeVariances(srcImg_Lab, srcMeans);
	targetVariances = computeVariances(targetImg_Lab, targetMeans);

	Mat_<Vec3f> temp = resultImg;
	double tempData[3] = { 0 };

	for (int k = 0; k < 3; k++){
		tempData[k] = targetVariances[k] / srcVariances[k];
	}

	for (int i = 0; i < temp.rows; ++i){
		for (int j = 0; j < temp.cols; ++j){
			for (int k = 0; k < 3; k++){
				temp(i, j)[k] = tempData[k] * (temp(i, j)[k] - srcMeans[k]) + targetMeans[k];
			}
		}
	}

	resultImg = LabToRGB(temp);

	return resultImg;

}





int main(){
	Mat out, result;

	Mat src = imread("dilate_5_1.png");
	Mat target = imread("10603.png");

	out = colorTransfer(src, target);
	out.convertTo(result, CV_8U, 255.0, 1.0 / 255.0);
	imwrite("0000.png", result);

	return 0;
}

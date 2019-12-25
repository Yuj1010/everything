//最小外围轮廓///////////////////////////////////////////////////////////////

Mat src; Mat src_gray;
int thresh = 100;
int max_thresh = 255;
RNG rng(12345);

int num = 900;

///// 函数声明
void thresh_callback();
//
///** @主函数 */
int main(int argc, char** argv)
{
	string ImgName;
	ifstream finPos("D:\\mask_1\\img.txt");
	for (int i = 0; i<101 && getline(finPos, ImgName); i++){
		ImgName = "D:\\ROI\\" + ImgName;
		src = imread(ImgName);
		/// 转化成灰度图像并进行平滑
		cvtColor(src, src_gray, CV_BGR2GRAY);
		blur(src_gray, src_gray, Size(3, 3));
		thresh_callback();
		num++;
		
	}
	cout << endl;
	system("pause");
	return 0;
}

/** @thresh_callback 函数 */
void thresh_callback()
{
	
	char name[50];
	Mat threshold_output;
	vector<vector<Point> > contours;
	vector<Vec4i> hierarchy;

	/// 使用Threshold检测边缘
	threshold(src_gray, threshold_output, thresh, 255, THRESH_BINARY);
	/// 找到轮廓
	findContours(threshold_output, contours, hierarchy, CV_RETR_TREE, CV_CHAIN_APPROX_SIMPLE, Point(0, 0));

	/// 多边形逼近轮廓 + 获取矩形和圆形边界框
	vector<vector<Point> > contours_poly(contours.size());
	vector<Rect> boundRect(contours.size());
	vector<Point2f>center(contours.size());
	vector<float>radius(contours.size());

	for (int i = 0; i < contours.size(); i++)
	{
		approxPolyDP(Mat(contours[i]), contours_poly[i], 3, true);
		boundRect[i] = boundingRect(Mat(contours_poly[i]));
		minEnclosingCircle(contours_poly[i], center[i], radius[i]);
	}


	/// 画多边形轮廓 + 包围的矩形框 + 圆形框
	Mat drawing = Mat::zeros(threshold_output.size(), CV_8UC3);
	for (int i = 0; i< contours.size(); i++)
	{
		//Scalar color = Scalar(rng.uniform(0, 255), rng.uniform(0, 255), rng.uniform(0, 255));
		//drawContours(src, contours_poly, i, Scalar(0, 0, 255), 1, 8, vector<Vec4i>(), 0, Point());
		//rectangle(src, boundRect[i].tl(), boundRect[i].br(), Scalar(0, 255, 0), 1, 8, 0);
		circle(src, center[i], (int)radius[i], Scalar(0, 0, 255), 1, 8, 0);
	}

	
	//imshow("Contours", src);
	//waitKey(100);
}

int main(int argc, char** argv)
{
	Mat src, fgd, bgd, mask, result, binMask;
	

	src = imread("D:\\ROI\\900.png");
	mask.create(src.size(), CV_8UC1);
	mask.setTo(Scalar(GC_BGD));
	

	Rect rect(160, 170, 550, 550);
	mask(rect).setTo(Scalar(GC_PR_FGD));
	grabCut(src, mask, rect, bgd, fgd, 1, GC_INIT_WITH_RECT);
	binMask.create(mask.size(), CV_8UC1);
	binMask = mask & 1;
	
	src.copyTo(result, binMask);
	threshold(result, result, 0, 255, THRESH_BINARY);
	imshow("mask", result);
	waitKey(0);
	return 0;
}

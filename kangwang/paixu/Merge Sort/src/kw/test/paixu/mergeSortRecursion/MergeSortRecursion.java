package kw.test.paixu.mergeSortRecursion;

import kw.test.paixu.merge.Merge;

/**
 * auther   kangwang
 */
public class MergeSortRecursion {
    public static void mergeSortRecursion(int a[],int left ,int right){
 
        if(left == right){
            return;
        }
        int mid = (left+right)/2;
        mergeSortRecursion(a,left,mid);
        mergeSortRecursion(a,mid+1,right);
        Merge.merge(a,left,mid,right-1);

    }

}

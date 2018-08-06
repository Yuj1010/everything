package kw.test.paixu.merge;

/**
 * auther   kangwang
 *
 */
public class Merge {
    /**
     * 将数据进行合并
     * @param a
     * @param left
     * @param mid
     * @param right
     */
    public static void merge(int a[],int left,int mid,int right){
        int len = right - left + 1;
        int b [] =new int[len];
        int i = left;
        int index=0;
        int j = mid+1;
        while (i<=mid&&j<right){
            b[index++]=a[i]<=a[j]?a[i++]:a[j++];
        }
        while(i<=mid){
            b[index++]=a[i++];
        }
        while(j<=right){
            b[index++]=a[j++];
        }
        for(i=0;i<right;i++){
            a[i]=b[i];
        }
    }
}

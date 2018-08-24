//简单动态规划

//斐波那契数列
public class Solution {
    public int Fibonacci(int n) {
        int a=1,b=1,c=0;
        if(n<0){
            return 0;
        }else if(n==1||n==2){
            return 1;
        }else{
            for (int i=3;i<=n;i++){
                c=a+b;
                b=a;
                a=c;
            }
            return c;
        }
    }
}


//跳台阶
public class Solution {
    public int JumpFloor(int target) {
        if(target<=0){
            return 0;
        }
        if(target==1){
            return 1;
        }
        if(target==2){
            return 2;
        }
        int total_1=1;
        int total_2=2;
        int total=0;
        for(int i=3;i<=target;i++){
            total=total_1+total_2;
            total_1=total_2;
            total_2=total;
        }
        return total;
    }
}



//变态跳台阶

public class Solution {
    public int JumpFloorII(int target) {
        if(target<=0){
            return 0;
        }
        int total=1;
        for(int i=1;i<target;i++){
            total=2*total;
        }
        return total;
    }
}



//矩形覆盖

public class Solution {
    public int RectCover(int target) {
        if(target<=0){
            return 0;
        }
        if(target==1){
            return 1;
        }
        if(target==2){
            return 2;
        }
        int t1=1,t2=2;
        int result=0;
        for(int i=3;i<=target;i++){
            result=t1+t2;
            t1=t2;
            t2=result;
        }
        return result;
    }
}



//连续子数组的最大和
import java.util.*;

public class Solution {
    public int FindGreatestSumOfSubArray(int[] array) {
        int result=array[0];
        int max=array[0];
        for(int i=1;i<array.length;i++){
            max=Math.max(array[i],max+array[i]);
            result=Math.max(result,max);
        }
        return result;
    }
}
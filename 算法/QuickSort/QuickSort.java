package QuickSort;

import java.util.Stack;

public class QuickSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] arr= {1,5,3,6,4,2,9,7,8,0};
		QuickSort_R(arr, 0, arr.length-1);;
		for(int i=0;i<arr.length;i++) {
			System.out.print(arr[i]+" ");
		}
	}
	//递归
	public static void QuickSort(int [] array,int left,int right) {
    	if(left>=right) {
    		return ;
    	}
    	int i=left;
    	int j=right;
    	int index=array[i];	//以数组第一个元素为基准数
    	while(i<j) {
    		while(i<j&&array[j]>=index) {
        		j--;
        	}
        	if(i<j) {
        		array[i++]=array[j];
        	}
    		while(i<j&&array[i]<=index) {
        		i++;
        	}
    		if(i<j) {
    			array[j--]=array[i];
    		}
    	}
    	array[i]=index;
    	QuickSort(array,left,i-1);
    	QuickSort(array, i+1, right);
    }
    
    //非递归快速排序
    //利用栈模拟
    public static int partion(int [] array,int left,int right) {
    	int i=left;
    	int j=right;
    	int index=array[i];	//以数组第一个元素为基准数
    	while(i<j) {
    		while(i<j&&array[j]>=index) {
        		j--;
        	}
        	if(i<j) {
        		array[i++]=array[j];
        	}
    		while(i<j&&array[i]<=index) {
        		i++;
        	}
    		if(i<j) {
    			array[j--]=array[i];
    		}
    	}
    	array[i]=index;
    	return left;
    }
    public static void QuickSort_R(int [] array,int left,int right) {
    	if(left>=right) {
    		return ;
    	}
    	Stack<Integer> stack=new Stack<>();
    	stack.push(right);
    	stack.push(left);
    	while(!stack.isEmpty()) {
    		int i=stack.pop();
    		int j=stack.pop();
    		int index=partion(array,i,j);
    		if(i<index-1) {
    			stack.push(index-1);
    			stack.push(i);
    		}
    		if(j>index+1) {
    			stack.push(j);
    			stack.push(index+1);
    		}
    	}
    }
}

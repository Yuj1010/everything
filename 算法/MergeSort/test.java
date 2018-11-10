package MergeSort;

//πÈ≤¢≈≈–Ú
//µ›πÈ
class test{
	public static void main(String[] args) {
		int [] arr= {1,5,3,6,4,2,9,7,8,0};
		MergeSort(arr, 0, arr.length-1);;
		for(int i=0;i<arr.length;i++) {
			System.out.print(arr[i]+" ");
		}
	}
	
	public static void MergeSort(int [] array,int left,int right) {
		if(left<right) {
			int mid=(right+left)/2;
			MergeSort(array,left, mid);
			MergeSort(array, mid+1, right);
			merge(array,left,mid,right);
		}
	}
	public static int [] merge(int [] array,int left,int mid,int right) {
		int l_size=mid-left+1;
		int r_size=right-mid;
		int [] Left=new int [l_size+1];
		int [] Right=new int [r_size+1];
		for(int i=0;i<l_size;i++) {
			Left[i]=array[left+i];
		}
		for(int i=0;i<r_size;i++) {
			Right[i]=array[mid+1+i];
		}
		Left[l_size]=Integer.MAX_VALUE;
		Right[r_size]=Integer.MAX_VALUE;
		int i=0,j=0;
		for(int k=left;k<=right;k++) {
			if(Left[i]<=Right[j]) {
				array[k]=Left[i];
				i++;
			}else {
				array[k]=Right[j];
				j++;
			}
		}
		return array;
	}
	
}






package sort;

public class test_sort {

	public static void main(String[] args) {
		int [] arr= {1,5,3,6,4,2,9,7,8,0};
		ShellSort(arr);
		for(int i=0;i<arr.length;i++) {
			System.out.print(arr[i]+" ");
		}
	}
	public static void Swap(int[] data, int i, int j) {
		
		int temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}

	//冒泡排序
	public static void BubbleSort(int [] array) {
		if(array.length<=1) {
			return ;
		}
		for(int i=0;i<array.length-1;i++) {
			for(int j=0;j<array.length-1-i;j++) {
				if(array[j]>array[j+1]) {
					Swap(array,j,j+1);
				}
			}
		}
	}
	//选择排序
	public static void SelectSort(int [] array) {
		if(array.length<=1) {
			return;
		}
		for(int i=0;i<array.length;i++) {
			for(int j=i+1;j<array.length;j++) {
				if(array[j]<array[i]) {
					Swap(array, i, j);
				}
			}
		}
	}
	
	//插入排序
	public static void InsertSort(int [] array) {
		if(array.length<=1) {
			return ;
		}
		for(int i=1;i<array.length;i++) {
			int temp=array[i];
			int j=i;
			for(;j>0;j--) {
				if(array[j-1]>temp) {
					array[j]=array[j-1];
				}else {
					array[j]=temp;
					break;
				}
			}
			array[j]=temp;
		}
	}
	
	//堆排序
	//下沉式调整
//	public static void ADjustDown(int [] array, int size, int index) {
//		int parent=index;
//		int child=2*parent+1;//左子树
//		while(child<size) {
//			if(child+1<size&&array[child+1]>array[child]) {
//				child=child+1;
//			}
//			if(array[child]>array[parent]) {
//				Swap(array, child, parent);
//			}else {
//				break;
//			}
//			parent=child;
//			child=2*parent+1;
//		}
//		return ;
//	}
//	//建大堆
//	public static void HeapCreat(int [] array) {
//		if(array.length<=1) {
//			return ;
//		}
//		//下沉式,从后往前遍历
//		int i=(array.length-1-1)/2;//最后一个非叶子节点
//		for(;i>=0;i--) {
//			ADjustDown(array,array.length,i);
//		}
//		
//	}
//	public static void HeapPop(int [] array) {
//		if(array.length<=1) {
//			return ;
//		}
//		Swap(array, 0, array.length-1);
//		ADjustDown(array,array.length-1, 0);
//	}
//	public static void HeapSort(int [] array) {
//		if(array.length<=1) {
//			return ;
//		}
//		HeapCreat(array);
//		for(int i=0;i<array.length;i++) {
//			HeapPop(array);
//		}
//	}
	public static void AdjustDown(int[] array, int size, int index) {
        int parent = index;
        int child = 2 * parent + 1; // 左子树
        while (child < size) {
            // 先拿左右子树进行比较, 看谁比较大, 然后再拿大的和
            // 父节点进行比较
            if (child + 1 < size && array[child + 1] > array[child]) {
                // 右子树比左子树大, 就让 child 指向右子树
                child = child + 1;
            }
            if (array[child] > array[parent]) {
                Swap(array, child, parent);
            } else {
                // 此时调整就调整完了~~
                break;
            }
            parent = child;
            child = 2 * parent + 1;
        } // end while
        return;
    }

    public static void HeapCreate(int[] array, int size) {
        if (size <= 1) {
            return;
        }
        // 下沉式调整, 需要从后往前遍历,
        // 从最后一个非叶子节点开始遍历
        // size - 1表示最后一个元素的下标
        // 拿着这个下标 - 1 / 2 就得到了当前元素的父节点
        int i = (size - 1 - 1) / 2;
        for (; i >= 0; --i) {
            AdjustDown(array, size, i);
        }
    }

    public static void HeapPop(int[] array, int size) {
        if (size <= 1) {
            return;
        }
        Swap(array, 0, size - 1);
        AdjustDown(array, size - 1, 0);
    }

    public static void HeapSort(int[] array, int size) {
        if (size <= 1) {
            return;
        }
        // 1. 建立大堆
        HeapCreate(array, size);
        // 2. 循环删除堆顶元素
        // 每次删除一个元素, 就把当前的最大值放到数组末尾了
        int i = 0;
        for (; i < size; ++i) {
            HeapPop(array, size - i);
        }
    }
    
    
    
  //希尔排序(改进版 的插入排序）
    
    public static void ShellSort(int [] array) {
    	if(array.length<=1) {
    		return ;
    	}
    	//此处采用希尔序列：N/2,N/4,N/8....
    	int gap=array.length/2;
    	for(;gap>=1;gap/=2) {
    		int bound=gap;
    		for(;bound<array.length;bound++) {
    			int bound_value=array[bound];
    			int i=bound;
    			for(;i>=gap;i-=gap) {
    				if(array[i-gap]>bound_value) {
    					array[i]=array[i-gap];
    				}
    				else {
    					break;
    				}
    			}
    			array[i]=bound_value;
    		}
    	}
    	return;
    }
}



















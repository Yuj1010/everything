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

	//ð������
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
	//ѡ������
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
	
	//��������
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
	
	//������
	//�³�ʽ����
//	public static void ADjustDown(int [] array, int size, int index) {
//		int parent=index;
//		int child=2*parent+1;//������
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
//	//�����
//	public static void HeapCreat(int [] array) {
//		if(array.length<=1) {
//			return ;
//		}
//		//�³�ʽ,�Ӻ���ǰ����
//		int i=(array.length-1-1)/2;//���һ����Ҷ�ӽڵ�
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
        int child = 2 * parent + 1; // ������
        while (child < size) {
            // ���������������бȽ�, ��˭�Ƚϴ�, Ȼ�����ô�ĺ�
            // ���ڵ���бȽ�
            if (child + 1 < size && array[child + 1] > array[child]) {
                // ����������������, ���� child ָ��������
                child = child + 1;
            }
            if (array[child] > array[parent]) {
                Swap(array, child, parent);
            } else {
                // ��ʱ�����͵�������~~
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
        // �³�ʽ����, ��Ҫ�Ӻ���ǰ����,
        // �����һ����Ҷ�ӽڵ㿪ʼ����
        // size - 1��ʾ���һ��Ԫ�ص��±�
        // ��������±� - 1 / 2 �͵õ��˵�ǰԪ�صĸ��ڵ�
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
        // 1. �������
        HeapCreate(array, size);
        // 2. ѭ��ɾ���Ѷ�Ԫ��
        // ÿ��ɾ��һ��Ԫ��, �Ͱѵ�ǰ�����ֵ�ŵ�����ĩβ��
        int i = 0;
        for (; i < size; ++i) {
            HeapPop(array, size - i);
        }
    }
    
    
    
  //ϣ������(�Ľ��� �Ĳ�������
    
    public static void ShellSort(int [] array) {
    	if(array.length<=1) {
    		return ;
    	}
    	//�˴�����ϣ�����У�N/2,N/4,N/8....
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



















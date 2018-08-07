package kw.test.paixu;

import kw.test.paixu.mergeSortRecursion.MergeSortRecursion;

/**
 * auther   kangwang
 * 2018-8-5
 */
public class App {
    public static void main(String[]args){
        int A1[] = { 6, 5, 3 };      // 从小到大归并排序
        for(int i=0;i<A1.length;i++){
            System.out.println(A1[i]);
        }
        MergeSortRecursion.mergeSortRecursion(A1,0,A1.length-1);
        System.out.println("=================");

        System.out.println("=================");
        for(int i=0;i<A1.length;i++){
            System.out.println(A1[i]);
        }

    }
}

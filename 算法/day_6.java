//包含min函数的栈
//定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。

import java.util.*;

public class Solution {

    Stack<Integer> stack = new Stack<>();
    public void push(int node) {
        stack.push(node);
    }
    
    public void pop() {
        stack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int min() {
        int min=stack.peek();
        int temp=0;
        Iterator<Integer> iter = stack.iterator();
        while(iter.hasNext()){
            temp=iter.next();
            if(min>temp){
                min=temp;
            }
        }
        return min;
    }
}
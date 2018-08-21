//用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。

import java.util.Stack;

public class Solution {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();
    
    public void push(int node) {
        stack1.push(node);
    }
    
    public int pop() {
        while(!stack1.isEmpty()){
            stack2.push(stack1.pop());
        }
        int result=stack2.pop();
        while(!stack2.isEmpty()){
            stack1.push(stack2.pop());
        }
        return result;
    }
}


//大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。
n<=39

public class Solution {
    public int Fibonacci(int n) {
		//非递归
        int a=0,b=1,c=1;
        if(n==0){
            return 0;
        }else if(n==1||n==2){
            return 1;
        }else{
            for(int i=3;i<=n;i++){
                a=b+c;
                b=c;
                c=a;
            }
            return a;
        }
    }
}


		
public class Solution {
    public int Fibonacci(int n) {
		//递归
		if(n<=1){
            return n;
        }else{
            return Fibonacci(n-1)+Fibonacci(n-2);
        }
    }
}

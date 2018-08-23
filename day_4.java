/**
public class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }

}
*/
import java.util.*;

public class Solution {
    public void Mirror(TreeNode root) {
        if(root==null){
            return ;
        }
        if(root.left==null&&root.right==null){
            return ;
        }
        /*
        递归
        TreeNode temp=null;
        temp=root.left;
        root.left=root.right;
        root.right=temp;
        if(root.left!=null){
            Mirror(root.left);
        }
        if(root.right!=null){
            Mirror(root.right);
        }*/
        //非递归
        Stack<TreeNode> stack =new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode pRoot =stack.pop();
            if(pRoot.left!=null||pRoot.right!=null){
                TreeNode temp=pRoot.left;
                pRoot.left=pRoot.right;
                pRoot.right=temp;
            }
            if(pRoot.left!=null){
                stack.push(pRoot.left);
            }
            if(pRoot.right!=null){
                stack.push(pRoot.right);
            }
        }
        
    }
}
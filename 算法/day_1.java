/*在一个二维数组中（每个一维数组的长度相同），每一行都
按照从左到右递增的顺序排序，每一列都按照从上到下递增的
顺序排序。请完成一个函数，输入这样的一个二维数组和一个
整数，判断数组中是否含有该整数。*/

public class Solution {
    public boolean Find(int target, int [][] array) {
        int i=0;
        int j=array[0].length-1;
        while(i<array.length&&j>=0){
            if(array[i][j]==target){
                return true;
            }else if(array[i][j]<target){
                i++;
            }else{
                j--;
            }
        }
        return false;
    }
}



/*
输入一棵二叉树，求该树的深度。从根结点到叶结点依次经过的
结点（含根、叶结点）形成树的一条路径，最长路径的长度为树
的深度。
*/

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
public class Solution {
    public int TreeDepth(TreeNode root) {
        if(root==null){
            return 0;
        }
        int left=TreeDepth(root.left);
        int right=TreeDepth(root.right);
        return left>right?(left+1):(right+1);
    }
}



/*
给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一
个结点并且返回。注意，树中的结点不仅包含左右子结点，同时
包含指向父结点的指针。
*/

/*
public class TreeLinkNode {
    int val;
    TreeLinkNode left = null;
    TreeLinkNode right = null;
    TreeLinkNode next = null;

    TreeLinkNode(int val) {
        this.val = val;
    }
}
*/
public class Solution {
    public TreeLinkNode GetNext(TreeLinkNode pNode)
    {
        if(pNode==null){
            return null;
        }
        if(pNode.right!=null){
            pNode=pNode.right;
            while(pNode.left!=null){
                pNode=pNode.left;
            }
            return pNode;
        }
        while(pNode.next!=null){
            if(pNode==pNode.next.left) return pNode.next;
            pNode=pNode.next;
        }
        return null;
    }
}

package com.aero.LeetCodeEassy.Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution {
	
	class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	class TreeNode {
	    int val;
	    TreeNode left;
	    TreeNode right;
	    TreeNode(int x) { val = x; }
	}
	
	/**No191.将一个整数转化为32位二进制数，然后判断1的个数
	 * Number of 1 Bits https://leetcode.com/problems/number-of-1-bits/
	 * TODO n&(n-1) http://blog.csdn.net/wconvey/article/details/44705639
	 * @param n
	 * @return 
	 */
	public int hammingWeight(int n) {
        String nBinaryString = Integer.toBinaryString(n);
        char[] strArry = nBinaryString.toCharArray();
        int num = 0;
        for(int i=0;i<strArry.length;i++) {
        	if(strArry[i]=='1') num++;
        }
		return num;
    }
	
	/**No235.给定一个二叉树，找到给定2个节点的最近共同祖先。PS:祖先可以是自己
	 * Lowest Common Ancestor of a Binary Search Tree 
	 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
	 * @param root 根节点
	 * @param p
	 * @param q
	 * @return
	 */
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		//找到节点路径
		ArrayList<TreeNode> pathNodes1 = new ArrayList<TreeNode>();
		ArrayList<TreeNode> pathNodes2 = new ArrayList<TreeNode>();
		findNode(root,p,pathNodes1);
		findNode(root,q,pathNodes2);
		//比较节点路径
		int miniLegth = pathNodes1.size()>pathNodes2.size()?pathNodes2.size():pathNodes1.size();
		TreeNode ancestor = root;
		for(int i=1;i<miniLegth+1;i++) {			
			if(pathNodes1.get(pathNodes1.size()-i).val != pathNodes2.get(pathNodes2.size()-i).val) {
				break;
			}
			ancestor = pathNodes1.get(pathNodes1.size()-i);
		}
		
		return ancestor; 
    }
		/**
		 * 找到节点路径
		 * @param root
		 * @param p
		 * @param pathNodes 输出节点路径
		 * @return
		 */
		boolean findNode(TreeNode root, TreeNode p,ArrayList<TreeNode> pathNodes) {		
			if(root==null && p==null) {
				pathNodes.add(root);
				return true;
			} else if(root==null || p==null) {
				return false;
			}
			if(root.val == p.val || findNode(root.left,p,pathNodes) || findNode(root.right,p,pathNodes)) {
				pathNodes.add(root);
				return true;
			}		
			return false;
		}


	/**给定两个字符串s和t，写一个函数，判断t是否是s的变位词。如果t跟s包含相同字符但排列顺序不同，则称t是s的变位词
	 * Valid Anagram https://leetcode.com/problems/valid-anagram/
	 * @param s
	 * @param t
	 * @return
	 */
	public boolean isAnagram(String s, String t) {  
	    if(s==null||t==null||s.length()!=t.length()){  
	        return false;  
	    }  
	    char[] array1 = s.toCharArray();  
	    char[] array2 = t.toCharArray();  
	    Arrays.sort(array1);  
	    Arrays.sort(array2);  
	    return Arrays.equals(array1, array2);  
	} 
	
	/**判断数组中是否有重复的元素
	 * Contains Duplicate https://leetcode.com/problems/contains-duplicate/
	 * @param nums
	 * @return
	 */
	public boolean containsDuplicate(int[] nums) {
		Set<Integer> appearedNum = new HashSet<Integer>();  
	    for(int i = 0; i < nums.length; i++){  
	        if(!appearedNum.contains(nums[i])){  
	            appearedNum.add(nums[i]);  
	        } else return true;  
	    }  
	    return false;   
    }
	
	/**把一个二叉树左右全颠倒
	 * Invert Binary Tree https://leetcode.com/problems/invert-binary-tree/
	 * @param root
	 * @return
	 */
	public TreeNode invertTree(TreeNode root) {
        if(root == null) return root;
        if(root.left == null && root.right == null) return root;
		
		TreeNode tempNode = root.left;
		root.left = root.right;
		root.right = tempNode;
		
		invertTree(root.left);
		invertTree(root.right);
		
		return root;
    }
	
	/**判断两个二叉树是否完全相同
	 * Same Tree https://leetcode.com/problems/same-tree/
	 * @param p
	 * @param q
	 * @return
	 */
	public boolean isSameTree(TreeNode p, TreeNode q) {
		if(p==null && q==null) return true;
		else if(p==null || q==null) return false;
		else return p.val==q.val && isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
    }
	
	/**将数组中的0移动到末尾，只能在这个数组内部操作
	 * Move Zeroes https://leetcode.com/problems/move-zeroes/
	 * @param nums 含0数组
	 */
	public void moveZeroes(int[] nums) {
		int pos=0;
		for(int i=0;i<nums.length;i++){
			if(nums[i]!=0){
				nums[pos++] =nums[i];
			}
		}
		for(;pos<nums.length;pos++){
			nums[pos] = 0;
		}
    }
	
	/**删除单向链表中的一个节点 Delete Node in a Linked List
	 * https://leetcode.com/problems/delete-node-in-a-linked-list/
	 * @param node 要删除的节点
	 */
	public void deleteNode(ListNode node) {
        if(node == null || node.next == null) return;
        
        node.val = node.next.val;//这两行代码的顺序不能弄错了！
        node.next = node.next.next;
    }
		
	/**将一个非负整数的各位相加得到新数，然后再次各位相加，直至得到一个个位数，返回他
	 * Add Digits  https://leetcode.com/problems/add-digits/
	 * @param num 非负整数
	 * @return 个位数
	 */
	public int addDigits(int num) {
		/**思路：
		 * 1）num各位相加 = num % 9
		 * 2) 若num=abc,则num = a*100+b*10+c = a+b+c + (a*99+b*9+c*0) 
		 * 3) (x%y)%y == x%y
		 */
		return (num-1)%9+1;//结果肯定大于0
	}
	
	/**求一个二叉树的最大深度 Maximum Depth of Binary Tree
	 * https://leetcode.com/problems/maximum-depth-of-binary-tree/
	 * @param root 二叉树根节点
	 * @return
	 */
	public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
		return leftDepth>rightDepth?leftDepth+1:rightDepth+1;
    }
}



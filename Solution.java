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
	
	/**No191.��һ������ת��Ϊ32λ����������Ȼ���ж�1�ĸ���
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
	
	/**No235.����һ�����������ҵ�����2���ڵ�������ͬ���ȡ�PS:���ȿ������Լ�
	 * Lowest Common Ancestor of a Binary Search Tree 
	 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
	 * @param root ���ڵ�
	 * @param p
	 * @param q
	 * @return
	 */
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		//�ҵ��ڵ�·��
		ArrayList<TreeNode> pathNodes1 = new ArrayList<TreeNode>();
		ArrayList<TreeNode> pathNodes2 = new ArrayList<TreeNode>();
		findNode(root,p,pathNodes1);
		findNode(root,q,pathNodes2);
		//�ȽϽڵ�·��
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
		 * �ҵ��ڵ�·��
		 * @param root
		 * @param p
		 * @param pathNodes ����ڵ�·��
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


	/**���������ַ���s��t��дһ���������ж�t�Ƿ���s�ı�λ�ʡ����t��s������ͬ�ַ�������˳��ͬ�����t��s�ı�λ��
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
	
	/**�ж��������Ƿ����ظ���Ԫ��
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
	
	/**��һ������������ȫ�ߵ�
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
	
	/**�ж������������Ƿ���ȫ��ͬ
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
	
	/**�������е�0�ƶ���ĩβ��ֻ������������ڲ�����
	 * Move Zeroes https://leetcode.com/problems/move-zeroes/
	 * @param nums ��0����
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
	
	/**ɾ�����������е�һ���ڵ� Delete Node in a Linked List
	 * https://leetcode.com/problems/delete-node-in-a-linked-list/
	 * @param node Ҫɾ���Ľڵ�
	 */
	public void deleteNode(ListNode node) {
        if(node == null || node.next == null) return;
        
        node.val = node.next.val;//�����д����˳����Ū���ˣ�
        node.next = node.next.next;
    }
		
	/**��һ���Ǹ������ĸ�λ��ӵõ�������Ȼ���ٴθ�λ��ӣ�ֱ���õ�һ����λ����������
	 * Add Digits  https://leetcode.com/problems/add-digits/
	 * @param num �Ǹ�����
	 * @return ��λ��
	 */
	public int addDigits(int num) {
		/**˼·��
		 * 1��num��λ��� = num % 9
		 * 2) ��num=abc,��num = a*100+b*10+c = a+b+c + (a*99+b*9+c*0) 
		 * 3) (x%y)%y == x%y
		 */
		return (num-1)%9+1;//����϶�����0
	}
	
	/**��һ���������������� Maximum Depth of Binary Tree
	 * https://leetcode.com/problems/maximum-depth-of-binary-tree/
	 * @param root ���������ڵ�
	 * @return
	 */
	public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
		return leftDepth>rightDepth?leftDepth+1:rightDepth+1;
    }
}



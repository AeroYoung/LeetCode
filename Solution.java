package com.aero.LeetCodeEassy.Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
	
	static class ListNode {
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
	
	/**No118. ��������������һ�����������
	 * ˼·����n�е�m����Ϊ�������C(n-1,m-1) = C(n-1,n-m)�͵�n-m-1�������
	 * @param numRows
	 * @return
	 */
    public List<List<Integer>> generate(int numRows) {
    	
    	List<List<Integer>> results = new ArrayList<List<Integer>>();

    	for(int n=0;n<numRows;n++){
    		List<Integer> nRow = new ArrayList<Integer>();
    		int[] mNums = new int[n+1];
    		
    		for(int m=0;m<n/2+1;m++){
        		long factN=1;
        		long factM=1;
        		for(int k=n;k>=n-m+1;k--){
        			factN=factN*k;
        		}    			
    			for(int k=m;k>1;k--){
    				factM=factM*k;
    			}
    			mNums[m]=(int) (factN/factM);
    			mNums[n-m]=mNums[m];
    		}
    		for(int i=0;i<=n;i++){
    			nRow.add(mNums[i]);
    		}
    		results.add(nRow);
    	}
    	
		return results;       
    }
	
	/**���ּ�һ
	 * No66.Plus one https://leetcode.com/problems/plus-one/
	 * @param digits
	 * @return
	 */
    public int[] plusOne(int[] digits) {
		if(0==digits.length) return digits;
		for(int i=digits.length-1;i>=0;i--)
		{
		   if(digits[i]!=9) {digits[i]++;return digits;}
		   else digits[i]=0;
		}
       int[] result=new int[digits.length+1];
       result[0]=1; //��ΪĬ�϶���0
       return result;   
    }
	
	/**No198.House Robber ȡ�������в�����Ԫ��֮�͵Ŀ������ֵ
	 * https://leetcode.com/problems/house-robber/
	 * ˼·���ݹ�
	 * @param nums
	 * @return
	 */
    public int rob(int[] nums) {
    	if(nums.length==0) return 0;
    	else if(nums.length==1) return nums[0];
    	
    	int[] haveRob = new int[nums.length];
    	haveRob[0]=nums[0];
    	haveRob[1]=Math.max(nums[0], nums[1]);
    	for(int i=2;i<nums.length;i++){
    		haveRob[i] = Math.max(haveRob[i-1], haveRob[i-2]+nums[i]);
    	}
    	
    	return haveRob[nums.length-1];
    }
	
	/**No27.Remove Element
	 * ���������Ƴ�������val��ͬ��Ԫ��
	 * @param nums
	 * @param val
	 * @return ������ĳ���
	 */
    public int removeElement(int[] nums, int val) {
        int n=0;
        for(int i=0;i<nums.length;i++)
        {
          if(nums[i]!=val) nums[n++]=nums[i];
        }
        return n;
    }
	
	/**No.Power of Two https://leetcode.com/problems/power-of-two/
	 * �ж��Ƿ���2����
	 * @param n
	 * @return
	 */
    public boolean isPowerOfTwo(int n) {
    	if(n==0) return false;
    	while(n%2==0){
    		n = n/2;
    	}
    	return n==1;
    }
	
	/**No202.Happy Number https://leetcode.com/problems/happy-number/
	 * Write an algorithm to determine if a number is "happy".
	 * A happy number is a number defined by the following process: 
	 * Starting with any positive integer, replace the number by the sum of the squares of its digits, 
	 * and repeat the process until the number equals 1 (where it will stay), 
	 * or it loops endlessly in a cycle which does not include 1. 
	 * Those numbers for which this process ends in 1 are happy numbers.
	    Example: 19 is a happy number		
		1^2 + 9^2 = 82
		8^2 + 2^2 = 68
		6^2 + 8^2 = 100
		1^2 + 0^2 + 0^2 = 1
	 * @param n
	 * @return
	 */
    public boolean isHappy(int n) {    	
    	while(n>=10){
    		int num = n;
    		int m = 0;
    		n=0;
    		while(num!=0){
    			m = num%10;
    			n+=m*m;
    			num = num/10;
    		}
    	}    
        return n==1 || n==7;
    }
	
	/**No263.�ж�һ����������ֻ��2,3,5����6,8����,14����;����1��ugly number
	 * Ugly Number  https://leetcode.com/problems/ugly-number/
	 * @param num
	 * @return
	 */
	public boolean isUgly(int num) {
		if(num<2) return num==1;

	      for(int i=2;i<6&&num>1;i++){
	        while(num%i==0)
	            num/=i;
	      }  
	      return num==1;
    }
	
	/**No83.ɾ�������������е��ظ�Ԫ��
	 * Remove Duplicates from Sorted List https://leetcode.com/problems/remove-duplicates-from-sorted-list/
	 * @param head
	 * @return
	 */
	public ListNode deleteDuplicates(ListNode head) {
		if(head == null || head.next == null) {
	        return head;
	    }
	    ListNode i = head;
	    ListNode j = head.next;
	    while(j != null) {
	        if(i.val == j.val){
	            i.next = j.next;
	            j = j.next;
	        } else {
	            i = i.next;
	            j = j.next;
	        }
	    }
	    return head;    
    }
	
	/**No206.��תһ����������
	 * Reverse Linked List https://leetcode.com/problems/reverse-linked-list/
	 * @param head
	 * @return
	 */
	public ListNode reverseList(ListNode head) {
		if(head == null)    return head;
        ListNode slow=null, mid=null, fast=head;
        while(fast != null){
            slow = mid;
            mid = fast;            
            fast = fast.next;
            mid.next = slow;
        }
        return mid;       
    }
	/**No13.��һ����������(eg,IX=9)ת��Ϊint
	 * Roman to Integer https://leetcode.com/problems/roman-to-integer/
	 * @param s
	 * @return
	 */
	public int romanToInt(String s) {
		if (s == null || s.length() == 0) {
	        return 0;
	    }
	    int[] map = new int[26];
	    map['I' - 'A'] = 1;
	    map['V' - 'A'] = 5;
	    map['X' - 'A'] = 10;
	    map['L' - 'A'] = 50;
	    map['C' - 'A'] = 100;
	    map['D' - 'A'] = 500;
	    map['M' - 'A'] = 1000;
	    int sum = 0;
	    for (int i = 0; i < s.length(); i++) {
	        int cur = map[s.charAt(i) - 'A'];
	        sum += cur - 2 * (sum % cur); //the easy formula
	    }
	    return sum;
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



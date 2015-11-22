package com.aero.LeetCodeEassy.Main;

public class Solution {
	
	/**判断数组中是否有重复的元素
	 * Contains Duplicate https://leetcode.com/problems/contains-duplicate/
	 * @param nums
	 * @return
	 */
	public boolean containsDuplicate(int[] nums) {
		return true;
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



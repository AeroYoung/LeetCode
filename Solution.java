package com.aero.LeetCodeEassy.Main;

public class Solution {
	
	/**�ж��������Ƿ����ظ���Ԫ��
	 * Contains Duplicate https://leetcode.com/problems/contains-duplicate/
	 * @param nums
	 * @return
	 */
	public boolean containsDuplicate(int[] nums) {
		return true;
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



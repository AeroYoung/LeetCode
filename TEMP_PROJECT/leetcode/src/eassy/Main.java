package eassy;

import eassy.Solution.ListNode;

public class Main {

	public static void main(String[] args) {		
		//Initialization
		ListNode head = new ListNode(0);
		ListNode child1 = new ListNode(2);
		ListNode child2 = new ListNode(2);
		ListNode child3 = new ListNode(2);
		ListNode child4 = new ListNode(3);
		ListNode child5 = new ListNode(3);
		head.next = child1;
		child1.next = child2;
		child2.next = child3;
		child3.next = child4;
		child4.next = child5;
		child5.next = null;
		
		//int nums[] = {-1,-5,-5,-3,2,5,0,4};
		int nums[] = {0,0,0,0};
		//Solution
		Solution solution = new Solution();	 
		System.out.println(solution.fourSum(nums,0));
	}
}

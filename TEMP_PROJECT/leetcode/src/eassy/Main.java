package eassy;

import java.util.HashSet;
import java.util.Set;

import eassy.Solution.ListNode;
import eassy.Solution.TreeNode;

public class Main {

	public static void main(String[] args) {		
		//Initialization
		TreeNode one = new TreeNode(1);
		TreeNode two = new TreeNode(2);
		TreeNode three = new TreeNode(3);
		TreeNode four = new TreeNode(4);
		TreeNode five = new TreeNode(5);
		TreeNode six = new TreeNode(6);
		one.left = two;
		one.right = three;
		two.right = five;
		three.right = four;
		five.right = six;
		
		Solution solution = new Solution();	 
		System.out.println(solution.rightSideView(one));
	}
}

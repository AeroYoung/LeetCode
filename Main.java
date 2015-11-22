package com.aero.LeetCodeEassy.Main;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
		
		int nums[] = {0,1,0,3,0,4,5,6,0,9};
		if(solution.containsDuplicate(nums)) System.out.println("true");
		else System.out.println("false");
	}
}


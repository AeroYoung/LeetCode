package eassy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.Character;
import java.lang.reflect.Array;



public class Solution {
	
	class Interval {
		int start;
		int end;
		Interval() { start = 0; end = 0; }
		public Interval(int s, int e) { start = s; end = e; }
	}
	static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	static class TreeNode {
	    int val;
	    TreeNode left;
	    TreeNode right;
	    public TreeNode(int x) { val = x; }
	}

	public int lengthOfLongestSubstring(String s) {
        if(s.length()==1) return 1;
        else if(s.length()==0) return 0;
        
        int result = 0;
		int i=0;
		int j=1;
		
		while(j<s.length())
		{
			String subString = s.substring(i, j);
			String next = s.substring(j, j+1);
			int index = subString.indexOf(next);
			
			if(index>=0){	
				result = (j-i)>result?(j-i):result;
				i = i+index+1;
			}
			else{
				result = (j-i+1)>result?(j-i+1):result;
			}
			j++;	
		}
		return result;
    }
	
	public List<Integer> rightSideView(TreeNode root) {
		List<Integer> result = new ArrayList<Integer>();
        if(root==null) return result;
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        while(q.size()>0){
            int size = q.size();
            for(int i=0;i<size;i++){
                TreeNode node= q.poll();
                if(i==size-1)
                    result.add(node.val);
                if(node.left!=null) q.add(node.left);
                if(node.right!=null) q.add(node.right);
            }
        }
        return result;
    }
	
	/**No.127 Word Ladder
	 * 
	 * @param beginWord
	 * @param endWord
	 * @param wordList
	 * @return
	 */
	public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
		Set<String> beginSet = new HashSet<String>(), endSet = new HashSet<String>();
		HashSet<String> visited = new HashSet<String>();
		int len = 1;		
		
		beginSet.add(beginWord);
		endSet.add(endWord);
		while (!beginSet.isEmpty() && !endSet.isEmpty()) {
			if (beginSet.size() > endSet.size()) {
				Set<String> set = beginSet;
				beginSet = endSet;
				endSet = set;
			}

			Set<String> temp = new HashSet<String>();
			for (String word : beginSet) {
				char[] chs = word.toCharArray();

				for (int i = 0; i < chs.length; i++) {
					for (char c = 'a'; c <= 'z'; c++) {
						char old = chs[i];
						chs[i] = c;
						String target = String.valueOf(chs);

						if (endSet.contains(target)) {
							return len + 1;
						}

						if (!visited.contains(target) && wordList.contains(target)) {
							temp.add(target);
							visited.add(target);
						}
						chs[i] = old;
					}
				}
			}

			beginSet = temp;
			len++;
		}
		
		return 0;
    }
	
	/**No65. Valid Number
	 * 
	 * @param s
	 * @return
	 */
	public boolean isNumber(String s) {
		s = s.trim();
	    
	    boolean pointSeen = false;
	    boolean eSeen = false;
	    boolean numberSeen = false;
	    boolean numberAfterE = true;
	    for(int i=0; i<s.length(); i++) {
	        if('0' <= s.charAt(i) && s.charAt(i) <= '9') {
	            numberSeen = true;
	            numberAfterE = true;
	        } else if(s.charAt(i) == '.') {
	            if(eSeen || pointSeen) {
	                return false;
	            }
	            pointSeen = true;
	        } else if(s.charAt(i) == 'e') {
	            if(eSeen || !numberSeen) {
	                return false;
	            }
	            numberAfterE = false;
	            eSeen = true;
	        } else if(s.charAt(i) == '-' || s.charAt(i) == '+') {
	            if(i != 0 && s.charAt(i-1) != 'e') {
	                return false;
	            }
	        } else {
	            return false;
	        }
	    }
	    
	    return numberSeen && numberAfterE;
    }
	
	/**No8. String to Integer (atoi)
	 * 
	 * @param str
	 * @return
	 */
	public int myAtoi(String str) {
		int index = 0, sign = 1, total = 0;
	    //1. Empty string
	    if(str.length() == 0) return 0;

	    //2. Remove Spaces
	    while(str.charAt(index) == ' ' && index < str.length())
	        index ++;

	    //3. Handle signs
	    if(str.charAt(index) == '+' || str.charAt(index) == '-'){
	        sign = str.charAt(index) == '+' ? 1 : -1;
	        index ++;
	    }
	    
	    //4. Convert number and avoid overflow
	    while(index < str.length()){
	        int digit = str.charAt(index) - '0';
	        if(digit < 0 || digit > 9) break;

	        //check if total will be overflow after 10 times and add digit
	        if(Integer.MAX_VALUE/10 < total || Integer.MAX_VALUE/10 == total && Integer.MAX_VALUE %10 < digit)
	            return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;

	        total = 10 * total + digit;
	        index ++;
	    }
	    return total * sign;
    }
	
	/**No42. Trapping Rain Water
	 * 
	 * @param height
	 * @return
	 */
	public int trap(int[] height) {
        int result = 0;
        if(height.length<3) return 0;
        
        int left = 0;
        int right = height.length-1;        
        
        while(right>left){
        	int min = Math.min(height[left], height[right]);
        	if(height[left]==min)
        		while(++left<right && height[left]<min)
        			result+=min-height[left];
        	else
        		while(left<--right && height[right]<min)
        			result+=min-height[right];
        	        	
        }
        
        return result;
    }
	
	/**No18. 4Sum
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public List<List<Integer>> fourSum(int[] nums, int target) {
		List<List<Integer>> results = new ArrayList<List<Integer>>();  
		Arrays.sort(nums);
		
		for(int i=0;i<nums.length-3;i++){
			if(i>0 && nums[i]==nums[i-1]) continue;
			for(int j=i+1;j<nums.length-2;j++){
				if(j>i+1 && nums[j]==nums[j-1]) continue;
				int left = j+1;
				int right = nums.length-1;
				while(left<right){
					int sum = nums[i]+nums[j]+nums[left]+nums[right]-target;
					if(sum==0){
	        			Integer result[] = {nums[i],nums[j],nums[left],nums[right]};
	        			results.add(Arrays.asList(result));
	        			
	        			while(++left<right && nums[left] ==nums[left-1]);
	        			while(left<--right && nums[right]==nums[right+1]);        			
	        		}else if(sum<0) ++left;
	        		else --right;
				}
			}
		}		
		return results;
    }
	
	/**No15. 3Sum
	 * 
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> results = new ArrayList<List<Integer>>();        
        Arrays.sort(nums);
        for(int i=0;i<nums.length-2;i++){
        	if(i>0 && nums[i]==nums[i-1]) continue;
        	int left = i+1;
        	int right = nums.length-1;
        	while(left<right){
        		int sum = nums[i]+nums[left]+nums[right];
        		if(sum==0){
        			Integer result[] = {nums[i],nums[left],nums[right]};
        			results.add(Arrays.asList(result));
        			
        			while(++left<right && nums[left] ==nums[left-1]);
        			while(left<--right && nums[right]==nums[right+1]);        			
        		}else if(sum<0) ++left;
        		else --right;
        	}        	
        }        
        return results;
    }
	
	/**No16. 3Sum Closest --> 15
	 * You may assume that each input would have exactly one solution
	 * @param nums
	 * @param target
	 * @return
	 */
	public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int result = java.lang.Integer.MAX_VALUE-1;
        for(int i=0;i<nums.length-2;i++){
        	int left = i+1;
        	int right = nums.length-1;
        	while(left<right){
        		int sum = nums[i]+nums[left]+nums[right];
        		if(sum==target) return target;
        		else if(sum>target) right -= 1;
        		else left +=1;
        		if(Math.abs(target-sum)<Math.abs(target-result))
        			result = sum;
        	}
        }
        
		return result;
    }
	
	/**No.14 Longest Common Prefix
	 * 
	 * @param strs
	 * @return
	 */
	public String longestCommonPrefix(String[] strs) {
        if(strs.length==0) return "";
		
        String prefix = strs[0];
        for(int i=1;i<strs.length;i++){
        	while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            } 
        }
        
        return prefix;
    }
	
	/**No.10 Regular Expression Matching
	 * 
	 * @param s
	 * @param p
	 * @return
	 */
	public boolean isMatch(String s, String p) {
        if(s==p) return true;
        
        char preEle=' ';
        while(p.length()>0 && s.length()>0)
        {
        	char sp = p.charAt(0);        	
        	char ss = s.charAt(0);
        	
        	if(sp=='.'){        		
        		preEle = sp;
        		s = s.substring(1);
        		p = p.substring(1);
        		
        	}else if(sp=='*'){
        		if(preEle=='.') preEle=ss;
        		int sl=s.length();
        		for(int i=0;i<s.length();i++){
        			if(s.charAt(i)!=preEle){
        				sl = i;
        				break;
        			}
        		}
        		
        		int pl=s.length();
        		for(int i=1;i<p.length();i++){
        			if(p.charAt(i)!=preEle){
        				pl = i;
        				break;
        			}
        		}
        		
        		s = s.substring(sl);
        		p = p.substring(Math.min(sl, pl));
        	}else{ //如果是字母
        		if(sp==ss){
        			preEle = sp;
        			s = s.substring(1);
            		p = p.substring(1);
        			continue;
        		}else{
        			char sps = p.charAt(p.length()>1?1:0);
        			if(sps!='*'){
        				return false;
        			}else{
        				int l=s.length();
                		for(int i=0;i<s.length();i++){
                			if(s.charAt(i)!=sp){
                				l = i;
                				break;
                			}
                		}
                		s = s.substring(l);
                		p = p.substring(2);
        			}
        		}
        	}
        }
        
        if(p.length()==s.length())return true;
        else return false;
    }
	
	/**No12.返回罗马数字，1~3999
	 * 
	 * @param num
	 * @return
	 */
	public String intToRoman(int num) {
        String result = "";
        
        String[][] roman = {
			        		{"","I","II","III","IV","V","VI","VII","VIII","IX"},
			                {"","X","XX","XXX","XL","L","LX","LXX","LXXX","XC"},
			                {"","C","CC","CCC","CD","D","DC","DCC","DCCC","CM"},
			                {"","M","MM","MMM"}};
        
        result+=roman[3][num/1000];
        result+=roman[2][num/100%10];
        result+=roman[1][num/10%10];
        result+=roman[0][num%10];
        
        return result;
    }
	
	/**No11. Container With Most Water 装最多的水！！
	 * 
	 * @param height
	 * @return
	 */
	public int maxArea(int[] height) {
		int result = 0;
		
		int i = 0;
		int j= height.length - 1;
		while(i<j){
			int temp = getArea(height[i],height[j],j-i);
			result=temp>result?temp:result;
			
			if(height[i]>height[j]) j--;
			else i++;
		}
		
		return result;        
    }
	private int getArea(int frist,int second,int distance){
		return distance*Math.min(frist,second);
	}
	
	/**No2.Add Two Numbers
	 * 
	 * @param l1
	 * @param l2
	 * @return
	 */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		if(l1==null || l2==null) return null;
    	
    	return l1;        
    }
	
	/**No1.Two Sum
	 * https://leetcode.com/problems/two-sum/
	 * @param nums
	 * @param target
	 * @return
	 */
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int[] defaultResult = {0, 0};
        for (int i = 0; i < nums.length; i++) {
            if (map.get(target-nums[i]) != null ) {
                int[] result = {map.get(target-nums[i]) + 1, i + 1 };
                return result;
            }
            map.put(nums[i], i);
        }
        return defaultResult;      
    }
	
	/**No58.Length of Last Word
	 * 
	 * @param s
	 * @return
	 */
    public int lengthOfLastWord(String s) {
    	if(s.length()==0) return 0;
    	s=s.trim();
    	String lastWord = s.lastIndexOf(' ')>-1?s.substring(s.lastIndexOf(' '),s.length()-1):s;
    	return lastWord.length();
    }
	
	/**No238.Product of Array Except Self
	 * Return an array such that output[i] is equal to the product of all the elements of nums except nums[i].
	 * @param nums An array of n integers where n > 1
	 * @return
	 */
    public int[] productExceptSelf(int[] nums) {
	 int len = nums.length;
	    int [] output = new int[len];

	    int leftMult = 1, rightMult = 1;

	    for(int i = len-1; i >= 0; i--){
	        output[i] = rightMult;
	        rightMult *= nums[i];
	    }
	    for(int j = 0; j < len; j++){
	        output[j] *= leftMult;
	        leftMult *= nums[j];

	    }

	    return output;
    }
	
	/**No136.Single Number I
	 * 思路：位运算的交换律和结合律 http://www.cnblogs.com/wuyuegb2312/p/3254506.html
	 * a^a=0 a^0=a a^b^a=a^a^b
	 * @param nums
	 * @return
	 */
    public int singleNumber(int[] nums) {
    	int result = nums[0];
    	for(int i=1;i<nums.length;i++) {
    		result^=nums[i];
    	}
    	return result;
    }
	
	/**No165.比较版本大小 2.37>2.5>1.1>0.1
	 * 
	 * @param version1
	 * @param version2
	 * @return version1>version2返回1，<返回-1，=返回0
	 */
    public int compareVersion(String version1, String version2) {
    	while(version1.length()>0 || version2.length()>0) {
    		int int1 = version1.length()>0?Integer.parseInt(version1.substring(0, version1.indexOf('.')>0?version1.indexOf('.'):version1.length())):0;
        	int int2 = version2.length()>0?Integer.parseInt(version2.substring(0, version2.indexOf('.')>0?version2.indexOf('.'):version2.length())):0;
        	if(int1>int2) return 1;
        	else if(int2>int1) return -1;
        	
        	version1 = version1.indexOf('.')>0?version1.substring(version1.indexOf('.')+1):"";
        	version2 = version2.indexOf('.')>0?version2.substring(version2.indexOf('.')+1):"";
    	}
    	
    	return 0;
    }
	
	/**No189.Rotate an array of n elements to the right by k steps.
	 * https://leetcode.com/problems/rotate-array/
	 * @param nums
	 * @param k
	 */
	public void rotate(int[] nums, int k) {
    	if(k<1) return;
    	if(k>nums.length) k-=nums.length;
    	int tail[] = new int[k];
    	for(int i=0;i<k;i++){
    		tail[i]=nums[nums.length-k+i];
    	}
    	for(int i=nums.length-1;i>k-1;i--){
    		nums[i]=nums[i-k];
    	}
    	for(int i=0;i<k;i++){
    		nums[i]=tail[i];
    	}
    }
	
	/**No125.判断是否是回文，忽略字符串中字母数字以外的符号
	 * 
	 * @param s
	 * @return
	 */
    public boolean isPalindrome(String s) {
    	if(s=="") return false;
    	int i=0;
    	int j=s.length()-1;
    	char[] chs = s.toCharArray();
    	
    	while(i<j){
    		while(!Character.isLetterOrDigit(chs[i])) {
    			i++;
    			if(i==j) return true;
    		}
    		while(!Character.isLetterOrDigit(chs[j])) {
    			j--;
    			if(i==j) return true;
    		}
    		
    		if(Character.toLowerCase(chs[i])==Character.toLowerCase(chs[j])){
    			i++;
    			j--;
    		} else {
    			return false;
    		}
    	}
    	
    	return true;
    }
	
	/**No7.reverse integer include negative-number eg:input -123 output -321
	 * int可能溢出，如何解决?
	 * @param x
	 * @return
	 */
    public int reverse(int x) {
        int result = 0;
        if(x==0) return 0;
        
    	while(x!=0){
    		if(Math.abs(result)> Integer.MAX_VALUE/10) return 0;
    		result = result*10 + x%10;
    		x/=10;
    	}
        
        return result;
    }
	
	/**No223.Rectangle Area
	 * Find the total area covered by two rectilinear rectangles in a 2D plane.
	 */
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        
    	int areaA = (C-A)*(D-B); 
        int areaB = (G-E)*(H-F); 
    	
        int bottomX = Math.max(A, E); 
        int bottomY = Math.max(B, F);
        int topX = Math.min(C,G);
        int topY = Math.min(D,H);

        int overlap = 0; 
        if(topX > bottomX && topY > bottomY) { 
            overlap = (topX - bottomX) * (topY - bottomY);   
        } 
        
    	return areaA+areaB-overlap;
    }
	
	/**No9.Palindrome Number 判断一个整数是不是回文数字
	 * 
	 * @param x
	 * @return
	 */
    public boolean isPalindrome(int x) {
    	int original = x;
    	int result = 0;
    	
    	while(x>0){
    		result = result*10 + x%10;
    		x/=10;
    	}
    	
    	return original==result;
    }
	
	/**No172.Factorial Trailing Zeroes:Given an integer n, return the number of trailing zeroes in n!.
	 * n!作质因子分解，质因子2的个数多余5的个数，所以计算出n!中有多少个质因子5就可以了
	 * @param n
	 * @return
	 */
    public int trailingZeroes(int n) {
    	int count = 0; 
    	while (n > 1) {
    	  count += n / 5;
    	  n = n / 5; 
    	}
    	return count;       
    }
	
	/**No119. Given an index k, return the kth row of the Pascal's triangle.
	 * 
	 * @param rowIndex
	 * @return
	 */
    public List<Integer> getRow(int rowIndex) {
        Integer dp[] = new Integer[rowIndex + 1];       
        for (int i = 0; i < rowIndex + 1; i++) 
            for (int j = i; j >= 0; j--) 
                dp[j] = (j == 0 || j == i) ? 1 : (dp[j-1] + dp[j]);
        return Arrays.asList(dp);
    }
	
	/**No118. 输入行数，返回一个杨辉三角形
	 * 思路：第n行第m个数为排列组合C(n-1,m-1) = C(n-1,n-m)和第n-m-1个数相等
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
	
	/**数字加一
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
       result[0]=1; //因为默认都是0
       return result;   
    }
	
	/**No198.House Robber 取得数组中不相邻元素之和的可能最大值
	 * https://leetcode.com/problems/house-robber/
	 * 思路：递归
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
	 * 从数组中移除所有与val相同的元素
	 * @param nums
	 * @param val
	 * @return 新数组的长度
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
	 * 判断是否是2的幂
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
	
	/**No263.判断一个数质因子只有2,3,5比如6,8就是,14不是;另外1是ugly number
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
	
	/**No83.删除有序单向链表中的重复元素
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
	
	/**No206.反转一个单向链表
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
	/**No13.给一个罗马数字(eg,IX=9)转换为int
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

	/**No.56 给一些区间的集合,合并重叠的区间
	 * Merge Intervals https://leetcode.com/problems/merge-intervals/
	 * @param intervals
	 * @return
	 */
	public List<Interval> merge(List<Interval> intervals) {
		 List<Interval> result = new ArrayList<>();
		    Collections.sort(intervals, new IntervalComparator());
		    if(intervals.size()==0){
		        return result;
		    }
		    result.add(intervals.get(0));
		    for(int index=1; index<intervals.size(); index++){
		        if(intervals.get(index).start<=result.get(result.size()-1).end){
		            result.get(result.size()-1).end=Math.max(intervals.get(index).end,result.get(result.size()-1).end);
		        }
		        else{
		            result.add(intervals.get(index));
		        }
		    }
		    return result;      
    }	
	class IntervalComparator implements Comparator<Interval> {
	    public int compare(Interval o1, Interval o2){
	        return o1.start-o2.start;
	    }
	}
}



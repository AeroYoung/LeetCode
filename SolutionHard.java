package com.aero.LeetCodeEassy.Main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class SolutionHard {
	
	class Interval {
		int start;
		int end;
		Interval() { start = 0; end = 0; }
		public Interval(int s, int e) { start = s; end = e; }
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

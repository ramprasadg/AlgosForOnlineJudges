package com.ramprasadg.leetcode;

import static org.junit.Assert.*;

import org.junit.Test;

public class PeakElement {
    public int findPeakElement(int[] nums) {
        int start = 0;
        int end = nums.length;
        int mid = (start + end) / 2;
        while((mid == 0 || nums[mid-1] > nums[mid]) || ( mid == nums.length-1 || nums[mid] < nums[mid+1]) && start != end) {
            if(nums[mid-1] > nums[mid]) {
                end = mid;
            } else {
                start = mid;
            }
            mid = (start + end) / 2;
        }
        return mid;
    }
    
    @Test
    public void testCase1() {
        int arr[] = {2,1};
        assertEquals(findPeakElement(arr), 0);
    }
    
    @Test
    public void testCase2() {
        int arr[] = {1,2};
        assertEquals(findPeakElement(arr), 0);
    }
}

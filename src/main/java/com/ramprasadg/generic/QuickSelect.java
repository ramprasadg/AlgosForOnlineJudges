package src.main.java.com.ramprasadg.generic;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class QuickSelect {
    public int thirdMax(int[] nums1) {
        if (nums1.length == 0) {
            return Integer.MIN_VALUE;
        }
        
        Set<Integer> s = new HashSet<Integer>();
        for(int num:nums1) {
            s.add(num);
        }
        
        Integer[] nums = s.toArray(new Integer[s.size()]);
        
        if (nums.length < 3) {
            return kthElement(nums, 0, nums.length, nums.length - 1);
        } else {
            return kthElement(nums, 0, nums.length, nums.length - 3);
        }
    }

    public int kthElement(Integer[] nums, int start, int end, int k) {
        //System.out.println("kthElement start=" + start + " end=" + end + " k=" + k);
        int pivot = end - 1;
        int i = start;
        int j = pivot - 1;
        while (i < j) {
            while (i < j && nums[i] < nums[pivot]) {
                i++;
            }
            while (j > i && nums[j] > nums[pivot]) {
                j--;
            }
            if (i < j) {
                swap(nums, i, j);
            }
        }
        int newPivot = pivot;
        if (i < pivot && nums[i] > nums[pivot]) {
            newPivot = i;
            swap(nums, newPivot, pivot);
        }

        if (newPivot == k) {
            return nums[newPivot];
        } else {
            if (newPivot > k) {
                return kthElement(nums, start, newPivot, k);
            } else {
                return kthElement(nums, newPivot, end, k - newPivot);
            }
        }
    }

    public void swap(Integer[] nums, int i, int j) {
        System.out.println("swapping i=" + i + " j=" + j);
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }

    @Test
    public void test1() {
        int arr[] = { 1, 2, 3 };
        assertEquals(thirdMax(arr), 1);
    }

    @Test
    public void test2() {
        int arr[] = { 1, 2, -1 };
        assertEquals(thirdMax(arr), -1);
    }
    
    @Test
    public void test3() {
        int arr[] = { 1, 2 };
        assertEquals(thirdMax(arr), 2);
    }
    
    @Test
    public void test4() {
        int arr[] = { 1,2,2,5,3,5 };
        assertEquals(thirdMax(arr), 2);
    }
    
    @Test
    public void test5() {
        int arr[] = { };
        assertEquals(thirdMax(arr), Integer.MIN_VALUE);
    }
}

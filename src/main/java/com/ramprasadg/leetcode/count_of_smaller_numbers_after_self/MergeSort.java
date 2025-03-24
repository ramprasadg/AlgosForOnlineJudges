package com.ramprasadg.leetcode.count_of_smaller_numbers_after_self;

import static org.junit.Assert.*;

import java.util.*;
import org.junit.Test;

// https://discuss.leetcode.com/topic/31554/11ms-java-solution-using-merge-sort-with-explanation
public class MergeSort {
    int[] count;

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<Integer>();

        count = new int[nums.length];
        int[] indexes = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            indexes[i] = i;
        }
        mergesort(nums, indexes, 0, nums.length - 1);
        print(nums);
        print(indexes);

        for (int i = 0; i < count.length; i++) {
            res.add(count[i]);
        }
        return res;
    }

    private void mergesort(int[] nums, int[] indexes, int start, int end) {
        if (end <= start) {
            return;
        }
        int mid = (start + end) / 2;
        mergesort(nums, indexes, start, mid);
        mergesort(nums, indexes, mid + 1, end);

        merge(nums, indexes, start, end);
    }

    private void merge(int[] nums, int[] indexes, int start, int end) {
        System.out.println("start=" + start + " end=" + end);
        print(nums);
        print(indexes);

        int mid = (start + end) / 2;
        int left_index = start;
        int right_index = mid + 1;
        int rightcount = 0;
        int[] new_indexes = new int[end - start + 1];

        int sort_index = 0;
        while (left_index <= mid && right_index <= end) {
            if (nums[indexes[right_index]] < nums[indexes[left_index]]) {
                new_indexes[sort_index] = indexes[right_index];
                rightcount++;
                right_index++;
            } else {
                new_indexes[sort_index] = indexes[left_index];
                count[indexes[left_index]] += rightcount;
                left_index++;
            }
            sort_index++;
        }
        while (left_index <= mid) {
            new_indexes[sort_index] = indexes[left_index];
            count[indexes[left_index]] += rightcount;
            left_index++;
            sort_index++;
        }
        while (right_index <= end) {
            new_indexes[sort_index++] = indexes[right_index++];
        }
        for (int i = start; i <= end; i++) {
            indexes[i] = new_indexes[i - start];
        }
    }

    public void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
        System.out.println();
    }

    @Test
    public void test1() {
        List<Integer> result = countSmaller(new int[]{2, 1});
        List<Integer> expected = Arrays.asList(new Integer[]{1, 0});
        assertEquals(expected, result);
    }

    @Test
    public void test2() {
        List<Integer> result = countSmaller(new int[]{5, 2, 6, 1});
        List<Integer> expected = Arrays.asList(new Integer[]{2, 1, 1, 0});
        assertEquals(expected, result);
    }
}

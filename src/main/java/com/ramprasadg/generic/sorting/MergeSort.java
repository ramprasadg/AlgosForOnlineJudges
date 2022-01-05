package src.main.java.com.ramprasadg.generic.sorting;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import src.main.java.com.ramprasadg.utils.Utils;

public class MergeSort {

    int flipCounts[];

    public void sort(int arr[]) {
        flipCounts = new int[arr.length];
        sort(arr, 0, arr.length - 1);
        Utils.printArray(flipCounts);
    }

    private void sort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }

        int mid = (start + end) / 2;

        sort(arr, start, mid);
        sort(arr, mid + 1, end);

        merge(arr, start, end);
    }

    private void merge(int[] arr, int start, int end) {
        Utils.printArray(flipCounts);

        int arr1[] = new int[end - start + 1];
        int mid = (start + end) / 2;
        int l = start;
        int r = mid + 1;
        int i = 0;
        int flipCount = 0;

        while (l <= mid && r <= end) {
            if (arr[l] < arr[r]) {
                arr1[i] = arr[l];
                flipCounts[arr[l]] += flipCount;
                l++;
                i++;
            } else {
                arr1[i] = arr[r];
                flipCount++;
                r++;
                i++;
            }
        }

        while (l <= mid) {
            arr1[i] = arr[l];
            flipCounts[arr[l]] += flipCount;
            l++;
            i++;
        }

        while (r <= end) {
            arr1[i] = arr[r];
            r++;
            i++;
        }

        for (i = 0; i < arr1.length; i++) {
            arr[start + i] = arr1[i];
        }
    }

    @Test
    public void test() {
        int[] arr = { 2, 1, 0 };
        Arrays.sort(arr);
        int[] arr1 = { 2, 1, 0 };
        sort(arr1);
        for (int i = 0; i < arr.length; i++) {
            assertEquals(arr[i], arr1[i]);
        }
    }
}

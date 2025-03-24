package com.ramprasadg.generic;

import java.util.*;

public class WeaveArrays {
    public static void main(String args[]) {
        int arr1[] = {1, 2, 3};
        int arr2[] = {4, 5, 6};
        weave(arr1, arr2, 0, 0, new LinkedList<Integer>());
    }

    private static void weave(int[] arr1, int[] arr2, int i, int j, List<Integer> list) {
        if (i == arr1.length && j == arr2.length) {
            for (int x : list) {
                System.out.print(x + " ");
            }
            System.out.println();
        } else {
            if (i < arr1.length) {
                List<Integer> list1 = new LinkedList<Integer>(list);
                list1.add(arr1[i]);
                weave(arr1, arr2, i + 1, j, list1);
            }
            if (j < arr2.length) {
                List<Integer> list2 = new LinkedList<Integer>(list);
                list2.add(arr2[j]);
                weave(arr1, arr2, i, j + 1, list2);
            }
        }
    }
}

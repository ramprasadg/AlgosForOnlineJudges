package com.ramprasadg.interview.coinbase;

import java.util.*;

public class JDelta {
    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int d = s.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = s.nextInt();
        }
        System.out.println(findCount(arr, d));
    }

    private static int findCount(int[] arr, int d) {
        int count = 0;
        Arrays.sort(arr);
        for (int i = 0, j = 1; j < arr.length;) {
            if (arr[j] - arr[i] == d) {
                count++;
            }
            if (arr[j] - arr[i] < d) {
                j++;
            } else {
                i++;
            }
        }
        return count;
    }
}

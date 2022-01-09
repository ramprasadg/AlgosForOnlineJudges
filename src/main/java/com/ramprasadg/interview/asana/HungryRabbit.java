package com.ramprasadg.interview.asana;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

public class HungryRabbit {
    public int totalCarrots(final int input[][]) {
        if (input == null || input.length == 0 || input[0].length == 0) {
            return 0;
        }
        int[][] arr = new int[input.length][input[0].length];

        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length; j++) {
                arr[i][j] = input[i][j];
            }
        }

        return findTotalCarrotHelper(arr);
    }

    private int findTotalCarrotHelper(int[][] arr) {
        int midI = arr.length / 2;
        int midJ = arr[0].length / 2;

        List<Integer> is = new ArrayList<Integer>();
        List<Integer> js = new ArrayList<Integer>();

        is.add(midI);
        if (arr.length % 2 == 0) {
            is.add(midI - 1);
        }

        js.add(midJ);
        if (arr[0].length % 2 == 0) {
            js.add(midJ - 1);
        }

        int max = Integer.MIN_VALUE;
        int posI = 0;
        int posJ = 0;

        for (int i : is) {
            for (int j : js) {
                if (safeGetValue(arr, i, j) > max) {
                    posI = i;
                    posJ = j;
                    max = safeGetValue(arr, i, j);
                }
            }
        }

        return findTotalCarrotHelperRecursion(arr, posI, posJ);
    }

    private int findTotalCarrotHelperRecursion(int[][] arr, int posI, int posJ) {
        int carrots = safeGetValue(arr, posI, posJ);

        if (carrots == 0) {
            return carrots;
        }

        int nextBestI = posI;
        int nextBestJ = posJ;
        safeSetValue(arr, posI, posJ, 0);
        int bestVal = safeGetValue(arr, nextBestI, nextBestJ);

        int[] movesI = { 0, 0, 1, -1 };
        int[] movesJ = { 1, -1, 0, 0 };

        for (int i = 0; i < movesI.length; i++) {
            int nextI = posI + movesI[i];
            int nextJ = posJ + movesJ[i];

            if (safeGetValue(arr, nextI, nextJ) > bestVal) {
                bestVal = safeGetValue(arr, nextI, nextJ);
                nextBestI = nextI;
                nextBestJ = nextJ;
            }
        }
        return carrots + findTotalCarrotHelperRecursion(arr, nextBestI, nextBestJ);
    }

    private int safeGetValue(int[][] arr, int i, int j) {
        if (i < 0 || j < 0 || i >= arr.length || j >= arr[0].length) {
            return 0;
        } else {
            return arr[i][j];
        }
    }

    private void safeSetValue(int[][] arr, int i, int j, int val) {
        if (i < 0 || j < 0 || i >= arr.length || j >= arr[0].length) {
            return;
        } else {
            arr[i][j] = val;
        }
    }

    @Test
    public void test1() {
        int arr[][] = { { 5, 7, 8, 6, 3 }, { 0, 0, 7, 0, 4 }, { 4, 6, 3, 4, 9 }, { 3, 1, 0, 5, 8 } };
        assertEquals(27, totalCarrots(arr));
    }

    @Test
    public void test2() {
        int arr[][] = { { 1 }, { 1 } };
        assertEquals(2, totalCarrots(arr));
    }

    @Test
    public void test3() {
        int arr[][] = { { 1 }, { 2 } };
        assertEquals(3, totalCarrots(arr));
    }

    @Test
    public void test4() {
        int arr[][] = { { 2 }, { 1 } };
        assertEquals(3, totalCarrots(arr));
    }

    @Test
    public void test5() {
        int arr[][] = { { 1 } };
        assertEquals(1, totalCarrots(arr));
    }

    @Test
    public void test6() {
        int arr[][] = { { 1, 2 } };
        assertEquals(3, totalCarrots(arr));
    }

    @Test
    public void test7() {
        int arr[][] = { { 2, 1 } };
        assertEquals(3, totalCarrots(arr));
    }

    @Test
    public void test8() {
        int arr[][] = { { 1, 3, 6, 7 }, { 4, 2, 5, 3 }, { 5, 3, 4, 8 } };
        assertEquals(51, totalCarrots(arr));
    }
    
    @Test
    public void test9() {
        int arr[][] = { { } };
        assertEquals(0, totalCarrots(arr));
    }
    
    @Test
    public void test10() {
        int arr[][] = { { 1, 3, 6, 7 }, { 4, 2, 5, 3 }, { 5, 0, 4, 8 } };
        assertEquals(33, totalCarrots(arr));
    }

}

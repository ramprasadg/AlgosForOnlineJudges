package com.ramprasadg.generic.datastructures;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * segment tree with lazy propagation
 *
 * @author rampg
 */
public class SegmentTrees {
    int n;
    int tree[];
    int lazy[];

    public void printTree() {
        print(tree);
    }

    public void printLazy() {
        print(lazy);
    }

    private void print(int[] tree) {
        for (int i = 0; i < tree.length; i++) {
            System.out.print(tree[i] + " ");
        }
        System.out.println();
    }

    public void init(int[] arr) {
        n = 1;
        while (n < arr.length) {
            n *= 2;
        }
        tree = new int[2 * n - 1];
        lazy = new int[2 * n - 1];
        constructTree(arr, 0, n - 1, 0);
    }

    private int constructTree(int[] arr, int start, int end, int k) {
        if (start == end) {
            if (start < arr.length) {
                tree[k] = arr[start];
            }
            return tree[k];
        }

        int mid = (start + end) / 2;
        return tree[k] = constructTree(arr, start, mid, 2 * k + 1)
                + constructTree(arr, mid + 1, end, 2 * k + 2);
    }

    int findSum(int start, int end) {
        return findSum(start, end, 0, n - 1, 0, 0);
    }

    private int findSum(int start, int end, int rangeStart, int rangeEnd, int k, int lazyValue) {
        if (end < rangeStart || start > rangeEnd || start > end) {
            return 0;
        }

        lazy[k] += lazyValue;
        if (rangeStart >= start && rangeEnd <= end) {
            if (start == end) {
                tree[k] += lazy[k];
                lazy[k] = 0;
            }
            int val = tree[k] + lazy[k] * (end - start + 1);
            System.out.println(
                    "returning from k="
                            + k
                            + " value="
                            + val
                            + " start="
                            + start
                            + " end="
                            + end
                            + " tree="
                            + tree[k]
                            + " lazy="
                            + lazy[k]);
            return val;
        }
        int rangeMid = (rangeStart + rangeEnd) / 2;

        lazyValue = lazy[k];
        tree[k] += lazyValue * (rangeStart - rangeEnd + 1);
        lazy[k] = 0;

        return findSum(start, Math.min(end, rangeMid), rangeStart, rangeMid, 2 * k + 1, lazyValue)
                + findSum(Math.max(start, rangeMid + 1), end, rangeMid + 1, rangeEnd, 2 * k + 2,
                        lazyValue);
    }

    void addXToRange(int start, int end, int x) {
        addXToRange(start, end, 0, n - 1, 0, x);
    }

    private void addXToRange(int start, int end, int rangeStart, int rangeEnd, int k, int x) {
        if (end < rangeStart || start > rangeEnd) {
            return;
        }
        if (rangeStart >= start && rangeEnd <= end) {
            lazy[k] += x;
            if (start == end) {
                tree[k] += lazy[k];
                lazy[k] = 0;
            }
            return;
        }

        x += lazy[k];
        lazy[k] = 0;
        int rangeMid = (rangeStart + rangeEnd) / 2;
        addXToRange(start, end, rangeStart, rangeMid, 2 * k + 1, x);
        addXToRange(start, end, rangeMid + 1, rangeEnd, 2 * k + 2, x);
    }

    @Test
    public void test1() {
        int arr[] = {1, 2, 3, 4};
        init(arr);
        printTree();
        printLazy();
        assertEquals(10, findSum(0, 3));
        assertEquals(5, findSum(1, 2));
        // 2, 3, 3, 4
        addXToRange(0, 1, 1);
        printTree();
        printLazy();
        assertEquals(8, findSum(0, 2));
        printTree();
        printLazy();
        assertEquals(2, findSum(0, 0));
        printTree();
        printLazy();
        // 3, 3, 3, 4
        addXToRange(0, 0, 1);
        printTree();
        printLazy();
    }
}

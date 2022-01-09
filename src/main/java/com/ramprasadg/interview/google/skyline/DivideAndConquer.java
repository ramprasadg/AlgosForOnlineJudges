package com.ramprasadg.interview.google.skyline;

import java.util.*;

//https://discuss.leetcode.com/topic/63656/java-solution-divide-and-conquer-beat-95
public class DivideAndConquer {
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> result = new ArrayList<int[]>();
        if (buildings == null || buildings.length == 0)
            return result;
        return computeSkyline(buildings, 0, buildings.length - 1);
    }

    public static int[] getPoint(int x, int y) {
        int[] temp = { x, y };
        return temp;
    }

    private static List<int[]> computeSkyline(int[][] buildings, int from, int to) {
        List<int[]> result = new ArrayList<int[]>();
        if (from == to) {
            result.add(getPoint(buildings[from][0], buildings[from][2]));
            result.add(getPoint(buildings[from][1], 0));
            return result;
        }
        int mid = from + (to - from) / 2;
        List<int[]> LS1 = computeSkyline(buildings, from, mid);
        List<int[]> LS2 = computeSkyline(buildings, mid + 1, to);
        return merge(LS1, LS2);
    }

    public static List<int[]> merge(List<int[]> SL1, List<int[]> SL2) {
        List<int[]> result = new ArrayList<int[]>();
        int len1 = SL1.size(), len2 = SL2.size();
        int p1 = 0, p2 = 0, H1 = 0, H2 = 0;
        int[] cur = null;
        // A boolean parameter to record whether the cur value is from
        // SL1 or SL2
        boolean fromSL1 = true;
        while (p1 < len1 && p2 < len2) {
            int[] s1 = SL1.get(p1);
            int[] s2 = SL2.get(p2);
            if (s1[0] < s2[0]) {
                fromSL1 = true;
                cur = s1;
                H1 = cur[1];
                ++p1;
            } else if (s1[0] > s2[0]) {
                fromSL1 = false;
                cur = s2;
                H2 = cur[1];
                ++p2;
            } else {
                if (s1[1] > s2[1]) {
                    fromSL1 = true;
                    cur = s1;
                } else {
                    fromSL1 = false;
                    cur = s2;
                }
                H1 = s1[1];
                H2 = s2[1];
                ++p1;
                ++p2;
            }
            int h = 0;
            if (fromSL1)
                h = Math.max(cur[1], H2);
            else
                h = Math.max(cur[1], H1);
            if (result.isEmpty() || h != result.get(result.size() - 1)[1]) {
                result.add(getPoint(cur[0], h));
            }
        }

        while (p1 < len1)
            result.add(SL1.get(p1++));
        while (p2 < len2)
            result.add(SL2.get(p2++));

        return result;
    }
}

package com.ramprasadg.interview.google.skyline;

import java.util.*;

//https://discuss.leetcode.com/topic/65587/easy-java-solution-no-explanation-needed
public class PriorityQueueSweep {
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> res = new ArrayList<int[]>();
        if (buildings.length == 0)
            return res;

        List<int[]> points = new ArrayList<int[]>();
        for (int[] b : buildings) {
            points.add(new int[] { b[0], -b[2] });
            points.add(new int[] { b[1], b[2] });
        }
        Collections.sort(points, new Comparator<int[]>() {
            public int compare(int[] p1, int[] p2) {
                return p1[0] == p2[0] ? p1[1] - p2[1] : p1[0] - p2[0];
            }
        });
        PriorityQueue<Integer> q = new PriorityQueue<Integer>(points.size(), new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                return b - a;
            }
        });
        q.add(0);

        for (int[] p : points) {
            if (p[1] < 0) {
                if (-p[1] > q.peek())
                    res.add(new int[] { p[0], -p[1] });
                q.add(-p[1]);
            } else {
                q.remove(p[1]);
                if (p[1] > q.peek())
                    res.add(new int[] { p[0], q.peek() });
            }
        }
        return res;
    }
}

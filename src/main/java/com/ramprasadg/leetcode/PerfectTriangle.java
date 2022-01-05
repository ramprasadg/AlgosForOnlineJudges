package src.main.java.com.ramprasadg.leetcode;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;

import org.junit.Test;

public class PerfectTriangle {

    @Test
    public void example1() {
        int[][] rectangles = { { 1, 1, 3, 3 }, { 3, 1, 4, 2 }, { 3, 2, 4, 4 }, { 1, 3, 2, 4 }, { 2, 3, 3, 4 } };
        assertTrue(isRectangleCover(rectangles));
    }

    @Test
    public void example2() {
        int[][] rectangles = { { 1, 1, 2, 3 }, { 1, 3, 2, 4 }, { 3, 1, 4, 2 }, { 3, 2, 4, 4 } };
        assertFalse(isRectangleCover(rectangles));
    }

    @Test
    public void example3() {
        int[][] rectangles = { { 1, 1, 3, 3 }, { 3, 1, 4, 2 }, { 1, 3, 2, 4 }, { 3, 2, 4, 4 } };
        assertFalse(isRectangleCover(rectangles));
    }

    @Test
    public void example4() {
        int[][] rectangles = { { 1, 1, 3, 3 }, { 3, 1, 4, 2 }, { 1, 3, 2, 4 }, { 2, 2, 4, 4 } };
        assertFalse(isRectangleCover(rectangles));
    }

    @Test
    public void example5() {
        int[][] rectangles = { { 0, 0, 3, 3 }, { 1, 1, 2, 2 }, { 1, 1, 2, 2 } };
        assertFalse(isRectangleCover(rectangles));
    }

    @Test
    public void example6() {
        int[][] rectangles = { { 0, 0, 1, 1 }, { 0, 2, 1, 3 }, { 1, 1, 2, 2 }, { 2, 0, 3, 1 }, { 2, 2, 3, 3 },
                { 1, 0, 2, 3 }, { 0, 1, 3, 2 } };
        assertFalse(isRectangleCover(rectangles));
    }

    @Test
    public void example7() {
        int[][] rectangles = { { 0, 0, 4, 1 }, { 7, 0, 8, 2 }, { 6, 2, 8, 3 }, { 5, 1, 6, 3 }, { 4, 0, 5, 1 },
                { 6, 0, 7, 2 }, { 4, 2, 5, 3 }, { 2, 1, 4, 3 }, { 0, 1, 2, 2 }, { 0, 2, 2, 3 }, { 4, 1, 5, 2 },
                { 5, 0, 6, 1 } };
        assertFalse(isRectangleCover(rectangles));
    }

    @Test
    public void example8() {
        int[][] rectangles = { { 1, 1, 3, 3 }, { 3, 1, 4, 2 }, { 3, 2, 4, 4 }, { 1, 3, 2, 4 }, { 2, 3, 3, 4 } };
        assertTrue(isRectangleCover(rectangles));
    }

    @Test
    public void example9() {
        int[][] rectangles = { { 0, 0, 4, 1 }, { 7, 0, 8, 2 }, { 6, 2, 8, 3 }, { 5, 1, 6, 3 }, { 4, 0, 5, 1 },
                { 6, 0, 7, 2 }, { 4, 2, 5, 3 }, { 2, 1, 4, 3 }, { 0, 1, 2, 2 }, { 0, 2, 2, 3 }, { 4, 1, 5, 2 },
                { 5, 0, 6, 1 } };
        assertTrue(isRectangleCover(rectangles));
    }

    static class Point {
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object p) {
            if (p instanceof Point) {
                Point that = (Point) p;
                return that.x == this.x && that.y == this.y;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    HashMap<String, Integer> map = new HashMap<String, Integer>();
    public boolean isRectangleCover(int[][] rectangles) {
        if (rectangles.length == 0 || rectangles[0].length == 0) return false;
        int lx = Integer.MAX_VALUE, ly = lx, rx = Integer.MIN_VALUE, ry = rx, sum = 0;
        for (int[] rec : rectangles) {
            lx = Math.min(lx, rec[0]);
            ly = Math.min(ly, rec[1]);
            rx = Math.max(rx, rec[2]);
            ry = Math.max(ry, rec[3]);
            sum += (rec[2] - rec[0]) * (rec[3] - rec[1]);
            //bottom-left
            if (overlap(rec[0] + " " + rec[1], 1)) return false;
            //top-left
            if (overlap(rec[0] + " " + rec[3], 2)) return false;
            //bottom-right
            if (overlap(rec[2] + " " + rec[1], 4)) return false;
            //top-right
            if (overlap(rec[2] + " " + rec[3], 8)) return false;
        }
        int count = 0;
        Iterator<Integer> iter = map.values().iterator();
        while (iter.hasNext()) {
            Integer i = iter.next();
            if (i != 15 && i != 12 && i != 10 && i != 9 && i != 6 && i != 5 && i != 3) count++;
        }
        return count == 4 && sum == (rx - lx) * (ry - ly);
    }
    
    private boolean overlap(String corner, Integer type) {
        Integer temp = map.get(corner);
        if (temp == null) temp = type;
        else if ((temp & type) != 0) return true;
        else temp |= type;
        map.put(corner, temp);
        return false;
    }
}

package src.main.java.com.ramprasadg.interview.google;

import static org.junit.Assert.*;

import java.util.PriorityQueue;

import org.junit.Test;

public class TrappingRainWaterII {
    private static class Cell implements Comparable<Cell> {
        private int row;
        private int col;
        private int value;

        public Cell(int r, int c, int v) {
            this.row = r;
            this.col = c;
            this.value = v;
        }

        @Override
        public int compareTo(Cell other) {
            return value - other.value;
        }
    }

    private int water;
    private boolean[][] visited;

    public int trapRainWater(int[][] heightMap) {
        if (heightMap.length == 0)
            return 0;
        PriorityQueue<Cell> walls = new PriorityQueue<Cell>();
        water = 0;
        visited = new boolean[heightMap.length][heightMap[0].length];
        int rows = heightMap.length, cols = heightMap[0].length;
        // build wall;
        for (int c = 0; c < cols; c++) {
            walls.add(new Cell(0, c, heightMap[0][c]));
            walls.add(new Cell(rows - 1, c, heightMap[rows - 1][c]));
            visited[0][c] = true;
            visited[rows - 1][c] = true;
        }
        for (int r = 1; r < rows - 1; r++) {
            walls.add(new Cell(r, 0, heightMap[r][0]));
            walls.add(new Cell(r, cols - 1, heightMap[r][cols - 1]));
            visited[r][0] = true;
            visited[r][cols - 1] = true;
        }
        // end build wall;
        while (walls.size() > 0) {
            Cell min = walls.poll();
            System.out.println("min row=" + min.row + " col=" + min.col + " val=" + min.value);
            fill(heightMap, min.row + 1, min.col, walls, min.value);
            fill(heightMap, min.row - 1, min.col, walls, min.value);
            fill(heightMap, min.row, min.col + 1, walls, min.value);
            fill(heightMap, min.row, min.col - 1, walls, min.value);
        }
        return water;
    }

    private void fill(int[][] height, int row, int col, PriorityQueue<Cell> walls, int min) {
        if (row < 0 || col < 0)
            return;
        else if (row >= height.length || col >= height[0].length)
            return;
        else if (visited[row][col])
            return;
        else if (height[row][col] >= min) {
            walls.add(new Cell(row, col, height[row][col]));
            visited[row][col] = true;
            return;
        } else {
            System.out.println(row + ", " + col + " height = " + height[row][col] + ", bar = " + min);
            water += min - height[row][col];
            visited[row][col] = true;
            print(visited);
            fill(height, row + 1, col, walls, min);
            fill(height, row - 1, col, walls, min);
            fill(height, row, col + 1, walls, min);
            fill(height, row, col - 1, walls, min);
        }
    }

    private void print(boolean[][] visited) {
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[0].length; j++) {
                System.out.print(visited[i][j] ? 1 : 0);
            }
            System.out.println();
        }
    }

    @Test
    public void test1() {
        int[][] arr = { 
                { 1, 4, 3, 1, 3, 2 }, 
                { 3, 2, 1, 3, 2, 4 }, 
                { 2, 3, 3, 2, 3, 1 }
                };
        assertEquals(4, trapRainWater(arr));
    }

    @Test
    public void test2() {
        int[][] arr = { 
                { 1, 4, 3, 1, 4, 2 }, 
                { 3, 2, 1, 4, 2, 4 }, 
                { 2, 3, 3, 2, 4, 1 } 
                };
        assertEquals(5, trapRainWater(arr));
    }
}

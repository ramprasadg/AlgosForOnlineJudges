package interview;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class c3iot1 {

    static int[][] m = null;
    static boolean[][] visited = null;
    static int cheeseVisited = 0;
    static int rowLen = 0;
    static int colLen = 0;
    static Point jerry = null;
    static int ans = 0;
    static int pathLength = 0;

    static int[][][][] distanceMatrix = null;
    private static Point tom;
    private static Set<Point> cheesePoints;
    private static Set<Point> visitedCheesePoints;

    static boolean isValid(int i, int j) {
        if (i < 0 || i >= rowLen) {
            return false;
        }
        if (j < 0 || colLen <= 0 || j >= m[0].length) {
            return false;
        }
        if (m[i][j] == 1) {
            return false;
        }
        return true;
    }

    static boolean isCheese(int i, int j) {
        if (isValid(i, j)) {
            return m[i][j] == 2;
        } else {
            return false;
        }
    }

    static int minMoves(int[][] maze, int x, int y) {
        m = maze;
        rowLen = m.length;
        colLen = m[0].length;

        visited = new boolean[rowLen][colLen];
        jerry = new Point(x, y);
        ans = Integer.MAX_VALUE;

        tom = new Point(0, 0);
        distanceMatrix = new int[rowLen][colLen][rowLen][colLen];
        bfsVisit(tom);

        cheesePoints = new HashSet<Point>();
        visitedCheesePoints = new HashSet<Point>();

        if (distanceMatrix[tom.x][tom.y][jerry.x][jerry.y] == 0) {
            return -1;
        }

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (isCheese(i, j)) {
                    cheesePoints.add(new Point(i, j));
                    if (distanceMatrix[tom.x][tom.y][i][j] == 0) {
                        return -1;
                    }
                }
            }
        }

        for (Point cheesePoint : cheesePoints) {
            bfsVisit(cheesePoint);
        }

        tsp(tom, cheesePoints, visitedCheesePoints);

        return ans;
    }

    private static void tsp(Point point, Set<Point> remaining, Set<Point> visited) {
        if (remaining.contains(point)) {
            remaining.remove(point);
            visited.add(point);
        }

        System.out.println(String.format("%s distanceToJerry=%d remainingCheese=%d", point,
                distanceMatrix[point.x][point.y][jerry.x][jerry.y], remaining.size()));

        if (remaining.isEmpty()) {
            System.out.println("foundAPath");
            int newPathLength = pathLength + distanceMatrix[point.x][point.y][jerry.x][jerry.y];
            if (newPathLength < ans) {
                System.out.println("foundAShorterPath");
                ans = newPathLength;
            }
        }

        List<Point> neighbours = new LinkedList(remaining);
        for (Point r : neighbours) {
            pathLength += distanceMatrix[point.x][point.y][r.x][r.y];
            if (pathLength < ans) {
                tsp(r, remaining, visited);
            }
            pathLength -= distanceMatrix[point.x][point.y][r.x][r.y];
        }

        if (visited.contains(point)) {
            remaining.add(point);
            visited.remove(point);
        }
    }

    static class Point {
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return String.format("Point(%d, %d)", x, y);
        }
    }

    public static int[][][][] bfsVisit(Point start) {
        int[] x = { 0, 0, 1, -1 };// This represent 4 directions right, left,
        int[] y = { 1, -1, 0, 0 };// down , up
        LinkedList<Point> q = new LinkedList<Point>();
        q.add(start);

        while (!q.isEmpty()) {
            Point p = q.removeFirst();
            for (int i = 0; i < 4; i++) {
                int a = p.x + x[i];
                int b = p.y + y[i];
                if (!(a == start.x && b == start.y) && isValid(a, b) && distanceMatrix[start.x][start.y][a][b] == 0) {
                    distanceMatrix[start.x][start.y][a][b] = 1 + distanceMatrix[start.x][start.y][p.x][p.y];
                    q.add(new Point(a, b));
                }
            }
        }

        return distanceMatrix;
    }

    public static void main(String args[]) {
        int[][] maze1 = { { 0, 2, 0 }, { 0, 0, 1 }, { 1, 1, 1 } };
        System.out.println("ans1: " + minMoves(maze1, 1, 1));

        int[][] maze2 = { { 0, 1, 0 }, { 1, 0, 1 }, { 0, 2, 2 } };
        System.out.println("ans2: " + minMoves(maze2, 1, 1));

        int[][] maze3 = { { 0, 2, 0 }, { 1, 1, 2 }, { 1, 0, 0 } };
        System.out.println("ans3: " + minMoves(maze3, 2, 1));

        int[][] maze4 = { { 0, 0, 2 }, { 0, 0, 0 }, { 0, 2, 0 } };
        System.out.println("ans4: " + minMoves(maze4, 2, 2));
    }
}

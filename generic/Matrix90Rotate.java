package generic;

public class Matrix90Rotate {
    // static int a[][] = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, {
    // 13, 14, 15, 16 } };
    static int a[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };

    static int n = a.length;

    static void print() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(String.format("%2d", a[i][j]));
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        print();
        System.out.println();
        rotate();
        print();
    }

    private static void rotate() {
        // for (int level = 0; level < n / 2; level++) {
        // int startingRow = level;
        // int startingCol = level;
        // int matrixSize = n - 2 * level;
        // for (int i = 0; i < matrixSize - 1; i++) {
        // int topL = a[startingRow][startingCol + i];
        // int topR = a[startingRow + i][startingCol + matrixSize - 1];
        // int bottomR = a[startingRow + matrixSize - 1][startingCol +
        // matrixSize - 1 - i];
        // int bottomL = a[startingRow + matrixSize - 1 - i][startingCol];
        //
        // a[startingRow][startingCol + i] = bottomL;
        // a[startingRow + i][startingCol + matrixSize - 1] = topL;
        // a[startingRow + matrixSize - 1][startingCol + matrixSize - 1 - i] =
        // topR;
        // a[startingRow + matrixSize - 1 - i][startingCol] = bottomR;
        // }
        // }

        for (int level = 0; level < n / 2; level++) {
            int startingRow = level;
            int startingColumn = level;
            int endingRow = n - level;
            int endingColumn = n - level;
            
            
        }
    }
}

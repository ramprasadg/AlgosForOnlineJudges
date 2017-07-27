package utils;

public class Utils {
    static <T> void printArray(T[] arr) {
        for (T a : arr) {
            System.out.print(a + ", ");
        }
        System.out.println();
    }

    public static void printArray(int[] arr) {
        for (int a : arr) {
            System.out.print(a + ", ");
        }
        System.out.println();
    }

    public static void print2DArray(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

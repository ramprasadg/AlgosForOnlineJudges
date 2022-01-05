package src.main.java.com.ramprasadg.generic;

import src.main.java.com.ramprasadg.utils.Utils;

public class ArrayRotation {
    public static void main(String args[]) {
        int arr[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        rotateReverse(arr, 3);
        Utils.printArray(arr);
        int arr1[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        rotateGcd(arr1, 3);
        Utils.printArray(arr1);
    }

    private static void rotateGcd(int[] arr, int rotate) {
        rotate = rotate % arr.length;
        int gcd = gcd(arr.length, rotate);
        for (int i = 0; i < gcd; i++) {
            int j = i;
            int temp = arr[j];
            while(true) {
                int k = j + rotate;
                while(k >= arr.length) {
                    k = k - arr.length;
                }
                if(k == i) {
                    break;
                }
                arr[j] = arr[k];
                j = k;
            }
            arr[j] = temp;
        }
    }

    private static int gcd(int a, int b) {
        if(b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }

    public static void rotateReverse(int[] arr, int rotate) {
        rotate = rotate % arr.length;
        reverse(arr, 0, rotate - 1);
        reverse(arr, rotate, arr.length - 1);
        reverse(arr, 0, arr.length - 1);
    }

    private static void reverse(int[] arr, int start, int end) {
        for (int i = start, j = end; i < j; i++, j--) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}

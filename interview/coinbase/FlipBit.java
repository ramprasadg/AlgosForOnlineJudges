package interview.coinbase;

import java.util.Arrays;
import java.util.Scanner;

public class FlipBit {
    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = s.nextInt();
        }
        System.out.println(find(arr));
    }

    public static int find(int[] arr) {
        int res = 0;
        int sz = arr.length;
        int[] left = new int[sz];
        for (int i = 0; i < sz; i++) {
            left[i] = (arr[i] == 1 ? 1 : 0) + (i == 0 ? 0 : left[i - 1]);
        }
        boolean all_1 = true;
        for (int i = 0; i < sz; i++) {
            if (arr[i] == 0)
                all_1 = false;
        }
        if (all_1)
            return sz - 1;
        res = left[sz - 1] + 1;
        int[] temp = new int[sz];
        Arrays.fill(temp, Integer.MAX_VALUE);
        for (int i = 1; i < sz; i++)
            temp[i] = Math.min(temp[i - 1], i - 2 * left[i - 1]);
        for (int j = 1; j < sz; j++) {
            int val = j + 1 - left[j] + (left[sz - 1] - left[j]);
            val = Math.max(val, j - 2 * left[j] - temp[j] + left[sz - 1] + 1);
            res = Math.max(res, val);
        }
        return res;
    }

    static int numberOfRoutes(int[][] a, int m, int n) {
        int mod = 1000000007;
        int T[][] = new int[m][n];
        for (int i = 0; i < m; i++) {
            if (a[i][0] == 0 || ((i > 0) && T[i - 1][0] == 0)) {
                T[i][0] = 0;
            } else {
                T[i][0] = 1;
            }
        }

        for (int j = 0; j < n; j++) {
            if (a[0][j] == 0 || ((j > 0) && T[0][j-1] == 0)) {
                T[0][j] = 0;
            } else {
                T[0][j] = 1;
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (a[i][j] != 0) {
                    T[i][j] = (T[i - 1][j] + T[i][j - 1]) % mod;
                }

            }
        }
        return T[m - 1][n - 1];
    }
}

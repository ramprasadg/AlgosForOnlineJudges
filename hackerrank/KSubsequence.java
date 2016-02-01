package hackerrank;

public class KSubsequence {

    int[][] sum;

    public int find(int arr[], int k) {
        int cnt = 0;
        sum = new int[arr.length][arr.length];

        for (int i = 0; i < arr.length; i++) {
            sum[0][i] = arr[i];
            if (sum[0][i] % k == 0)
                cnt++;
        }

        for (int length = 1; length < arr.length; length++) {
            for (int i = length; i < arr.length; i++) {
                sum[length][i] = sum[length - 1][i] + arr[i - length];
                if (sum[length][i] % k == 0)
                    cnt++;
            }
        }

        return cnt;
    }

    public static void main(String args[]) {
        int arr[] = { 1, 2, 3, 4, 1 };
        KSubsequence ks = new KSubsequence();
        System.out.println(ks.find(arr, 3));
    }
}

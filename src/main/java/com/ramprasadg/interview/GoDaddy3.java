package com.ramprasadg.interview;

public class GoDaddy3 {
    static void arrangeCoins(long coins[]) {
        for (long c : coins) {
            long i = (int) Math.sqrt(c);
            for (; i * (i + 1) / 2 <= c; i++) {
            }
            System.out.println(i - 1);
        }
    }

    public static void main(String args[]) {
        long[] arr = {2, 5, 8, 3};
        arrangeCoins(arr);
    }
}

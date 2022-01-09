package com.ramprasadg.interview.coinbase;

import java.util.Scanner;

public class InversedNumber {
    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        
        int result = 0;
        int lastSetBit = 0;

        for (int i = 0; i < 32; i++) {
            if ((n & (1 << i)) != 0) {
                lastSetBit = i;
            }
        }

        for (int i = 0; i <= lastSetBit; i++) {
            if ((n & (1 << i)) == 0) {
                result += 1 << i;
            }
        }

        System.out.println(result);
    }
}

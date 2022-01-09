package com.ramprasadg.hackerrank;

import java.io.*;

/**
 * https://www.hackerrank.com/challenges/lego-blocks/editorial
 * 
 * You have 4 types of lego blocks, of sizes given as (1 x 1 x 1), (1 x 1 x 2), (1 x 1 x 3), and (1 x 1 x 4). Assume that you have an infinite number of blocks of each type.
 * Using these blocks, you want to make a wall of height N and width M. The wall should not have any holes in it. The wall you build should be one solid structure. A solid structure can be interpreted in one of the following ways: 
 * (1)It should not be possible to separate the wall along any vertical line without cutting any lego block used to build the wall. 
 * (2)You cannot make a vertical cut from top to bottom without cutting one or more lego blocks.
 * 
 * The blocks can only be placed horizontally. In how many ways can the wall be built?
 * 
 * Input Format
 * The first line contains the number of test cases T. T test cases follow. Each case contains two integers N and M.
 * Constraints
 * 1 <= T <= 100 
 * 1 <= N,M <= 1000
 * 
 * Output Format
 * Output T lines, one for each test case containing the number of ways to build the wall.
 * As the numbers can be very large, output the result modulo 1000000007.
 */
public class LegoBlocks {
    
    final static int MOD = 1000000007;
    final static int MAX_COL = 1000;
    static long[] SingleRow = new long[MAX_COL+1];
    static long[] MultiRowNoConstrain = new long[MAX_COL+1];
    static long[] MultiRowWithConstrain = new long[MAX_COL+1];
        
    private static long singleRow(int i) {
        if(i<0) return 0;
        if(i==0) return 1;
        else return SingleRow[i];
    }
    
    private static long pow(long x, int p) {
        if(p==0) return 1;
        if(p==1) return x;
        long power = x;
        for(int i=2; i<=p; i++) {
            power = (power*x); 
            power = power % MOD;
        }
        return power;
    }
    
    public static void main(String[] args) throws Exception {
        int T, N, M;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        T = Integer.parseInt(line);

        for(int i=1; i<=MAX_COL; i++) {
            //Put 1 lego and solve rest.
            //Put 2 lego and solve rest. 
            //Put 3 lego and solve rest.
            //Put 4 lego and solve rest.
            SingleRow[i] = singleRow(i-1) + singleRow(i-2) + singleRow(i-3) + singleRow(i-4);
            SingleRow[i] = SingleRow[i] % MOD;
        }
        
        while(T-- > 0) {
            line = br.readLine();
            String[] inputStr = line.split(" ");
            N = Integer.parseInt(inputStr[0]);
            M = Integer.parseInt(inputStr[1]);
            
            for (int i=0; i<=M; i++) {
                MultiRowNoConstrain[i] = pow(singleRow(i), N);
            }

            MultiRowWithConstrain[0] = 1;
            MultiRowWithConstrain[1] = 1;
            for (int i=2; i<=M; i++) {
                long tmp = 0;
                for (int j=1; j<i; j++) {
                    tmp += (MultiRowWithConstrain[j] * MultiRowNoConstrain[i-j]) % MOD;
                    tmp = tmp % MOD;
                }
                MultiRowWithConstrain[i] = MultiRowNoConstrain[i] - tmp;
                while(MultiRowWithConstrain[i] < 0) {
                    MultiRowWithConstrain[i] += MOD;
                }
            }
            System.out.println(MultiRowWithConstrain[M]);
        }
    }
}

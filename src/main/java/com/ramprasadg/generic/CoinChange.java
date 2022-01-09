package com.ramprasadg.generic;

/*
 * http://www.geeksforgeeks.org/dynamic-programming-set-7-coin-change/
 * 
 * given a set of denominations {d1, d2, ... dn} and target amount n, how many ways can n be formed
 */

public class CoinChange {
    int n;
    int[] denominations;
    int[][] numberOfWays;

    CoinChange(int n, int[] d) {
        this.n = n;
        this.denominations = d;
        numberOfWays = new int[n + 1][denominations.length];
        
        //there is only one unique way of making 0
        for (int i = 0; i < denominations.length; i++)
            numberOfWays[0][i] = 1;

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < denominations.length; j++) {
                if (denominations[j] <= i) {
                    int numberOfWaysOfFormingIUsingDj = numberOfWays[i - denominations[j]][j];
                    int numberOfWaysOfFormingIWithoutUsingDj = 0;
                    if (j > 0)
                        numberOfWaysOfFormingIWithoutUsingDj = numberOfWays[i][j - 1];

                    numberOfWays[i][j] = numberOfWays[i][j] + numberOfWaysOfFormingIUsingDj
                            + numberOfWaysOfFormingIWithoutUsingDj;
                }
            }
        }
    }

    int getAnswer() {
        for (int i = 0; i < numberOfWays.length; i++) {
            for (int j = 0; j < numberOfWays[i].length; j++) {
                System.out.print(numberOfWays[i][j] + " ");
            }
            System.out.println();
        }

        return 0;
    }

    public static void main(String[] args) {
        int[] d = { 1, 2, 4 };
        CoinChange c = new CoinChange(4, d);
        System.out.println(c.getAnswer());
    }

}

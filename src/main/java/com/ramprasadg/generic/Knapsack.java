package com.ramprasadg.generic;

import java.util.Scanner;

public class Knapsack {

    private static class Item {
        public int value;
        public int weight;
    }

    public static void main(String args[]) throws Exception {
        Scanner in = new Scanner(System.in);
        final int W = 99;
        final int I = 5;
        Item[] item = new Item[I];
        item[0] = new Item();
        item[0].value = 11;
        item[0].weight = 8;
        item[1] = new Item();
        item[1].value = 1;
        item[1].weight = 9;
        item[2] = new Item();
        item[2].value = 1;
        item[2].weight = 1;
        item[3] = new Item();
        item[3].value = 20;
        item[3].weight = 90;
        item[4] = new Item();
        item[4].value = 1;
        item[4].weight = 10;

        int[][] knapSack = new int[I + 1][W + 1];

        for (int i = 0; i <= I; i++) {
            for (int w = 0; w <= W; w++) {
                if (i == 0 || w == 0)
                    knapSack[i][w] = 0;
                else if (item[i - 1].weight > w)
                    knapSack[i][w] = knapSack[i - 1][w];
                else
                    knapSack[i][w] = Math.max(
                            knapSack[i - 1][w],
                            knapSack[i - 1][w - item[i - 1].weight] + item[i - 1].value);
            }
        }

        System.out.print(knapSack[I][W]);
    }
}

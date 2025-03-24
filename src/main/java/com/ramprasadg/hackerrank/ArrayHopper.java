package com.ramprasadg.hackerrank;

import java.util.*;

public class ArrayHopper {

    public static void findMinFlight(ArrayList<Integer> input) {

        if (input.isEmpty() || input.get(0) == 0) {
            System.out.println("failure\n");
            return;
        }

        int n = input.size() + 1;

        int[] minFlights = new int[n];
        int[] parent = new int[n];

        minFlights[0] = 0;

        for (int x = 1; x < n; x++) {
            minFlights[x] = Integer.MAX_VALUE;

            for (int y = 0; y < x; y++) {
                if (x <= y + input.get(y) && minFlights[y] != Integer.MAX_VALUE) {
                    if (minFlights[x] > minFlights[y] + 1) {
                        minFlights[x] = minFlights[y] + 1;
                        parent[x] = y;
                    }
                    break;
                }
            }
        }

        if (minFlights[n - 1] == Integer.MAX_VALUE) {
            System.out.println("failure\n");
        } else {
            ArrayList<Integer> res = new ArrayList<Integer>();

            int it = parent[n - 1];
            res.add(it);
            while (it != 0) {
                it = parent[it];
                res.add(it);
            }

            for (int i = res.size() - 1; i >= 0; i--) {
                System.out.print(res.get(i) + ", ");
            }
            System.out.print("out\n");
        }
    }

    public static ArrayList<Integer> readInput() {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> input = new ArrayList<Integer>();

        while (sc.hasNextInt()) {
            input.add(sc.nextInt());
        }

        return input;
    }

    public static void main(String args[]) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */

        // ArrayList<Integer> input = readInput();
        ArrayList<Integer> input = new ArrayList<Integer>();
        input.add(0);
        input.add(0);
        input.add(0);
        input.add(0);

        findMinFlight(input);
    }
}

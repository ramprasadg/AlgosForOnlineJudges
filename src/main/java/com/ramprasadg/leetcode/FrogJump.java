package com.ramprasadg.leetcode;

import java.util.*;

public class FrogJump {
    public static boolean canCross(int[] stones) {
        if (stones.length < 1) {
            return true;
        }

        if (stones[0] != 0) {
            return false;
        }

        if (stones.length < 2) {
            return true;
        }

        if (stones[1] != 1) {
            return false;
        }

        if (stones.length < 3) {
            return true;
        }

        HashMap<Integer, Set<Integer>> pathToStone = new HashMap<Integer, Set<Integer>>();

        for (int i = 0; i < stones.length; i++) {
            pathToStone.put(stones[i], new HashSet<Integer>());
        }

        int goal = stones[stones.length - 1];

        Set<Integer> pathToOne = pathToStone.get(1);
        pathToOne.add(1);

        for (int stone : stones) {
            Set<Integer> pathLengths = pathToStone.get(stone);
            for (int pathLength : pathLengths) {
                System.out.println("Stone= " + stone + " pathLength=" + pathLength);
                for (int i = -1; i <= 1; i++) {
                    int newStone = stone + pathLength + i;
                    if (newStone <= stone) {
                        continue;
                    }
                    if (newStone == goal) {
                        return true;
                    }
                    if (pathToStone.containsKey(newStone)) {
                        pathToStone.get(newStone).add(pathLength + i);
                    }
                }
            }
        }
        return false;
    }

    public static void main(String args[]) {
        int[] stones = {0, 1, 3, 4, 5, 7, 9, 10, 12};
        System.out.println(canCross(stones));
    }
}

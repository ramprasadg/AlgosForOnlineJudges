package com.ramprasadg.leetcode;

import java.util.*;

public class BinaryWatch {

    public List<String> readBinaryWatch(int num) {
        List<String> retVal = new LinkedList<String>();
        for (int bitsInHour = 0; bitsInHour < 4; bitsInHour++) {
            int bitsInMinutes = num - bitsInHour;
            if (bitsInMinutes > 5 || bitsInMinutes < 0)
                continue;

            Set<Integer> hours = getNums(12, 4, bitsInHour);
            Set<Integer> minutes = getNums(60, 6, bitsInMinutes);
            for (int h : hours) {
                for (int m : minutes) {
                    retVal.add(String.format("%d:%02d", h, m));
                }
            }
        }
        return retVal;
    }

    public Set<Integer> getNums(int limit, int bits, int bitsToSet) {
        Set<Integer> nums = new HashSet<Integer>();
        nums.add(0);
        Set<Integer> retVal = getNums(limit, bits, bitsToSet, nums);
        return retVal;
    }

    public Set<Integer> getNums(int limit, int bits, int bitsToSet, Set<Integer> nums) {
        if (bits == 0) {
            if (bitsToSet != 0) {
                return new HashSet<Integer>();
            } else {
                return nums;
            }
        }

        Set<Integer> numSetBit = new HashSet<Integer>();
        for (int n : nums) {
            if (n * 2 + 1 < limit)
                numSetBit.add(n * 2 + 1);
        }
        Set<Integer> numDontSetBit = new HashSet<Integer>();
        for (int n : nums) {
            if (n * 2 < limit)
                numDontSetBit.add(n * 2);
        }

        Set<Integer> retVal = getNums(limit, bits - 1, bitsToSet - 1, numSetBit);
        retVal.addAll(getNums(limit, bits - 1, bitsToSet, numDontSetBit));
        return retVal;
    }

    public static int findNthDigit(int n) {
        long x = 9;
        int numberOfDigits = 1;
        long num = 1;
        while (n > x * numberOfDigits) {
            n -= x * numberOfDigits;
            numberOfDigits++;
            x *= 10;
            num *= 10;
        }
        n--;
        num = num + n / numberOfDigits;
        return Long.toString(num).charAt(n % numberOfDigits) - '0';
    }

    public static void main(String args[]) {
        // List<String> ans = new BinaryWatch().readBinaryWatch(1);
        // for(String time: ans) {
        // System.out.println(time);
        // }

        System.out.println(findNthDigit(9));
    }
}

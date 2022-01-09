package com.ramprasadg.interview;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class seq1 {
    static int[] sortIntersect(int[] f, int[] m) {
        Arrays.sort(f);
        Arrays.sort(m);

        List<Integer> outList = new ArrayList<Integer>();

        int i = f.length - 1;
        int j = m.length - 1;
        while (i >= 0 && j >= 0) {
            if (f[i] == m[j]) {
                outList.add(f[i]);
                i--;
                j--;
            } else if (f[i] > m[j]) {
                i--;
            } else {
                j--;
            }
        }

        int[] array = new int[outList.size()];
        i = 0;
        for (int x : outList) {
            array[i++] = x;
        }
        return array;
    }

    public static void main(String args[]) {
        int[] f = { 2, 3, 1, 1 };
        int[] m = { 4, 7, 6 };
        int[] output = sortIntersect(f, m);
        System.out.println("result");
        for (int o : output) {
            System.out.println(o);
        }
    }
}

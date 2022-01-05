package src.main.java.com.ramprasadg.interview;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.Arrays;

import org.junit.Test;

public class LexicographicOrderRank {

    static final long M = 1000000007;

    static public long factorial(int n) {
        long factorial = 1;
        for (int i = 1; i <= n; i++) {
            factorial = mod((factorial * i));
        }
        return factorial;
    }

    static public long mod(long a) {
        a = a % M;
        if (a < 0) {
            a += M;
        }
        return a;
    }

    static public void print(char[] ch) {
        for (int i = 0; i < ch.length; i++) {
            System.out.print(ch[i]);
        }
        System.out.println();
    }

    public long solve(String s) {
        int n = s.length();
        long rank = 0;

        char[] inputString = s.toCharArray();
        int remainingLength = n - 1;

        int[] charFrequency = new int[26];
        for (int i = 0; i < n; i++)
            charFrequency[inputString[i] - 'a']++;

        char[] sortedString = s.toCharArray();
        Arrays.sort(sortedString);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("input = ");
                print(inputString);
                System.out.print("sorted= ");
                print(sortedString);
                System.out.println("i=" + i + " j=" + j + " inputString[i]= " + inputString[i] + " sortedString[j]="
                        + sortedString[j] + " rank=" + rank + " remainingLength=" + remainingLength);

                if (sortedString[j] == '-')
                    continue;

                if (inputString[i] == sortedString[j]) {
                    charFrequency[inputString[i] - 'a']--;
                    sortedString[j] = '-';
                    remainingLength--;
                    break;
                }

                long denominator = 1;
                for (int k = 0; k < 26; k++) {
                    if (k == (sortedString[j] - 'a'))
                        denominator = denominator * factorial(charFrequency[k] - 1);
                    else
                        denominator = denominator * factorial(charFrequency[k]);
                    denominator = mod(denominator);
                }

                rank += divide(factorial(remainingLength), denominator);
                rank = mod(rank);

                while (j < n - 1 && sortedString[j] == sortedString[j + 1])
                    j++;
            }
        }

        return rank;
    }

    private long divide(long x, long y) {
        return mod(x * inverse(y));
    }

    long inverse(long a) {
        long t = 0;
        long r = M;
        long newT = 1;
        long newR = a;

        while (newR != 0) {
            long quotient = r / newR;
            long tempT = newT;
            newT = t - quotient * newT;
            t = tempT;

            long tempR = newR;
            newR = r - quotient * newR;
            r = tempR;
        }

        if (r > 1) {
            throw new RuntimeException("Not invertible " + a);
        }
        t = mod(t);

        return t;
    }

    @Test
    public void test1() {
        String s = "axaelixedhtshsixbuzouqtjrkpyafthezfuehcovcqlbvmkbrwxhzrxymricmehktxepyxomxcx";
        assertEquals(352781740, solve(s));
    }

    @Test
    public void test2() {
        String s = "caabbc";
        assertEquals(60, solve(s));
    }

    @Test
    public void test3() {
        String s = "aab";
        assertEquals(0, solve(s));
    }

    @Test
    public void test4() {
        String s = "aba";
        assertEquals(1, solve(s));
    }

    @Test
    public void test5() {
        String s = "abba";
        assertEquals(2, solve(s));
    }

    @Test
    public void test6() {
        String s = "aaaa";
        assertEquals(solve(s), 0);
    }

    @Test
    public void test7() {
        String s = "baaa";
        assertEquals(solve(s), 3);
    }

    @Test
    public void modTest1() {
        long a = 1;
        assertEquals(BigInteger.valueOf(a).modInverse(BigInteger.valueOf(M)).intValue(), inverse(a));
    }

    @Test
    public void modTest2() {
        long a = M + 1;
        assertEquals(BigInteger.valueOf(a).modInverse(BigInteger.valueOf(M)).intValue(), inverse(a));
    }
}
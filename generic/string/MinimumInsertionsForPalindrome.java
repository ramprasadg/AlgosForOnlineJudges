package generic.string;

import static org.junit.Assert.*;

import org.junit.Test;

import utils.Utils;

//http://www.geeksforgeeks.org/dynamic-programming-set-28-minimum-insertions-to-form-a-palindrome/
public class MinimumInsertionsForPalindrome {
    int minimumInsertions1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        if (s.length() == 1) {
            return 0;
        }

        char firstChar = s.charAt(0);
        char lastChar = s.charAt(s.length() - 1);

        if (firstChar == lastChar) {
            return minimumInsertions1(s.substring(1, s.length() - 1));
        } else {
            return Math.min(minimumInsertions1(s.substring(1, s.length())),
                    minimumInsertions1(s.substring(0, s.length() - 1))) + 1;
        }
    }

    int minimumInsertions(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int arr[][] = new int[s.length()][s.length() + 1];
        char ch[][] = new char[s.length()][s.length() + 1];

        for (int length = 2; length <= s.length(); length++) {
            for (int start = 0; start + length <= s.length(); start++) {
                char firstChar = s.charAt(start);
                char lastChar = s.charAt(start + length - 1);

                System.out.println("length=" + length + " firstChar=" + firstChar + " lastChar=" + lastChar);
                if (firstChar != lastChar) {
                    int insertLastChar = arr[start][length - 1];
                    int insertFirstChar = Integer.MAX_VALUE;
                    if (start + 1 < s.length()) {
                        insertFirstChar = arr[start + 1][length - 1];
                    }

                    if (insertLastChar < insertFirstChar) {
                        arr[start][length] = 1 + insertLastChar;
                    } else {
                        arr[start][length] = 1 + insertFirstChar;
                    }

                } else {
                    if (start + 1 < s.length()) {
                        arr[start][length] = arr[start + 1][length - 2];
                    }
                }
            }
        }

        Utils.print2DArray(arr);

        int i = s.length() - 2;
        int j = s.length();
        while (i > 0 || j - 2 > 0) {
            if (arr[i][j] == arr[i + 1][j - 2]) {
                i--;
                j -= 2;
            } else {
                if (arr[i][j] - 1 == arr[i + 1][j - 1]) {
                    System.out.println("add: " + s.charAt(i) + " at: " + (i + j));
                } else {
                    System.out.println("add: " + s.charAt(i + j) + " at: " + (i));
                }
                i--;
                j--;
            }
        }

        return arr[0][s.length()];
    }

    @Test
    public void test1() {
        assertEquals(0, minimumInsertions(""));
    }

    @Test
    public void test2() {
        assertEquals(0, minimumInsertions("a"));
    }

    @Test
    public void test3() {
        assertEquals(0, minimumInsertions("aa"));
    }

    @Test
    public void test4() {
        assertEquals(0, minimumInsertions("aba"));
    }

    @Test
    public void test5() {
        assertEquals(1, minimumInsertions("ab"));
    }

    @Test
    public void test6() {
        assertEquals(2, minimumInsertions("aabb"));
    }
}

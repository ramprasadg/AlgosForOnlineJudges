package src.main.java.com.ramprasadg.leetcode;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

//https://leetcode.com/problems/reconstruct-original-digits-from-english/
public class ReconstructOriginalDigitsFromEnglish {
    String[] num;

    public void constructNumberMap() {
        num = new String[10];
        num[0] = "zero";
        num[1] = "one";
        num[2] = "two";
        num[3] = "three";
        num[4] = "four";
        num[5] = "five";
        num[6] = "six";
        num[7] = "seven";
        num[8] = "eight";
        num[9] = "nine";
    }

    public void populateHashMap(HashMap<Character, Integer> hm, String s, int start, int end) {
        for (int i = start; i < end; i++) {
            char a = s.charAt(i);
            Integer b = hm.get(a);
            if (b == null) {
                hm.put(a, 1);
            } else {
                hm.put(a, b + 1);
            }
        }
    }

    public String originalDigits(String s) {
        HashMap<Integer, Integer> res = new HashMap<Integer, Integer>();
        for (int i = 0; i < 10; i++) {
            res.put(i, 0);
        }

        constructNumberMap();
        HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
        populateHashMap(hm, s, 0, s.length());
        
        int k = 0;
        while (hm.size() > 0 && k++ < 2) {
            for (int i = 0; i < num.length; i++) {
                String num1 = num[i];
                int j = 0;
                for (; j < num1.length(); j++) {
                    char a = num1.charAt(j);
                    Integer b = hm.get(a);
                    
                    if (b == null || b <= 0) {
                        if(j > 0) {
                            //System.out.println("adding part of" + num1);
                            populateHashMap(hm, num1, 0, j + 1);
                        }
                        break;
                    } else {
                        if (1 == b) {
                            //System.out.println("removing " + a);
                            hm.remove(a);
                            //System.out.println(hm.size());
                        } else {
                            //System.out.println("reducing " + a + " " + (b-1));
                            hm.put(a, b - 1);
                        }
                    }
                }
                if (j == num1.length()) {
                    res.put(i, res.get(i) + 1);
                }
            }
        }

        StringBuilder ret = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            for(int j = 0; j<res.get(i); j++) {
                ret.append(String.valueOf(i));
            }
        }

        return ret.toString();

    }
    
    @Test
    public void test1() {
        assertEquals(originalDigits("owoztneoer"), "012");
    }
    
    @Test
    public void test2() {
        assertEquals(originalDigits("owoztneoerzero"), "0012");
    }
}

package com.ramprasadg.interview;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Test;

public class Twittr2 {
    public static String maskEmail(String inputEmail) {

        String email = inputEmail.trim();

        StringBuilder sb = new StringBuilder();
        boolean flag = false;

        for (int i = 0; i < email.length(); i++) {
            if (flag) {
                sb.append(email.charAt(i));
            } else if (i == 0) {
                sb.append(email.charAt(0));
            }
            if (i + 1 < email.length() && email.charAt(i + 1) == '@') {
                sb.append("*****");
                sb.append(email.charAt(i));
                flag = true;
            }
        }

        return sb.toString();
    }

    public static String maskPhone(String phone) {
        StringBuilder sb = new StringBuilder();
        for (int i = phone.length() - 1; i >= 0; i--) {
            if (Character.isDigit(phone.charAt(i))) {
                if (sb.length() < 4) {
                    sb.append(phone.charAt(i));
                } else {
                    sb.append("*");
                }
            } else if (phone.charAt(i) == '+') {
                sb.append("+");
            }

            if (sb.length() == 4 || sb.length() == 8 || sb.length() == 12) {
                sb.append("-");
            }
        }

        if (sb.charAt(sb.length() - 1) == '-') {
            sb.deleteCharAt(sb.length() - 1);
        }
        
        return  sb.reverse().toString();
    }

    public static void main(String args[]) throws Exception {
        /*
         * Enter your code here. Read input from STDIN. Print output to STDOUT
         */

        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {

            String ip = input.nextLine();

            String[] splitArr = ip.split(":");
            if (ip.charAt(0) == 'E') {
                System.out.println("E:" + maskEmail(splitArr[1]));
            } else if (ip.charAt(0) == 'P') {
                System.out.println("P:" + maskPhone(splitArr[1]));
            }
        }
    }

    @Test
    public void test1() {
        String ph = "(333)456-7890";
        assertEquals(maskPhone(ph), "***-***-7890");
    }

    @Test
    public void test2() {
        String ph = "+1(333) 456-7890";
        assertEquals(maskPhone(ph), "+*-***-***-7890");
    }

    @Test
    public void test3() {
        String ph = "+1 (333) 444-5678";
        assertEquals(maskPhone(ph), "+*-***-***-5678");
    }

    @Test
    public void test4() {
        String ph = "+91 (333) 444-5678";
        assertEquals(maskPhone(ph), "+**-***-***-5678");
    }

    @Test
    public void test5() {
        String ph = "+111 (333) 444-5678";
        assertEquals(maskPhone(ph), "+***-***-***-5678");
    }

    @Test
    public void test6() {
        String ph = "333 444 5678";
        assertEquals(maskPhone(ph), "***-***-5678");
    }

    @Test
    public void test7() {
        String ph = "(333) 444-5678";
        assertEquals(maskPhone(ph), "***-***-5678");
    }

    @Test
    public void test8() {
        String email = "jackAndJill@twitter.com";
        assertEquals(maskEmail(email), "j*****l@twitter.com");
    }

    @Test
    public void test9() {
        String email = "jl@twitter.com";
        assertEquals(maskEmail(email), "j*****l@twitter.com");
    }

    @Test
    public void test10() {
        String email = "j@twitter.com";
        assertEquals(maskEmail(email), "j*****j@twitter.com");
    }

    public void test11() {
        String phone = "P:+1(333) 456-7890";
        assertEquals(maskPhone(phone), "+*-***-***-7890");
    }

}

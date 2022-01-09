package com.ramprasadg.interview.google;

import static org.junit.Assert.*;

import java.util.Stack;

import org.junit.Test;

public class DecodeString {

    public String decodeString(String s) {
        StringBuilder retVal = new StringBuilder();
        Stack<String> stack = new Stack<String>();

        Integer number = 0;
        for (int i = 0; i < s.length(); i++) {
            Character ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                number = number * 10 + Character.getNumericValue(ch);
            } else if (ch == '[') {
                stack.add(number.toString());
                stack.add("[");
                number = 0;
            } else if (ch == ']') {
                StringBuilder strToRepeat = new StringBuilder();
                while (!stack.peek().equals("[")) {
                    strToRepeat.insert(0, stack.pop());
                }
                stack.pop();//discard [
                int repeat = Integer.parseInt(stack.pop());

                String str = strToRepeat.toString();
                strToRepeat.setLength(0);

                while (repeat > 0) {
                    strToRepeat.append(str);
                    repeat --;
                }
                stack.push(strToRepeat.toString());
            } else {
                stack.add(ch.toString());
            }
        }
        
        while (! stack.isEmpty()) {
            retVal.insert(0, stack.pop());
        }

        return retVal.toString();
    }

    @Test
    public void test1() {
        assertEquals("accacc", decodeString("2[a2[c]]"));
    }

    @Test
    public void test2() {
        assertEquals("sfeegfeegi", decodeString("s2[f2[e]g]i"));
    }

    @Test
    public void test3() {
        assertEquals("aaxzz", decodeString("2[a]x2[z]"));
    }

    @Test
    public void test4() {
        assertEquals("abc", decodeString("abc"));
    }

    @Test
    public void test5() {
        assertEquals("aa", decodeString("2[a]"));
    }

    @Test
    public void test6() {
        assertEquals("aabb", decodeString("2[a]2[b]"));
    }
}
package src.main.java.com.ramprasadg.interview.honeywell;

import static org.junit.Assert.*;

import org.junit.Test;

public class BalancedSmileys {
    public static String[] is_balanced(String[] expression) {
        String result[] = new String[expression.length];
        for (int i = 0; i < expression.length; i++) {
            result[i] = is_balanced(expression[i]) ? "YES" : "NO";
        }
        
        return result;
    }

    private static boolean is_balanced(String string) {
        return is_balanced(string, 0, 0);
    }

    private static boolean is_balanced(String string, int startIndex, int openParanthesis) {
        if (startIndex >= string.length()) {
            return openParanthesis == 0;
        }
        if (openParanthesis < 0) {
            return false;
        }

        char ch = string.charAt(startIndex);

        if (ch == '(') {
            return is_balanced(string, startIndex + 1, openParanthesis + 1);
        } else if (ch == ')') {
            return is_balanced(string, startIndex + 1, openParanthesis - 1);
        } else if(ch == ':') {
            return is_balanced(string, startIndex + 1, openParanthesis) || is_balanced(string, startIndex + 2, openParanthesis);
        } else {
            return is_balanced(string, startIndex + 1, openParanthesis);
        }
    }

    @Test
    public void test1() {
        assertTrue(is_balanced("Start :):)"));
    }
    
    @Test
    public void test2() {
        assertTrue(is_balanced("(:)"));
    }
    
    @Test
    public void test3() {
        assertTrue(is_balanced("Today (:()"));
    }
    
    @Test
    public void test4() {
        assertFalse(is_balanced(")("));
    }
    
    @Test
    public void test5() {
        assertFalse(is_balanced(":(("));
    }
    
    @Test
    public void testa1() {
        assertTrue(is_balanced(""));
    }
}

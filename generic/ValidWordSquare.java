package generic;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class ValidWordSquare {
    public boolean validWordSquare(List<String> words) {
        for (int i = 0; i < words.size(); i++) {
            for (int j = 0; j < words.get(i).length(); j++) {
                if (words.size() - 1 < i || words.size() - 1 < j || words.get(i).length() - 1 < j || words.get(j).length() - 1 < i
                        || words.get(i).charAt(j) != words.get(j).charAt(i)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    @Test
    public void test1() {
        List<String> words = new LinkedList<String>();
        words.add("abcd");
        words.add("bnrt");
        words.add("crmy");
        words.add("dtye");
        assertTrue(validWordSquare(words));
    }
    
    @Test
    public void test2() {
        List<String> words = new LinkedList<String>();
        words.add("abcd");
        words.add("bnrt");
        words.add("crm");
        words.add("dt");
        assertTrue(validWordSquare(words));        
    }
    
    @Test
    public void test3() {
        List<String> words = new LinkedList<String>();
        words.add("ball");
        words.add("area");
        words.add("read");
        words.add("lady");
        assertFalse(validWordSquare(words));
    }
    
    @Test
    public void test4() {
        List<String> words = new LinkedList<String>();
        words.add("abc");
        words.add("bde");
        words.add("cefg");
        assertFalse(validWordSquare(words));
    }
}

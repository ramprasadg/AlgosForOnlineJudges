package generic;

import static org.junit.Assert.*;

import org.junit.Test;

public class OneEditDistance {
    public boolean isOneEditDistance(String s1, String s2) {
        String shorter = null;
        String longer = null;
        if(s1.length() < s2.length()) {
            shorter = s1;
            longer = s2;
        } else {
            shorter = s2;
            longer = s1;
        }
        
        int len1 = shorter.length();
        int len2 = longer.length();
        
        if(len2 - len1 > 1) {
            return false;
        }
        
        if(len1 == 0) {
            return true;
        }
        
        int i = 0;
        int j = 0;
        int diffs = 0;
        
        while(i < shorter.length() && j < longer.length()) {
            if(shorter.charAt(i) == longer.charAt(j)) {
                i++;
                j++;
            } else {
                //assume edit will fix the diff
                if(len1 == len2) {
                    i++;
                    j++;
                    diffs ++;
                } 
              //assume delete will fix the diff
                else {
                    j++;
                    diffs ++;
                }
            }
        }
        
        //last character was extra
        if(j < longer.length()) {
            diffs ++;
        }
        
        return diffs == 1;
    }
    
    @Test
    public void test1() {
        assertTrue(isOneEditDistance("", "a"));
    }
    
    @Test
    public void test2() {
        assertTrue(isOneEditDistance("a", "b"));
    }
    
    @Test
    public void test3() {
        assertTrue(isOneEditDistance("a", "ab"));
    }
    
    @Test
    public void test4() {
        assertFalse(isOneEditDistance("a", "abb"));
    }
}

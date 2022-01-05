package src.main.java.com.ramprasadg.generic.datastructures;

import static org.junit.Assert.*;

import org.junit.Test;

//https://www.youtube.com/watch?v=CWDQJGaN1gY
public class FenwickTree {
    public int getParent(int x) {
        if(x == 0) {
            return x;
        }
        //01 original number
        //10 flip all bits
        //11 plus 1
        //01 and with original number
        //00 subract from original number
        return (x - (x & -x));
    }
    
    public int getNext(int x) {
        if(x == 0) {
            return x;
        }
        //01 original number
        //10 flip all bits
        //11 plus 1
        //01 and with original number
        //10 add from original number
        return (x + (x & -x));
    }
    
    @Test
    public void test1() {
        assertEquals(0, getParent(1));
        assertEquals(0, getParent(2));
        assertEquals(0, getParent(4));
        assertEquals(0, getParent(8));
        assertEquals(8, getParent(10));
        assertEquals(10, getParent(11));
    }
    
    @Test
    public void test2() {
        assertEquals(2, getNext(1));
        assertEquals(4, getNext(2));
        assertEquals(4, getNext(3));
        assertEquals(8, getNext(4));
        assertEquals(6, getNext(5));
    }
}

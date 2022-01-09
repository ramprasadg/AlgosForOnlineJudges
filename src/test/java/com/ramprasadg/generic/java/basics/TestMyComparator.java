package com.ramprasadg.generic.java.basics;

import java.util.Arrays;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

public class TestMyComparator {
    @Test
    public void test1() {
        Integer[] arr = { 1, 3, 2 };
        List<Integer> list = new ArrayList<Integer>(Arrays.asList(arr));
        Collections.sort(list);
        assertEquals(Integer.valueOf(1), list.get(0));
        assertEquals(Integer.valueOf(3), list.get(2));

        Collections.sort(list, (h1, h2) -> h2.compareTo(h1));

        assertEquals(Integer.valueOf(3), list.get(0));
        assertEquals(Integer.valueOf(1), list.get(2));
    }
}

package com.ramprasadg.generic.java.basics;

import java.util.Arrays;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

public class TestMyComparator {
    @Test
    public void testUsingLambda() {
        Integer[] arr = { 1, 3, 2 };
        List<Integer> list = new ArrayList<Integer>(Arrays.asList(arr));

        // sort 1,2,3
        Collections.sort(list, (a, b) -> a-b);
        assertEquals(Integer.valueOf(1), list.get(0));
        assertEquals(Integer.valueOf(3), list.get(2));

        // (same as) sort 1,2,3
        Collections.sort(list);
        assertEquals(Integer.valueOf(1), list.get(0));
        assertEquals(Integer.valueOf(3), list.get(2));

        // reverse sort 3,2,1
        Collections.sort(list, (a, b) -> b-a);
        assertEquals(Integer.valueOf(3), list.get(0));
        assertEquals(Integer.valueOf(1), list.get(2));

        // (same as) reverse sort 3,2,1
        Collections.sort(list, (h1, h2) -> h2.compareTo(h1));
        assertEquals(Integer.valueOf(3), list.get(0));
        assertEquals(Integer.valueOf(1), list.get(2));
    }

    @Test
    public void testUsingComparingInt() {
        class Student {
            String name;
            int age;

            public String toString() {
                return name;
            }

            public Student (String name, int age) {
                this.name = name;
                this.age = age;
            }
        }

        List<Student> list = new ArrayList<Student>();
        Student s1 = new Student("1",1);
        list.add(s1);
        Student s3 = new Student("3",3);
        list.add(s3);
        Student s2 = new Student("2",2);
        list.add(s2);

        assertEquals(s1, list.get(0));
        assertEquals(s3, list.get(1));

        // sort based on age
        Collections.sort(list, Comparator.comparingInt((x) -> x.age));

        assertEquals(s1, list.get(0));
        assertEquals(s3, list.get(2));
    }
}

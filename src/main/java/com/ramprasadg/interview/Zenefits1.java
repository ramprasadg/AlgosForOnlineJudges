package src.main.java.com.ramprasadg.interview;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class Zenefits1 {
    static boolean contains_bomb(int[] arr) {
        if (arr == null || arr.length < 6) {
            return false;
        }

        List<Integer> list = new LinkedList<Integer>();

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i - 1]) {
                if (list.isEmpty() || list.get(list.size() - 1) != arr[i]) {
                    list.add(arr[i]);
                }
                if (list.size() == 3) {
                    return true;
                }
            } else {
                if (! list.isEmpty() && list.get(list.size() - 1) + 1 != arr[i]) {
                    list.clear();
                }
            }
        }

        return false;
    }

    @Test
    public void test1() {
        int arr[] = { 1, 2, 2, 3, 3, 4, 5, 5 };
        assertFalse(contains_bomb(arr));
    }

    @Test
    public void test2() {
        int arr[] = { 1, 2, 2, 3, 3, 4, 4, 5 };
        assertTrue(contains_bomb(arr));
    }

    @Test
    public void test3() {
        int arr[] = { 1, 1, 2, 2, 4, 4 };
        assertFalse(contains_bomb(arr));
    }

    @Test
    public void test4() {
        int arr[] = { 1, 1, 2, 2, 3, 3, 3, 3, 4, 4 };
        assertTrue(contains_bomb(arr));
    }
}

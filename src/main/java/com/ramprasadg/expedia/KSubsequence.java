package src.main.java.com.ramprasadg.expedia;

import static org.junit.Assert.*;

import org.junit.Test;

public class KSubsequence {
    static long kSub(int k, int[] nums) {
        int sum = 0;
        int[] count = new int[k];
        count[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            sum = sum % k;
            count[sum]++;
        }
        
        int ans = 0;

        for (int i = 0; i < count.length; i++) {
            System.out.println(count[i]);
            if (count[i] > 1) {
                ans += count[i] * (count[i] - 1) / 2;
            }
        }

        return ans;
    }

    @Test
    public void test1() {
        assertEquals(4, kSub(3, new int[] { 1, 2, 3, 4, 1 }));
    }

    @Test
    public void test2() {
        assertEquals(6, kSub(3, new int[] { 10, 1, 2, 3, 2, 1 }));
        //1,2,1,1,0,1
    }
    
    @Test
    public void test3() {
        assertEquals(4, kSub(3, new int[] { 1, 2, 3, 4, 1 }));
        //1,0,0,2,1
    }
}

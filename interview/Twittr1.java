package interview;

import static org.junit.Assert.*;

import org.junit.Test;

public class Twittr1 {
    int maxLength(int nums[], int k) {
        int len = nums.length;
        
        if(len < 1) {
            return 0;
        }

        int start = 0;
        int sumOfChars = nums[0];
        int lengthOfSubArray = 0;
        
        if(sumOfChars <= k) {
            lengthOfSubArray = 1;
        }
        
        for (int i = 1; i < len; i++) {
            sumOfChars += nums[i];
            
            while(sumOfChars > k) {
                sumOfChars -= nums[start];
                start ++;
            }
            
            if(sumOfChars <= k) {
                lengthOfSubArray = Math.max(lengthOfSubArray, i - start + 1);
            }
        }

        return lengthOfSubArray;
    }

    @Test
    public void test1() {
        int[] arr = { 1, 2, 3 };
        int k = 4;
        assertEquals(maxLength(arr, k), 2);
    }
    
    @Test
    public void test2() {
        int[] arr = { 3, 1, 2, 1 };
        int k = 4;
        assertEquals(maxLength(arr, k), 3);
    }
    
    @Test
    public void test3() {
        int[] arr = { 74, 659, 931, 273, 545, 879, 924, 710, 441, 166, 493, 43, 988, 504, 328, 730, 841, 613, 304, 170, 710, 158, 561, 934, 100, 279, 817, 336, 98, 827, 513, 268, 811, 634, 980, 150, 580, 822, 968, 673, 394, 337, 486, 746, 229, 92, 195, 358, 2, 154, 709, 945, 669, 491, 125, 197, 531, 904, 723, 667, 550 };
        int k = 22337;
        assertEquals(maxLength(arr, k), 46);
    }
}

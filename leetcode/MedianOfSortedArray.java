package leetcode;

import org.junit.Test;

public class MedianOfSortedArray {

    private static final double DELTA = 1e-15;

    private void assertEquals(double x, double y) {
        org.junit.Assert.assertEquals(x, y, DELTA);
    }

    @Test
    public void oneArrayWithOneElement() {
        int arr1[] = {};
        int arr2[] = { 1 };
        assertEquals(1.0, new MedianOfSortedArray().findMedianSortedArrays(arr1, arr2));
    }

    @Test
    public void oneArrayWithTwoElements() {
        int arr1[] = {};
        int arr2[] = { 2, 3 };
        assertEquals(2.5, new MedianOfSortedArray().findMedianSortedArrays(arr1, arr2));
    }

    @Test
    public void oneArrayWithThreeElements() {
        int arr1[] = {};
        int arr2[] = { 1, 3, 4 };
        assertEquals(3, new MedianOfSortedArray().findMedianSortedArrays(arr1, arr2));
    }

    @Test
    public void twoArraysEqualSize1() {
        int arr1[] = { 1, 2 };
        int arr2[] = { 3, 4 };
        assertEquals(2.5, new MedianOfSortedArray().findMedianSortedArrays(arr1, arr2));
    }

    @Test
    public void twoArraysEqualSize2() {
        int arr1[] = { 100000 };
        int arr2[] = { 100001 };
        assertEquals(100000.5, new MedianOfSortedArray().findMedianSortedArrays(arr1, arr2));
    }

    @Test
    public void median2NotPresentInBothArrays() {
        int arr1[] = { 1, 1, 3, 3 };
        int arr2[] = { 1, 1, 3, 3 };
        assertEquals(2, new MedianOfSortedArray().findMedianSortedArrays(arr1, arr2));
    }

    @Test
    public void twoArraysEvenLength2() {
        int arr1[] = { 1, 2, 3 };
        int arr2[] = { 4, 5, 6 };
        assertEquals(3.5, new MedianOfSortedArray().findMedianSortedArrays(arr1, arr2));
    }

    @Test
    public void twoArraysUnevenLength1() {
        int arr1[] = { 1 };
        int arr2[] = { 2, 3, 4, 5 };
        assertEquals(3, new MedianOfSortedArray().findMedianSortedArrays(arr1, arr2));
    }

    @Test
    public void twoArraysUnevenLength2() {
        int arr1[] = { 3, 4, 5 };
        int arr2[] = { 1, 2 };
        assertEquals(3, new MedianOfSortedArray().findMedianSortedArrays(arr1, arr2));
    }

    @Test
    public void twoArraysUnevenLength3() {
        int arr1[] = { 1, 2 };
        int arr2[] = { 3, 4, 5 };
        assertEquals(3, new MedianOfSortedArray().findMedianSortedArrays(arr1, arr2));
    }

    @Test
    public void twoArraysUnevenLength4() {
        int arr1[] = { 1, 2 };
        int arr2[] = { 1, 2, 3 };
        assertEquals(2, new MedianOfSortedArray().findMedianSortedArrays(arr1, arr2));
    }

    @Test
    public void twoArraysUnevenLength5() {
        int arr1[] = { 1, 3 };
        int arr2[] = { 2 };
        assertEquals(2, new MedianOfSortedArray().findMedianSortedArrays(arr1, arr2));
    }

    @Test
    public void twoArraysUnevenLength6() {
        int arr1[] = { 2 };
        int arr2[] = { 1, 3, 4 };
        assertEquals(2.5, new MedianOfSortedArray().findMedianSortedArrays(arr1, arr2));
    }

    @Test
    public void twoArraysUnevenLength7() {
        int arr1[] = { 2, 3 };
        int arr2[] = { 1 };
        assertEquals(2, new MedianOfSortedArray().findMedianSortedArrays(arr1, arr2));
    }

    @Test
    public void twoArraysUnevenLength8() {
        int arr1[] = { 1, 5, 6 };
        int arr2[] = { 2, 3, 4, 7 };
        assertEquals(4, new MedianOfSortedArray().findMedianSortedArrays(arr1, arr2));
    }

    @Test
    public void twoArraysUnevenLength9() {
        int arr1[] = { 1, 2 };
        int arr2[] = { 3, 4, 5, 6 };
        assertEquals(3.5, new MedianOfSortedArray().findMedianSortedArrays(arr1, arr2));
    }

    @Test
    public void twoArraysEvenLength10() {
        int arr1[] = { 1, 4, 5 };
        int arr2[] = { 2, 3, 6 };
        assertEquals(3.5, new MedianOfSortedArray().findMedianSortedArrays(arr1, arr2));
    }

    @Test
    public void twoArraysEvenLength11() {
        int arr1[] = { 1, 2, 6, 7 };
        int arr2[] = { 3, 4, 5, 8 };
        assertEquals(4.5, new MedianOfSortedArray().findMedianSortedArrays(arr1, arr2));
    }

    @Test
    public void twoArraysEvenLength12() {
        int arr1[] = { 3, 4, 5, 8 };
        int arr2[] = { 1, 2, 6, 7 };
        assertEquals(4.5, new MedianOfSortedArray().findMedianSortedArrays(arr1, arr2));
    }

    public void print(String s) {
        System.out.print(s);
    }

    public void println(String s) {
        System.out.println(s);
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        return helper(nums1, 0, nums1.length, nums2, 0, nums2.length);
    }

    public double median(int a, int b) {
        println("median2");
        return (a + b) / 2.0;
    }

    public double median(int a, int b, int c) {
        println("median3");
        int min = Math.min(Math.min(a, b), c);
        int max = Math.max(Math.max(a, b), c);
        return a + b + c - min - max;
    }

    public double median(int a, int b, int c, int d) {
        println("median4");
        int min = Math.min(Math.min(a, b), Math.min(c, d));
        int max = Math.max(Math.max(a, b), Math.max(c, d));
        return (a + b + c + d - min - max) / 2.0;
    }

    public double median(int[] nums, int start, int end) {
        int mid = (start + end) / 2;
        println("start=" + start + " end=" + end);
        for (int i = start; i < end; i++) {
            print(nums[i] + " ");
        }
        println("");

        double med;
        if (end - start == 0) {
            med = 0.0;
        } else if (end - start == 1) {
            med = nums[start];
        } else if ((end - start) % 2 == 1) {
            med = nums[mid];
        } else {
            med = median(nums[mid], nums[mid - 1]);
        }

        return med;
    }

    /**
     * [1][2] [1, 2][3], [1][2, 3], [1,3][2] [1,2][3,4], [3,4][1,2], [1,3],
     * [2,4] [1,2,4][3,5]
     */
    public double helper(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2) {
        int mid1 = (start1 + end1) / 2;
        int mid2 = (start2 + end2) / 2;
        int len1 = end1 - start1;
        int len2 = end2 - start2;

        if (len1 > len2) {
            println("case: swap");
            return helper(nums2, start2, end2, nums1, start1, end1);
        }

        double med1 = median(nums1, start1, end1);
        double med2 = median(nums2, start2, end2);
        println("");

        if (len1 == 0) {
            println("case: len1 == 0");
            return median(nums2, start2, end2);
        } else if (len2 == 0) {
            println("case: len2 == 0");
            return median(nums1, start1, end1);
        }

        if (len1 == 1 && len2 == 1) {
            println("case: len1 == 1 && len2 == 1");
            return median(nums1[start1], nums2[start2]);
        } else if (len1 == 1 && len2 == 2) {
            println("case: len1 == 1 && len2 == 2");
            return median(nums1[start1], nums2[start2], nums2[start2 + 1]);
        } else if (len1 == 2 && len2 == 2) {
            println("case: len1 == 2 && len2 == 2");
            return median(nums1[start1], nums2[start2], nums1[end1 - 1], nums2[end2 - 1]);
        } else if (med1 == med2) {
            println("case: med1 == med2");
            return med1;
        }

        if (len1 == 1 && len2 % 2 == 1) {
            return median(nums1[start1], nums2[mid2 - 1], nums2[mid2], nums2[mid2 + 1]);
        } else if (len1 == 1 && len2 % 2 == 0) {
            return median(nums1[start1], nums2[mid2 - 1], nums2[mid2]);
        } else if (len1 == 2 && len2 % 2 == 1) {
            return median(Math.max(nums1[start1], nums2[mid2 - 1]), nums2[mid2],
                    Math.min(nums1[start1 + 1], nums2[mid2 + 1]));
        } else if (len1 == 2 && len2 % 2 == 0) {
            return median(Math.max(nums1[start1], nums2[mid2 - 2]), nums2[mid2 - 1], nums2[mid2],
                    Math.min(nums1[start1 + 1], nums2[mid2 + 1]));
        }

        if (med1 <= med2) {

            if (len1 % 2 == 0) {
                println("case: med1 <= med2 && len1 %2 == 0");
                return helper(nums1, mid1 - 1, end1, nums2, start2, end2 - (mid1 - start1 - 1));
            } else {
                println("case: med1 <= med2 && len1 %2 == 1");
                return helper(nums1, mid1, end1, nums2, start2, end2 - (mid1 - start1));
            }
        } else {
            if (len1 % 2 == 0) {
                println("case: med1 > med2 && len1 %2 == 0");
                return helper(nums1, start1, mid1 + 1, nums2, start2 + (mid1 - start1 - 1), end2);
            } else {
                println("case: med1 > med2 && len1 %2 == 1");
                return helper(nums1, start1, mid1 + 1, nums2, start2 + (mid1 - start1), end2);
            }

        }
    }
}

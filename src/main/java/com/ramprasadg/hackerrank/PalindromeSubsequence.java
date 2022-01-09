package com.ramprasadg.hackerrank;

public class PalindromeSubsequence {
    
    
    int largestPalindromeEndingAtI[];
    int largestPalindromeStartingAftertI[];
    
    //longestPalindromeSubsequenceFinder
    int find(String s, int start, int end) {
        
        int val = 0;
        if(start == end) {
            return 1;
        } else if (start > end) {
            return 0;
        }
        
        int maxAfterStart = find(s, start+1, end);
        int maxBeforeEnd = find(s, start, end-1);
        if(s.charAt(start) == s.charAt(end)) {
            maxAfterStart = find(s, start+1, end-1);
            val = 2 + maxAfterStart;
            maxBeforeEnd = val;
        } else {
            val = Math.max( maxAfterStart,  maxBeforeEnd);
            maxBeforeEnd = val;
        }
        
        largestPalindromeEndingAtI[end] = Math.max(largestPalindromeEndingAtI[end], val);
        largestPalindromeStartingAftertI[start] = Math.max(largestPalindromeStartingAftertI[start], maxAfterStart);
        return val;
    }
    
    int findMaxPairPalindrome(String s) {
        largestPalindromeEndingAtI = new int[s.length()];
        largestPalindromeStartingAftertI = new int[s.length()];
        for(int i=0;i<largestPalindromeEndingAtI.length;i++) {
            largestPalindromeEndingAtI[i] = largestPalindromeStartingAftertI[i] = 1;
        }
        
        find(s, 0, s.length()-1);
        
        for(int i=0;i<largestPalindromeEndingAtI.length;i++) {
            System.out.print(largestPalindromeEndingAtI[i]);
        }
        System.out.println();
        
        for(int i=0;i<largestPalindromeEndingAtI.length;i++) {
            System.out.print(largestPalindromeStartingAftertI[i]);
        }
        System.out.println();
        
        int max = 0;
        for(int i=0; i<largestPalindromeEndingAtI.length; i++) {
            max = Math.max(largestPalindromeEndingAtI[i] * largestPalindromeStartingAftertI[i], max);
        }
        
        return max;
    }
    
    public static void main(String args[]) {
        PalindromeSubsequence ps = new PalindromeSubsequence();
        System.out.println(ps.findMaxPairPalindrome("abababa"));
    }
}

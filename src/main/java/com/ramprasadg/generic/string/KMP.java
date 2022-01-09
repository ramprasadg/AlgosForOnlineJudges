package com.ramprasadg.generic.string;
public class KMP {
    
    int[] prefix;
    
    public KMP(String pattern) {
        prefix = new int[pattern.length()];
        int j = 0;
        prefix[0] = j;
        for(int i = 1; i<pattern.length();) {
            if(pattern.charAt(i) == pattern.charAt(j)) {
                j++;
                prefix[i] = j;
                i++;
            } else {
                if(j > 0) {
                    j = prefix[j-1];
                } else {
                    prefix[i] = 0;
                    i++;
                }
            }
        }
        
        for(int i:prefix) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        new KMP("AAABAAA");
    }
    
}

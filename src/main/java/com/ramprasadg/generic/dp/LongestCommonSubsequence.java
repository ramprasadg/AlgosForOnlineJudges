package com.ramprasadg.generic.dp;

public class LongestCommonSubsequence {
    public static String find(String a, String b) {
        String ret = "";
        int matrix[][] = new int[a.length()][b.length()];
        
        for(int i = 0; i < a.length(); i++) {
            for(int j = 0; j < b.length(); j++) {
                if(a.charAt(i) == b.charAt(j)) {
                    if(i>0 && j >0) {
                        matrix[i][j] = matrix[i-1][j-1];
                    }
                } else {
                    matrix[i][j] = Integer.MAX_VALUE;
                    if(i > 0) {
                        matrix[i][j] = matrix[i-1][j];
                    }
                    if(j > 0) {
                        matrix[i][j] = Math.min(matrix[i][j-1], matrix[i][j]);
                    }
                    matrix[i][j] ++;
                }
            }
        }
        System.out.println(matrix[a.length()][b.length()]);
        
        return ret;
    }
}

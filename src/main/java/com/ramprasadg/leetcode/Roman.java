package com.ramprasadg.leetcode;

import java.util.*;

public class Roman {
    static class SymbolAndValue {
        public String symbol;
        public int value;
        
        public SymbolAndValue(String symbol, int value) {
            this.symbol = symbol;
            this.value = value;
        }
    }
    
    static List<SymbolAndValue> mappings = new LinkedList<SymbolAndValue>();
    
    static {    
        mappings.add(new SymbolAndValue("M",1000));
        mappings.add(new SymbolAndValue("CM",900));
        mappings.add(new SymbolAndValue("D",500));
        mappings.add(new SymbolAndValue("CD",400));
        mappings.add(new SymbolAndValue("C",100));
        mappings.add(new SymbolAndValue("XC",90));
        mappings.add(new SymbolAndValue("L",50));
        mappings.add(new SymbolAndValue("XL",40));
        mappings.add(new SymbolAndValue("X",10));
        mappings.add(new SymbolAndValue("IX",9));
        mappings.add(new SymbolAndValue("V",5));
        mappings.add(new SymbolAndValue("IV",4));
        mappings.add(new SymbolAndValue("I",1));
    }
    
    public static int romanToInt(String s) {
        int value = 0;
        int index = 0;
        char[] charArr = s. toCharArray();
        for(SymbolAndValue mapping: mappings) {
            if(index >= charArr.length)
                break;
            while(startsWith(charArr, index, mapping.symbol)) {
                value += mapping.value;
                index += mapping.symbol.length();
            }
        }
        return value;
    }
    
    private static boolean startsWith(char[] charArr, int index, String symbol) {
        if(index < charArr.length && charArr[index] == symbol.charAt(0)) {
            if(symbol.length() == 1) {
                return true;
            }
            else if(symbol.length() == 2 && index + 1 < charArr.length && charArr[index + 1] == symbol.charAt(1) ) {
                return true;
            }
        }
        return false;
    }

    public static void main(String args[]) {
        System.out.println(romanToInt("MMMDLXXXVI"));
    }
}

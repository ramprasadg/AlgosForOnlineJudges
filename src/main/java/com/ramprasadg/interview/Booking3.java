package com.ramprasadg.interview;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Booking3 {
    public static void main(String[] args){
        Scanner s = new Scanner( System.in );

        List<Integer> arr = new ArrayList();

        while(s.hasNextInt()) {
            arr.add(s.nextInt());
        }

        if(arr.size() == 0) {
            return;
        }

        List<Integer> ls = new ArrayList<>();
        int[] newArr = new int[arr.size()];

        newArr[0] = arr.get(0);
        for(int i = 1; i < arr.size(); i++){
            newArr[i] = arr.get(i) - arr.get(i-1);
        }

        ls.add(0, newArr[0]);

        int index = 0;

        for(int i = 1; i < newArr.length; i++){
            if(-127 <= newArr[i] && newArr[i] <= 127){
                ls.add(++index, newArr[i]);
            }else{
                ls.add(++index, -128);
                ls.add(++index, newArr[i]);
            }
        }

        System.out.print(ls.get(0));
        for(int i = 1; i < ls.size(); i++){
            System.out.print(" " + ls.get(i));
        }
        System.out.println();
    }
}

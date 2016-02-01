package javabasics;

public class ShiftOperators {
    public static void main(String[] args) {
        System.out.println(4<<1);//8
        System.out.println(4>>1);//2
        System.out.println(4>>>1);//2
        System.out.println(-4>>1);//-2 i.e. borrow the sign bit
        System.out.println(-4>>>1);//2147483646 i.e. always borrow sign bit
    }
}

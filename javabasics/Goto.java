package javabasics;

public class Goto {

    public static void main(String[] args) {
        rowLoop: for(int r = 0; r < 5; r++) {       // Labeled loop
            System.out.println("r:"+r);
            colLoop: for(int c = 0; c < 6; c++) {  // Another one
                System.out.println("c:"+c);
              break rowLoop;                                   // Use a label
            }
         }
    }

}

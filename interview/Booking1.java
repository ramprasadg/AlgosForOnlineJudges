package interview;
import java.util.Scanner;

public class Booking1 {
    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);

        int sq = 0, rect = 0, poly = 0;

        while (input.hasNextInt()) {
            int a = input.nextInt();
            int b = input.nextInt();
            int c = input.nextInt();
            int d = input.nextInt();

            if (a > 0 && b > 0 && c > 0 && d > 0) {
                if (a == b && b == c && c == d) {
                    sq++;
                } else if (a == c && b == d) {
                    rect++;
                } else {
                    poly++;
                }
            } else {
                poly++;
            }
        }

        System.out.println("" + sq + " " + rect + " " + poly);
    }
}

package src.main.java.com.ramprasadg.interview;

public class seq2 {
    static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }

    static String canReach(int a, int b, int c, int d) {
        int a1 = Math.max(a, b);
        int b1 = Math.min(a, b);
        int c1 = Math.max(c, d);
        int d1 = Math.min(c, d);
        
        if (c >= a && d >= b && gcd(a1, b1) == gcd(c1, d1)) {
            return "Yes";
        } else {
            return "No";
        }
    }
}

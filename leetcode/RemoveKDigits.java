package leetcode;

/**
 * 
 * Input: num = "1432219", k = 3 Output: "1219" Explanation: Remove the three
 * digits 4, 3, and 2 to form the new number 1219 which is the smallest.
 * 
 * Example 2: Input: num = "10200", k = 1 Output: "200" Explanation: Remove the
 * leading 1 and the number is 200. Note that the output must not contain
 * leading zeroes.
 * 
 * Example 3: Input: num = "10", k = 2 Output: "0" Explanation: Remove all the
 * digits from the number and it is left with nothing which is 0.
 *
 */
public class RemoveKDigits {
    public static String removeKdigits(String num, int k) {
        StringBuilder sb = new StringBuilder();
        for (char c : num.toCharArray()) {
            while (k > 0 && sb.length() != 0 && sb.charAt(sb.length() - 1) > c) {
                sb.setLength(sb.length() - 1);
                k--;
            }
            if (sb.length() != 0 || c != '0')
                sb.append(c); // Only append when it is not leading zero
        }
        if (k <= sb.length())
            sb.setLength(sb.length() - k); // use all remaining k
        return sb.length() == 0 ? "0" : sb.toString();
    }

    public static void main(String args[]) {
        System.out.println(RemoveKDigits.removeKdigits("21", 1));
    }
}

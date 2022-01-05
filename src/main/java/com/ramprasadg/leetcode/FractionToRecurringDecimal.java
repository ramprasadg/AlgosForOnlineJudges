package src.main.java.com.ramprasadg.leetcode;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

public class FractionToRecurringDecimal {

    static FractionToRecurringDecimal f = new FractionToRecurringDecimal();

    @Test
    public void divisible1() {
        assertEquals("1", f.fractionToDecimal(2, 2));
    }

    @Test
    public void divisible2() {
        assertEquals("-2", f.fractionToDecimal(-4, 2));
    }

    @Test
    public void divisible3() {
        assertEquals("2", f.fractionToDecimal(4, 2));
    }

    @Test
    public void notDivisible1() {
        assertEquals("1.5", f.fractionToDecimal(3, 2));
    }

    @Test
    public void notDivisible2() {
        assertEquals("0.(3)", f.fractionToDecimal(1, 3));
    }

    @Test
    public void notDivisible3() {
        assertEquals("1.(3)", f.fractionToDecimal(4, 3));
    }

    @Test
    public void notDivisible4() {
        assertEquals("3.(03)", f.fractionToDecimal(100, 33));
    }

    @Test
    public void notDivisible5() {
        assertEquals("0.1(6)", f.fractionToDecimal(1, 6));
    }

    public String fractionToDecimal(long num, long den) {
        StringBuilder sb = new StringBuilder();

        if ((num > 0) ^ (den > 0))
            sb.append("-");

        num = Math.abs(num);
        den = Math.abs(den);

        sb.append(num / den);
        if (num >= den) {
            num = num % den;
        }

        if (num != 0) {
            sb.append(".");

            HashMap<Long, Integer> map = new HashMap<Long, Integer>();
            map.put(num, sb.length());
            while (num != 0) {
                num *= 10;
                sb.append(num / den);
                num %= den;
                if (map.containsKey(num)) {
                    int index = map.get(num);
                    sb.insert(index, "(");
                    sb.append(")");
                    break;
                } else {
                    map.put(num, sb.length());
                }
            }
            return sb.toString();
        }

        return sb.toString();
    }
}

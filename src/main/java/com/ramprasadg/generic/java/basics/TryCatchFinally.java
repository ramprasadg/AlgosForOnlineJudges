package src.main.java.com.ramprasadg.generic.java.basics;

import org.junit.Test;

public class TryCatchFinally {
    public StringBuilder foo() {
        System.out.println("foo start");
        StringBuilder builder = new StringBuilder();
        try {
            System.out.println("foo try");
            builder.append("hello");
            return builder.append("world");
        } finally {
            System.out.println("foo catch start");
            // builder.append("+1");
            builder = null;
        }
    }

    @Test
    public void test1() {
        System.out.println("test1 start");
        System.out.println(foo().toString());
        System.out.println("test1 end");
    }
}

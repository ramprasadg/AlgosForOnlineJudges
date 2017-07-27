package generic.java.memory;
import java.util.*;

public class Heap {
    public static void main(String args[]) {
        List<Integer> a = new LinkedList<Integer>();
        List<Integer> b = new LinkedList<Integer>();
        while(true) {
            a.add(1<<5);
            b.add(1<<5);
        }
    }
}

package interview;
import java.util.*;

public class MemoryAnalysis {
    public static void main(String args[]) {
        System.out.println("hi");
        Map<MemoryAnalysis, String> leakMap = new HashMap<>();
        for(int i=0; i < 10000; i++) {
            leakMap.put(new MemoryAnalysis(), "");
        }
        while(true) {
            
        }
    }
}

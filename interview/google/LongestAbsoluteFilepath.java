package interview.google;

import static org.junit.Assert.*;

import java.util.Stack;

import org.junit.Test;

public class LongestAbsoluteFilepath {
    public int lengthLongestPath(String input) {
        String maxPath = "";
        Stack<String> path = new Stack<String>();

        input = input + "\n";
        StringBuilder sb = new StringBuilder();
        boolean isFile = false;
        int currentLevel = 0, previousLevel = 0;
        String parentPath = "";

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            switch (ch) {
            case '\n':
                String name = sb.toString();
                sb.setLength(0); // clear
                path.push(parentPath + name);
                if (isFile) {
                    if (maxPath.length() < path.peek().length()) {
                        maxPath = path.peek();
                    }
                    isFile = false;
                }

                previousLevel = currentLevel;
                currentLevel = 0;
                i++;

                while (i < input.length()) {
                    ch = input.charAt(i);
                    if (ch == '\t') {
                        currentLevel++;
                    } else {
                        i--;
                        break;
                    }
                    i++;
                }

                for (int j = currentLevel; j <= previousLevel && !path.isEmpty(); j++) {
                    path.pop();
                }
                if (!path.isEmpty()) {
                    parentPath = path.peek();
                    parentPath = parentPath + "/";
                } else {
                    parentPath = "";
                }
                break;
            case '.':
                isFile = true;
            default:
                sb.append(ch);
            }
        }
        System.out.println(maxPath);
        return maxPath.length();
    }

    @Test
    public void test1() {
        assertEquals(5, lengthLongestPath("a\nb\nc.png"));
    }
    
    @Test
    public void test2() {
        assertEquals(9, lengthLongestPath("a\n\tb\n\t\tc.png"));
    }
    
    @Test
    public void test3() {
        assertEquals(9, lengthLongestPath("a\n\tb\n\td\n\t\tc.png"));
    }
    
    @Test
    public void test4() {
        assertEquals(10, lengthLongestPath("a\n\tb\n\t\tc.png\n\td\n\t\tef.png"));
    }
    
    @Test
    public void test5() {
        assertEquals(10, lengthLongestPath("a\n\tb\n\t\tc.png\nd\n\te\n\t\tfg.png"));
    }

}

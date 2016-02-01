package generic;
public class KMP {
    
    int[] prefix;
    
    public KMP(String pattern) {
        prefix = new int[pattern.length()];
        int len = 0;
        prefix[0] = len;
        for(int i = 1; i<pattern.length();) {
            if(pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                prefix[i] = len;
                i++;
            } else {
                if(len > 0) {
                    len = prefix[len-1];
                } else {
                    prefix[i] = 0;
                    i++;
                }
            }
        }
        
        for(int i:prefix) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        new KMP("ABCABCABCDE");
        new KMP("ABABDABACDABABCABAB");
    }
    
}

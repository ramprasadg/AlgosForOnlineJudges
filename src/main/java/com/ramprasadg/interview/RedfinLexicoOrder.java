package src.main.java.com.ramprasadg.interview;

public class RedfinLexicoOrder {

    private static final int MAX_CHAR = 256;

    // A utility function to find factorial of n
    static int fact(int n) {
        return (n <= 1) ? 1 : n * fact(n - 1);
    }

    // Construct a count array where value at every index
    // contains count of smaller characters in whole string
    static void populateAndIncreaseCount(int[] count, String str) {
        int i;

        for (i = 0; i < str.length(); ++i)
            ++count[str.charAt(i)];

        for (i = 1; i < 256; ++i)
            count[i] += count[i - 1];
    }

    // Removes a character ch from count[] array
    // constructed by populateAndIncreaseCount()
    static void updatecount(int[] count, char ch) {
        int i;
        for (i = ch; i < MAX_CHAR; ++i)
            --count[i];
    }

    // A function to find rank of a string in all permutations
    // of characters
    static int findRank (String str)
    {
        int len = str.length();
        int mul = fact(len);
        int rank = 0, i;
        int count[] = new int[MAX_CHAR];  // all elements of count[] are initialized with 0
     
        // Populate the count array such that count[i] contains count of 
        // characters which are present in str and are smaller than i
        populateAndIncreaseCount( count, str );
     
        for (i = 0; i < len; ++i)
        {
            mul /= len - i;
     
            // count number of chars smaller than str[i]
            // fron str[i+1] to str[len-1]
            rank += count[ str.charAt(i) - 1] * mul;
     
            // Reduce count of characters greater than str[i]
            updatecount (count, str.charAt(i));
        }
     
        return rank;
    }

    // Driver program to test above function
    public static void main(String args[]) {
        System.out.println(findRank("bac"));
        System.out.println(findRank("aaa"));
        System.out.println(findRank("abba"));
        System.out.println(findRank("caabbc"));
    }

}

package src.main.java.com.ramprasadg.generic;

public class TicketCounterDP {
    static int input[][] = { { 1, 2, 1 }, { 1, 1, 3 }, { 1, 2, 3 }, { 1, 2, 3 }, };
    static int numberOfPeople = input.length;
    static int result[][] = new int[numberOfPeople][3 * numberOfPeople];

    public static void main(String args[]) {
        for(int i=0;i<result.length;i++) {
            for(int j =0;j<result[0].length;j++) {
                result[i][j] = Integer.MAX_VALUE;
            }
        }
        result[0][0] = input[0][0];
        result[0][1] = input[0][1];
        result[0][2] = input[0][2];
        
        for (int i = 1; i < numberOfPeople; i++) {
            for (int j = i; j < 3*(i+1); j++) {
                if(j-3 > 0 && result[i-1][j-3] != Integer.MAX_VALUE) {
                    result[i][j] = Math.min( result[i-1][j-3] + input[i][2], result[i][j]);
                }
                if(j-2 > 0 && result[i-1][j-2] != Integer.MAX_VALUE) {
                    result[i][j] = Math.min( result[i-1][j-2] + input[i][1], result[i][j]);
                }
                if(j-1 > 0 && result[i-1][j-1] != Integer.MAX_VALUE) {
                    result[i][j] = Math.min( result[i-1][j-1] + input[i][0], result[i][j]);
                }
                result[i][j] = Math.min( result[i-1][j], result[i][j]);
            }
        }
        
        for (int i = 0; i < numberOfPeople; i++) {
            for (int j = 0; j < 3*numberOfPeople; j++) {
                if(result[i][j] != Integer.MAX_VALUE)
                    System.out.print(result[i][j] + " ");
                else 
                    System.out.print("M ");
            }
            System.out.println();
        }
        
        
        int answer = Integer.MAX_VALUE;
        for(int i=numberOfPeople-1; i < 3 *numberOfPeople;i++) {
            answer = Math.min(answer, result[numberOfPeople-1][i]);
        }
        
        System.out.println(answer);
    }
}

package generic.geometry;

public class BooleanMatrixLine {
    public static void main(String args[]) {
        int I = 10, J = 10;
        boolean matrix[][] = new boolean[I][J];
        int startI = 0;
        int startJ = 0;
        int endI = 9;
        int endJ = 4;
        
        double slope =  (endJ - startJ) * 1.0 / (endI - startI);
        for(int i=startI; i <= endI; i++) {
            int j = (int) (startJ + slope * i);
            matrix[i][j] = true;
        }
        
        
        for(int i=0;i<I;i++) {
            for(int j=0;j<J;j++) {
                System.out.print((matrix[i][j]) ? 'X' : 'O');
            }
            System.out.println();
        }
    }
}

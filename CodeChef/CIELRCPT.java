import java.util.Scanner;

/**
 * 
 * @author ramprasad
 * http://www.codechef.com/problems/CIELRCPT
 *
 */
class CIELRCPT {

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		int test = in.nextInt();
		
		for (int i = 0; i < test; i++) {
			int n, m, k;
			
			n = in.nextInt();
			k = n / 2048;
			n = n % 2048;
			while (n >= 1) {
				m = n % 2;
				n = n / 2;
				if (m == 1)
					k++;
			}
			System.out.println(k);
		}			
	}
}

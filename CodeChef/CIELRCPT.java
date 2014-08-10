import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * @author ramprasad
 * http://www.codechef.com/problems/CIELRCPT
 *
 */
class CIELRCPT {

	public static void main(String[] args) throws Exception {
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(System.in));
		int test = Integer.parseInt(bufferedReader.readLine());
		
		for (int i = 0; i < test; i++) {
			int n, m, k;
			
			n = Integer.parseInt(bufferedReader.readLine());
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InteractRunner {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String values, exit = "no";
		int command;
		double a, b;
		Calculator calc = new Calculator();

		try {

			do {

				System.out.println("Insert command:");
				System.out.println("1 - sum numbers\n" + "2 - multiplication\n"
						+ "3 - involution\n" + "4 - get const PI");
				command = Integer.decode(in.readLine());

				switch (command) {
				case 1:
					System.out.println("Insert value A for sum:");
					a = Double.valueOf(in.readLine());
					System.out.println("Insert value B for sum:");
					b = Double.valueOf(in.readLine());
					calc.sum(a, b);
					System.out.println("Result=" + calc.getResult());
					calc.cleanResult();
					break;
				case 2:
					System.out.println("Insert value A for multiplication:");
					a = Double.valueOf(in.readLine());
					System.out.println("Insert value B for multiplication:");
					b = Double.valueOf(in.readLine());
					calc.multipl(a, b);
					System.out.println("Result=" + calc.getResult());
					calc.cleanResult();
					break;
				case 3:
					System.out.println("Insert value A (radix):");
					a = Double.valueOf(in.readLine());
					System.out.println("Insert value B (involution):");
					b = Double.valueOf(in.readLine());
					calc.pow(a, b);
					System.out.println("Result=" + calc.getResult());
					calc.cleanResult();
					break;
				case 4:
					System.out.println("Constant Pi=" + calc.getConstPI());
					calc.cleanResult();
					break;
				default:
					System.out.println("The command is incorrect!");
				}
				System.out.println("Exit from Calculator? (yes/no)");
				exit = in.readLine();
			} while (!exit.equals("yes")); /* while EXIT not (!) equals "yes" */

		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			in.close();
		}
	}
}
/*simple calculator*/

public class Calculator {

	private double result;

	public Calculator() { /* constructor for create object */
		result = 0;
	}

	public void sum(double a,double b) {		
			result= a+b;
	}

	public void pow(double a, double b) {
		result = Math.pow(a, b);
	}

	public double getConstPI() {
		result = Math.PI;
		return result;
	}

	public void multipl(double a, double b) {
		result = a*b;
	}

	public void cleanResult() {
		this.result = 0;
	}

	public double getResult() {
		return result;
	}

}

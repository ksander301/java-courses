/*simple calculator*/

public class Calculator {

    private double result;


    public Calculator() {
        result = 0;
    }

    /*
    * Calcuate operation SUM
    */
    public void sum(double first, double second) {
        result = first + second;
    }

    /*
    *Calculate operation involution
    */
    public void pow(double a, double b) {
        result = Math.pow(a, b);
    }

    /*
    * Get constant PI
    */
    public double getConstPI() {
        result = Math.PI;
        return result;
    }
    /*
    *Calculate operation multiplication
    */

    public void mult(double a, double b) {
        result = a * b;
    }

    public void cleanResult() {
        this.result = 0;
    }

    public double getResult() {
        return result;
    }
}

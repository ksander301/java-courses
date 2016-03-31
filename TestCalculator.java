import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.JUnitCore;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;

public class TestCalculator {

    public static void main(String[] args) {
        JUnitCore junitCore = new JUnitCore();
        junitCore.addListener(new CalculatorListener());
        junitCore.run(TestCalculator.class);
    }

    @Test
    public void getSumTest() {
        Calculator calculator = new Calculator();
        calculator.sum(1, 1);
        assertEquals(2, calculator.getResult(), 0.0);
        calculator.cleanResult();
    }

    @Test
    public void getMultTest() {
        Calculator calculator = new Calculator();
        calculator.mult(2.3, 3);
        assertEquals(6.9, calculator.getResult(), 0.0);
        calculator.cleanResult();
    }


    @Test
    public void getPowTest() {
        Calculator calculator = new Calculator();
        calculator.pow(1.2, 2);
        assertEquals(1.44, calculator.getResult(), 0.0);
        calculator.cleanResult();
    }

    @Test
    public void getPiTest() {
        Calculator calculator = new Calculator();
        assertEquals(3.141592653589793, calculator.getConstPI(), 0.0);
        calculator.cleanResult();
    }
}

class CalculatorListener extends RunListener {


    @Override
    public void testStarted(Description desc) {
        System.out.println("Started:" + desc.getDisplayName());
    }

    @Override
    public void testFinished(Description desc) {
        System.out.println("Finished:" + desc.getDisplayName());
    }

    @Override
    public void testFailure(Failure fail) {
        System.out.println("Failed: " + fail.getDescription().getDisplayName() + " [" + fail.getMessage() + "]");
    }
}
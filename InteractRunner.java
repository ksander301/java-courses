/**
 * Created by Ksander on 15.03.16.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Double.valueOf;

public class InteractRunner {

    private double first, second; 
    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private String exit, command; 
    private Calculator calculator;
	
	/* 	first,second - store values for calculate 
		exit - store command yes/no for end;
		command - store № of operation
	*/
	
	
    public InteractRunner() {
        calculator = new Calculator();
    }

    public static void main(String[] args) throws IOException {
        InteractRunner interactRunner = new InteractRunner();
        interactRunner.startCalculator();
    }

    /* Create object of Calculator, start the method inputCommand*/
    private void startCalculator() throws IOException {
        Calculator calc = new Calculator();
        this.inputCommand();
    }

    /* Offer  one of available command to insert,
    check the correctness of input command ( call method validateCommand),
    call executeOperation method to calculate ...*/

    private void inputCommand() throws IOException {
        System.out.println("Insert command:");
        System.out.println("1 - sum numbers\n" + "2 - multiplication\n"
                + "3 - involution\n" + "4 - get const PI");
        command = in.readLine();
        if (!validateCommand(command)) {
            System.out.println("The command is incorrect!Try again...");
            this.inputCommand();
        } else
            this.executeOperation(command);

    }

    /* check that input  commad will be one of available*/
    public boolean validateCommand(String command) {
        boolean result = false;
        if (command.equals("1") || command.equals("2") || command.equals("3") || command.equals("4"))
            result = true;
        return result;

    }

   /* Recognize the operation command,
    to initialiaze values for callculate by calling method initValues,
    call validateExit method   */

    public void executeOperation(String command) throws IOException {
        if (command.equals("4")) {
            System.out.println("Constant Pi=" + calculator.getConstPI());
            calculator.cleanResult();
        } else {
            if (initValues()) {
                switch (command) {
                    case "1":
                        calculator.sum(first, second);
                        System.out.println("A+B=" + calculator.getResult());
                        calculator.cleanResult();
                        break;
                    case "2":
                        calculator.mult(first, second);
                        System.out.println("A*B=" + calculator.getResult());
                        calculator.cleanResult();
                        break;
                    case "3":
                        calculator.pow(first, second);
                        System.out.println("A^b=" + calculator.getResult());
                        calculator.cleanResult();
                        break;
                }
            }
        }
        this.validateExit();
    }

    /* initialize variables for calculate, check correctness input */
    public boolean initValues() throws IOException {
        boolean result = true;
        try {
            System.out.println("Insert value A:");
            first = (double) valueOf(in.readLine());
            System.out.println("Insert value B:");
            second = (double) valueOf(in.readLine());
        } catch (Exception e) {
            System.out.println("Error! " + e.toString());
            System.out.println("Correct values format: *.*");
            result = false;
        }
        return result;
    }

    /* offer to unput command for exit*/
    private void validateExit() throws IOException {
        System.out.println("Exit from Calculator? (yes/no)");
        String exit = in.readLine();
        if (!this.recognizeExitCmd(exit))
            this.validateExit();
        else if (exit.equals("yes"))
            this.exitCalculator();
        else
            this.inputCommand();
    }

    /*check correctness of exit command*/
    private boolean recognizeExitCmd(String exit) {
        boolean result = false;
        if (exit.equals("yes") || exit.equals("no"))
            result = true;
        return result;
    }

    /* close the input stream */
    private void exitCalculator() throws IOException {
        in.close();
    }
}
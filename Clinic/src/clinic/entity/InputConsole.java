package clinic.entity;

import java.util.Scanner;

public class InputConsole implements Input {
    private final Scanner scanner;
    private final Output output;

    public InputConsole(Output output) {
        this.scanner = new Scanner(System.in);
        this.output = output;
    }

    @Override
    public String next() {
        return scanner.nextLine();
    }

    @Override
    public String ask(String question) {
        output.Println(question);
        return scanner.nextLine();
    }

    @Override
    public int getValidNumber(String question, int minNumber, int maxNumber) {
        output.Println(question);
        int validNumber = -1;
        boolean success = false;
        while (!success) {
            try {
                validNumber = Integer.decode(scanner.nextLine());
                if (validNumber >= minNumber && validNumber <= maxNumber)
                    success = true;
                else
                    output.Println("The number is NOT VALID. Try again");

            } catch (Exception e) {
                output.Println(" Error.Incorrect number format");
            }
        }
        return validNumber;
    }

    @Override
    public void close() {
        this.scanner.close();
    }
}

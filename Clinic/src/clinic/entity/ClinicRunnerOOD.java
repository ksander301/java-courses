package clinic.entity;

import java.io.IOException;

public class ClinicRunnerOOD {

    private  Clinic clinic;
    private String command;
    private  Input input;
    private  Output output;
    public static int COMMAND_FROM = 1;
    public static int COMMAND_TO = 11;
    public static String ACTION_START = "0";

    public static void main(String[] args) throws IOException {
        OutputConsole outputConsole=new OutputConsole();
        InputConsole inputConsole =new InputConsole(outputConsole);
        Clinic clinic = new Clinic("Lovely Pets");
        ClinicRunnerOOD clinicRunnerOOD = new ClinicRunnerOOD(inputConsole,outputConsole,clinic);
        clinicRunnerOOD.doAction();
    }

    public ClinicRunnerOOD(Input input,Output output, Clinic clinic) {
        this.clinic = clinic;
        this.output = output;
        this.input = input;
    }

    public void doAction() {
        String exit = "no";
        do {
            clinic.doAction(ACTION_START, clinic, input, output);
            command = String.valueOf(input.getValidNumber("Input command:", COMMAND_FROM, COMMAND_TO));
            clinic.doAction(command, clinic, input, output);
            exit = input.ask("Exit from Clinic ? [yes/no]");
        } while (!exit.equals("yes"));
        this.input.close();
    }


}

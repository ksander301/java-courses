package clinic.test;

import clinic.entity.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.JUnitCore;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class ClinicRunnerOODTest {


    public static void main(String[] args) throws IOException {

        JUnitCore junitCore = new JUnitCore();
        junitCore.addListener(new InteractRunnerListener());
        junitCore.run(ClinicRunnerOODTest.class);
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void addOneClient() {
        //assign
        final Output output = new OutputStud();
        final Input input = new InputStub(Arrays.asList("3", "Alex", "11", "yes").iterator(), output);
        final Clinic clinic = new Clinic("Test Clinic");
        final ClinicRunnerOOD clinicRunnerOOD = new ClinicRunnerOOD(input, output, clinic);
        //action
        clinicRunnerOOD.doAction();
        input.close();
        //assert
        assertNotNull(clinic.getAllCients());
        assertEquals(1, clinic.getCountClients());
    }

    @Test
    public void DeleteNotExistClient() {
        //assign
        final Output output = new OutputStud();
        final Input input = new InputStub(Arrays.asList("3", "Alex", "11", "n", "4", "2", "yes").iterator(), output);
        final Clinic clinic = new Clinic("Test Clinic");
        final ClinicRunnerOOD clinicRunnerOOD = new ClinicRunnerOOD(input, output, clinic);
        thrown.expect(IndexOutOfBoundsException.class);

        //action
        clinicRunnerOOD.doAction();
        input.close();

    }

    @Test
    public void invalidClientAge() {
        //assign
        final Output output = new OutputStud();
        final Input input = new InputStub(Arrays.asList("3", "Alex", "gfg").iterator(), output);
        final Clinic clinic = new Clinic("Test Clinic");
        final ClinicRunnerOOD clinicRunnerOOD = new ClinicRunnerOOD(input, output, clinic);
        thrown.expect(NumberFormatException.class);
        //action
        clinicRunnerOOD.doAction();
        input.close();
    }
}

class InteractRunnerListener extends RunListener {
    @Override
    public void testFailure(Failure fail) {
        System.out.println("Failed: " + fail.getDescription().getDisplayName() + " [" + fail.getMessage() + "] " + fail.getException());
    }
}
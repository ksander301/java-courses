package ru.lesson.Clinic;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.JUnitCore;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

import java.io.IOException;
import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;


public class ClinicRunnerTest {


  /*  public static void main(String[] args) throws IOException {

        JUnitCore junitCore = new JUnitCore();
        junitCore.addListener(new InteractRunnerListener());
        junitCore.run(ClinicRunnerTest.class);
    }*/

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void addClient() {
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
    public void removeClient() {
        //assign
        final Output output = new OutputStud();
        final Input input = new InputStub(Arrays.asList("3", "Alex", "11", "no", "3", "John", "11", "df", "4", "0", "yes").iterator(), output);
        final Clinic clinic = new Clinic("Test Clinic");
        final ClinicRunnerOOD clinicRunnerOOD = new ClinicRunnerOOD(input, output, clinic);
        //action
        clinicRunnerOOD.doAction();
        input.close();
        //assert
        assertEquals(1, clinic.getCountClients());
        assertThat("John", is(clinic.getAllCients().get(0).getClientName()));
    }

    @Test
    public void testAddAnimal() {
        //assign
        final Output output = new OutputStud();
        final Input input = new InputStub(Arrays.asList("3", "Alex", "11", "no", "5","0","2", "CatCat", "yes").iterator(), output);
        final Clinic clinic = new Clinic("Test Clinic");
        final ClinicRunnerOOD clinicRunnerOOD = new ClinicRunnerOOD(input, output, clinic);
        //action
        clinicRunnerOOD.doAction();
        input.close();
        //assert
        assertEquals(clinic.getCountAnimals(), 1);
        assertNotNull(clinic.getAllCients().get(0).getListAnimal().get(0));
    }

    @Test
    public void testDeleteAnimal() {
        //assign
        final Output output = new OutputStud();
        final Input input = new InputStub(Arrays.asList("3", "Alex", "11", "no", "5", "0", "2", "CatCat", "dsd", "5", "0", "1", "DogDog", "no", "6", "0","0", "yes").iterator(), output);
        final Clinic clinic = new Clinic("Test Clinic");
        final ClinicRunnerOOD clinicRunnerOOD = new ClinicRunnerOOD(input, output, clinic);
        //action
        clinicRunnerOOD.doAction();
        input.close();
        //assert
        assertThat("DogDog", is(clinic.getAllCients().get(0).getListAnimal().get(0).getName()));
    }

    @Test
    public void testEditlient() {
        //assign
        final Output output = new OutputStud();
        final Input input = new InputStub(Arrays.asList("3", "Alex", "11", "no", "9", "0", "Bill", "18", "yes").iterator(), output);
        final Clinic clinic = new Clinic("Test Clinic");
        final ClinicRunnerOOD clinicRunnerOOD = new ClinicRunnerOOD(input, output, clinic);
        //action
        clinicRunnerOOD.doAction();
        input.close();
        //assert
        assertThat("Bill", is(clinic.getAllCients().get(0).getClientName()));
        assertThat(18, is(clinic.getAllCients().get(0).getClientAge()));
    }

    @Test
    public void testEditAnimal(){
        //assign
        final Output output = new OutputStud();
        final Input input = new InputStub(Arrays.asList("3", "Alex", "11", "no", "5","0", "2", "CatCat","no","10","0","0","CatTest", "yes").iterator(), output);
        final Clinic clinic = new Clinic("Test Clinic");
        final ClinicRunnerOOD clinicRunnerOOD = new ClinicRunnerOOD(input, output, clinic);
        //action
        clinicRunnerOOD.doAction();
        input.close();
        //assert
        assertThat("CatTest", is (clinic.getAllCients().get(0).getListAnimal().get(0).getName()));
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
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

import java.io.IOException;
import java.util.Comparator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class TestInteractRunner {
    public static void main(String[] args) {
        JUnitCore junitCore = new JUnitCore();
        junitCore.addListener(new InteractRunnerListener());
        junitCore.run(Request.aClass(TestInteractRunner.class).sortWith(forward()));
        //junitCore.run(TestInteractRunner.class);
    }

    @Test
    public void test01ValidateCommand() {
        InteractRunner interactRunner = new InteractRunner();
        assertTrue("Failed method ValidateCommand ", interactRunner.validateCommand("dfgs"));


    }

    @Test
    public void test03ValidateCommand() {
        InteractRunner interactRunner = new InteractRunner();
        assertTrue("Failed method ValidateCommand ", interactRunner.validateCommand("1") == true);
    }

    @Test
    public void test02recognizeExitCmd() {
        InteractRunner interactRunner = new InteractRunner();
        assertTrue("Failed method recognizeExitCmd ", interactRunner.recognizeExitCmd("1"));
    }


    public static Comparator forward() {
        return new Comparator() {

            public int compare(Object object1, Object object2) {
                Description desc1 = (Description) object1;
                Description desc2 = (Description) object2;
                return desc1.getDisplayName().compareTo(desc2.getDisplayName());
            }
        };
    }
}

class InteractRunnerListener extends RunListener {
    @Override
    public void testStarted(Description desc) {
        System.out.println("Started:" + desc.getDisplayName());
    }

    @Override
    public void testFinished(Description desc) {
        System.out.println("Finished:" + desc.getDisplayName());
        System.out.println();
    }

    @Override
    public void testFailure(Failure fail) {
        System.out.println("Failed: " + fail.getDescription().getDisplayName() + " [" + fail.getMessage() + "]");
    }
}
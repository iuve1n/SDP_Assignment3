import notifications.Channel;
import notifications.EmailChannel;
import notifications.Notification;
import notifications.Reminder;
import notifications.SmsChannel;
import notifications.UrgentAlert;

public class Main {
    private static int passedChecks;
    private static int totalChecks;

    public static void main(String[] args) {
        if (args.length == 1 && "--demo".equals(args[0])) {
            runDemo();
            return;
        }

        System.out.println("Run with --demo to execute the assignment checks.");
    }

    private static void runDemo() {
        Channel email = new EmailChannel();
        Channel sms = new SmsChannel();

        runCheck(
                "T1",
                "Reminder + EmailChannel",
                new Reminder("REM-101", "Submit the design pattern assignment", email),
                "EMAIL envelope [id=REM-101, message=Reminder: Submit the design pattern assignment]"
        );
        runCheck(
                "T2",
                "Reminder + SmsChannel",
                new Reminder("REM-101", "Submit the design pattern assignment", sms),
                "SMS [id=REM-101, message=Reminder: Submit the design pattern assignment]"
        );
        runCheck(
                "T3",
                "UrgentAlert + EmailChannel",
                new UrgentAlert("ALT-201", "Server room temperature is high", email),
                "EMAIL envelope [id=ALT-201, message=URGENT: Server room temperature is high]"
        );
        runCheck(
                "T4",
                "UrgentAlert + SmsChannel",
                new UrgentAlert("ALT-201", "Server room temperature is high", sms),
                "SMS [id=ALT-201, message=URGENT: Server room temperature is high]"
        );
        runSwitchCheck(email, sms);

        System.out.println("SUMMARY: " + passedChecks + "/" + totalChecks + " PASS");
    }

    private static void runCheck(
            String checkId,
            String participants,
            Notification notification,
            String expected
    ) {
        String actual = notification.execute();
        boolean passed = expected.equals(actual);
        recordResult(checkId, participants, passed, "result=" + actual, expected);
    }

    private static void runSwitchCheck(Channel email, Channel sms) {
        Reminder original = new Reminder("REM-301", "Bring a student ID", email);
        Notification activeReference = original;
        String originalId = original.getId();
        String originalMessage = original.getMessage();
        String before = activeReference.execute();

        activeReference.setImplementation(sms);
        String after = activeReference.execute();

        boolean sameObject = original == activeReference;
        boolean stateUnchanged = originalId.equals(activeReference.getId())
                && originalMessage.equals(activeReference.getMessage());
        String expectedBefore =
                "EMAIL envelope [id=REM-301, message=Reminder: Bring a student ID]";
        String expectedAfter = "SMS [id=REM-301, message=Reminder: Bring a student ID]";
        boolean passed = sameObject
                && stateUnchanged
                && expectedBefore.equals(before)
                && expectedAfter.equals(after);
        String actual = "sameObject=" + sameObject
                + " | stateUnchanged=" + stateUnchanged
                + " | before=" + before
                + " | after=" + after;
        String expected = "sameObject=true | stateUnchanged=true | before="
                + expectedBefore + " | after=" + expectedAfter;

        recordResult(
                "T5",
                "Reminder: EmailChannel -> SmsChannel",
                passed,
                actual,
                expected
        );
    }

    private static void recordResult(
            String checkId,
            String participants,
            boolean passed,
            String actual,
            String expected
    ) {
        totalChecks++;
        if (passed) {
            passedChecks++;
        }

        System.out.println(
                checkId + " " + (passed ? "PASS" : "FAIL")
                        + " | " + participants
                        + " | " + actual
        );
        if (!passed) {
            System.out.println("expected=" + expected);
        }
    }
}

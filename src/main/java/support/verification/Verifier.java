package support.verification;

public class Verifier {

    public static void verifyEquals(String actual, String expected){
        if (!actual.equals(expected)){
            System.out.printf("Actual is: %s\nExpected is: %s\n", actual, expected);
            throw new AssertionError("Expected and actual different");
        }

    }
}

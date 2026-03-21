package edu.eci.dosw.tdd.core.util;

public class IdGeneratorUtil {

    private static int bookCounter = 1;
    private static int userCounter = 1;
    private static int loanCounter = 1;

    public static String generateBookId() {
        return String.valueOf(bookCounter++);
    }

    public static String generateUserId() {
        return String.valueOf(userCounter++);
    }

    public static String generateLoanId() {
        return String.valueOf(loanCounter++);
    }
}
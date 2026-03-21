package edu.eci.dosw.tdd.core.util;

public class ValidationUtil {

    public static boolean isNotBlank(String str) {
        return str != null && !str.trim().isEmpty();
    }

    public static boolean isPositive(Integer number) {
        return number != null && number > 0;
    }
}
package com.hepsiburada.common.util;

public class StringOperationUtil {

    public static void checkStringEmptyOrNull(String string) {
        if (string == null || string.isEmpty() || string.trim().isEmpty())
            throw new NullPointerException();
    }
}

package org.file;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validates {
    public static Boolean isvalidatePin(String pin) {
        String expression="^\\d{6}$";
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher=pattern.matcher(pin);
        return matcher.matches();
    }
}

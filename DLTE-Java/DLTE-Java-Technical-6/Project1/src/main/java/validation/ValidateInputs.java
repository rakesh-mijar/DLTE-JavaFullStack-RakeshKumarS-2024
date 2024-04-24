package validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateInputs {
    public static Boolean isvalidatePhone(Long number) {
        String contactString=Long.toString(number);
        String expression="\\d{10}";
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher=pattern.matcher(contactString);
        return matcher.matches();
    }
    public static Boolean isvalidatePin(int pin) {
        String expression="^\\d{6}$";
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher=pattern.matcher(String.valueOf(pin));
        return matcher.matches();
    }
}
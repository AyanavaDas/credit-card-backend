package com.socgen.creditcardbackend.util;

import java.util.regex.Pattern;

public class CustomerValidation {
    public static Boolean ValidateEmail(String email)
    {
        return Pattern.compile("^(.+)@(\\S+)$").matcher(email).matches();
    }

    public static Boolean ValidateNumber(String number)
    {
        return number.length() == 10;
    }
}

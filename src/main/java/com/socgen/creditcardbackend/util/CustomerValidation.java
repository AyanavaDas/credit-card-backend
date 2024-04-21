package com.socgen.creditcardbackend.util;

import java.util.regex.Pattern;

public class CustomerValidation {
    public static Boolean ValidateEmail(String email)
    {
        if(email!=null)
            return Pattern.compile("^(.+)@(\\S+)$").matcher(email).matches();
        else
            return false;
    }

    public static Boolean ValidateNumber(String number)
    {
        return number.length() == 10;
    }
}

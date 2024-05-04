package com.socgen.creditcardbackend.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordValidation {

    public static String encodePassword(String password)
    {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(8);
        return encoder.encode(password);
    }

    public static Boolean isValidPassword(String actual,String userInput)
    {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(8);
        return encoder.matches(userInput,actual);
    }
}

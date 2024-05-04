package com.socgen.creditcardbackend.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PasswordValidationTest {

    @Test
    public  void ShouldMatchEncodedBCryptEncoderPassword()
    {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(8);
        String result = encoder.encode("myPassword");
        assertTrue(encoder.matches("myPassword", result));
        assertFalse(encoder.matches("mypassword",result));
    }

}

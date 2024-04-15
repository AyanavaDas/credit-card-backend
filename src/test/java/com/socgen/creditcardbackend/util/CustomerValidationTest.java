package com.socgen.creditcardbackend.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CustomerValidationTest {

    @Test
    public void ShouldReturnTrueWhenEmailIsCorrectFormat()
    {
        assertTrue(CustomerValidation.ValidateEmail("ab@email.com"));

    }

    @Test
    public void ShouldReturnFalseWhenEmailIsIncorrectFormat()
    {
        assertFalse(CustomerValidation.ValidateEmail("ab.email.com"));
        assertFalse(CustomerValidation.ValidateEmail("abemailcom"));
    }

    @Test
    public void ShouldInvalidateWhenPhoneNumberIsIncorrectFormat()
    {
        assertFalse(CustomerValidation.ValidateNumber("123456789"));
        assertFalse(CustomerValidation.ValidateNumber("12345678901"));
    }

    @Test
    public void ShouldValidateWhenPhoneNumberIsCorrectFormat()
    {
        assertTrue(CustomerValidation.ValidateNumber("1234567890"));
    }
}

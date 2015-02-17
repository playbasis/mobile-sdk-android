package com.playbasis.android.playbasissdk.helper;

import junit.framework.TestCase;

import org.junit.Test;

public class ValidatorTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();

    }

    public void tearDown() throws Exception {

    }

    public void testIsValid() throws Exception {
        assertTrue(Validator.isValid("test"));
        assertFalse(Validator.isValid(" "));
        assertFalse(Validator.isValid(""));
        assertFalse(Validator.isValid("null"));
        assertFalse(Validator.isValid(null));
    }
    
    public void testIsValidAlphaNum() throws Exception{
        assertTrue(Validator.isValidAlphaNum("qwerRTREYT6543_52b-vn_-gsdfg"));
        assertFalse(Validator.isValidAlphaNum("Qw34%"));
        assertFalse(Validator.isValidAlphaNum(")"));
        assertFalse(Validator.isValidAlphaNum("@"));
        assertFalse(Validator.isValidAlphaNum("f."));
        assertFalse(Validator.isValidAlphaNum(null));
        assertFalse(Validator.isValidAlphaNum(""));

    }


    public void testIsValidEmail() throws Exception{
        assertTrue(Validator.isValidEmail("greg@sdf.gt"));
        assertTrue(Validator.isValidEmail("gr.eg@sdf.gt"));
        assertTrue(Validator.isValidEmail("g.re.g@sdf.gt"));
        assertTrue(Validator.isValidEmail("gr3eg_@sdf.gt"));
        assertTrue(Validator.isValidEmail("gr3eg_@s.df.gt"));
        assertTrue(Validator.isValidEmail("gr3eg_@s-df.gt"));
        assertFalse(Validator.isValidEmail("gr3eg_@s-df"));
        assertFalse(Validator.isValidEmail("@s.df"));
        assertFalse(Validator.isValidEmail("asfd@.df"));
        assertFalse(Validator.isValidEmail("asfd@"));
        assertFalse(Validator.isValidEmail("asfd@dfgf"));
        assertFalse(Validator.isValidEmail(""));
        assertFalse(Validator.isValidEmail(null));


    }
}
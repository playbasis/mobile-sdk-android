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
}
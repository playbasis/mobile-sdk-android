package com.playbasis.android.playbasissdk.helper;

import junit.framework.TestCase;

public class StringHelperTest extends TestCase {

    public void testRemoveLastChar() throws Exception {

        assertEquals("foo", StringHelper.removeLastChar("foo,"));
        assertEquals("fo", StringHelper.removeLastChar("foo"));
        assertEquals("", StringHelper.removeLastChar(""));
        assertEquals("", StringHelper.removeLastChar("f"));
        assertNull(StringHelper.removeLastChar(null));

    }
}
package com.brad;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;


/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase
{
    static final String RANDOM_STRING = "sdfsdf";
    static final String ENGLISH_LANGUAGE = "english";
    static final String SERVICE_ENGLISH_LANGUAGE = "en";
    static final String RUSSIAN_LANGUAGE = "russian";
    static final String SERVICE_RUSSIAN_LANGUAGE = "ru";

    public void testSanityCheck()
    {
        assertTrue( true );
    }

    public void testEnglishMapping(){
        assertTrue(LanguageMapper.map(ENGLISH_LANGUAGE) == SERVICE_ENGLISH_LANGUAGE);
    }

    public void testRussianMapping(){
        assertTrue(LanguageMapper.map(RUSSIAN_LANGUAGE) == SERVICE_RUSSIAN_LANGUAGE);
    }
}

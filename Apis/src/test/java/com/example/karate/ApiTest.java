package com.example.karate;

import com.intuit.karate.junit5.Karate;

/**
 * Test runner for Karate API tests.  All feature files located under
 * classpath:karate/features will be executed.  You can run this class
 * directly via Maven Surefire or your IDE.
 */
public class ApiTest {

    @Karate.Test
    Karate runAll() {
        return Karate.run("classpath:karate/features").relativeTo(getClass());
    }
}
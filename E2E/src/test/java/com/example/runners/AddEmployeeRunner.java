package com.example.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

/**
 * Cucumber runner for the add employee scenario.  Serenity integrates
 * seamlessly with JUnit to provide rich reports.  The tags option could be
 * used to selectively run scenarios.
 */
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "classpath:features/add_employee.feature",
        glue = "com.example.stepdefinitions",
        plugin = {"pretty"}
)
public class AddEmployeeRunner {
}
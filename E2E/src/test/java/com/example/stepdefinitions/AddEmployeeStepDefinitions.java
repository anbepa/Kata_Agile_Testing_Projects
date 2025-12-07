package com.example.stepdefinitions;

import com.example.tasks.AddEmployee;
import com.example.tasks.Login;
import com.example.tasks.SearchEmployee;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.steps.UIInteractionSteps;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.annotations.Managed;
import org.openqa.selenium.WebDriver;

/**
 * Step definitions that glue the feature file to the Screenplay tasks. Each
 * step calls the corresponding Screenplay task or assertion. The actor
 * instance persists across steps in the scenario via a member field.
 */
public class AddEmployeeStepDefinitions extends UIInteractionSteps {

    @Managed
    WebDriver driver;

    private Actor actor;

    @Before
    public void setTheStage() {
        actor = Actor.named("Admin");
        actor.can(BrowseTheWeb.with(driver));
    }

    @Given("the admin user logs into OrangeHRM")
    public void the_admin_user_logs_in() {
        java.util.Properties props = new java.util.Properties();
        try (java.io.InputStream input = getClass().getClassLoader().getResourceAsStream("automation.properties")) {
            if (input != null) {
                props.load(input);
            }
        } catch (java.io.IOException ex) {
            ex.printStackTrace();
        }

        String username = props.getProperty("username", "Admin");
        String password = props.getProperty("password", "admin123");
        String baseUrl = props.getProperty("base.url", "https://opensource-demo.orangehrmlive.com/");

        actor.attemptsTo(Login.withCredentials(username, password, baseUrl));
    }

    @When("they add a new employee with first name {string}, last name {string} and a profile photo")
    public void they_add_a_new_employee(String firstName, String lastName) {
        String fullName = firstName + " " + lastName;

        actor.remember("FIRST_NAME", firstName);
        actor.remember("FULL_NAME", fullName);

        // Use a default profile picture from resources called profile.jpg
        actor.attemptsTo(AddEmployee.withDetails(firstName, lastName, "profile.jpg"));
    }

    @When("they search for the employee named {string} in the directory")
    public void they_search_for_employee(String fullName) {
        String uniqueFirstName = actor.recall("FIRST_NAME");
        if (uniqueFirstName == null)
            uniqueFirstName = fullName.split(" ")[0]; // Fallback

        actor.attemptsTo(SearchEmployee.searchFor(uniqueFirstName));
    }

    @Then("the employee {string} should appear in the search results")
    public void the_employee_should_appear(String fullName) {
        String actualName = actor.recall("FULL_NAME");
        if (actualName == null)
            actualName = fullName; // Fallback

        // Assert using the custom Question
        actor.should(
                net.serenitybdd.screenplay.GivenWhenThen.seeThat(
                        com.example.questions.EmployeeAppears.inTheDirectory(actualName)));
    }
}
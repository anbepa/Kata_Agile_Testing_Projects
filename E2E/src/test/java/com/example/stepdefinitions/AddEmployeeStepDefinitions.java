package com.example.stepdefinitions;

import com.example.tasks.AddEmployee;
import com.example.tasks.Login;
import com.example.tasks.SearchEmployee;
import com.example.ui.DirectoryPage;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.steps.UIInteractionSteps;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.questions.Visibility;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;

/**
 * Step definitions that glue the feature file to the Screenplay tasks.  Each
 * step calls the corresponding Screenplay task or assertion.  The actor
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
        String username = System.getProperty("orangehrm.username", "Admin");
        String password = System.getProperty("orangehrm.password", "admin123");
        actor.attemptsTo(Login.withCredentials(username, password));
    }

    @When("they add a new employee with first name \"{string}\" and last name \"{string}\"")
    public void they_add_a_new_employee(String firstName, String lastName) {
        // Use a default profile picture from resources called profile.jpg
        actor.attemptsTo(AddEmployee.withDetails(firstName, lastName, "profile.jpg"));
    }

    @When("they upload a profile photo for the employee")
    public void they_upload_profile_photo() {
        // Photo upload is handled inside the AddEmployee task; nothing to do here.
    }

    @When("they search for the employee named \"{string}\" in the directory")
    public void they_search_for_employee(String fullName) {
        actor.attemptsTo(SearchEmployee.called(fullName));
    }

    @Then("the employee \"{string}\" should appear in the search results")
    public void the_employee_should_appear(String fullName) {
        // Build a dynamic locator for the result tile and assert its visibility.
        actor.attemptsTo(
                Ensure.that(DirectoryPage.RESULT_BY_NAME.of(fullName)).isDisplayed()
        );
    }
}
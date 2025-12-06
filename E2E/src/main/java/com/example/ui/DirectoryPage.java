package com.example.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

/**
 * Targets for the Directory search page.  The page contains a search form
 * allowing users to filter by employee name, job title and location.  Only
 * the employee name field and the search button are needed for the kata.
 */
public class DirectoryPage {
    /** Input for employee name. Uses placeholder to distinguish from other fields. */
    public static final Target EMPLOYEE_NAME_INPUT = Target.the("employee name input")
            .located(By.xpath("//input[@placeholder='Type for hints...']"));

    /** The Search button on the directory search form. */
    public static final Target SEARCH_BUTTON = Target.the("search button")
            .located(By.xpath("//button[contains(.,'Search')]"));

    /**
     * A dynamic target representing a result tile containing a particular
     * employee name.  In Screenplay you can resolve a Target by passing
     * parameters (see Target#of).  This allows us to search for a given
     * employee name in the results list.
     */
    public static final Target RESULT_BY_NAME = Target.the("result card for {0}")
            .locatedBy("//div[contains(@class,'oxd-directory-card')]//p[contains(normalize-space(text()), '{0}')]");
}
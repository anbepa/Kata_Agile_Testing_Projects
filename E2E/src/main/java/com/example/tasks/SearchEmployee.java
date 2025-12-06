package com.example.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.annotations.Subject;

import com.example.ui.MainMenu;
import com.example.ui.DirectoryPage;

/**
 * Navigates to the Directory module and searches for an employee by name.  The
 * task opens the Directory from the main menu, enters the supplied search
 * query into the employee name filter and clicks the search button.
 */
@Subject("search for an employee in the directory")
public class SearchEmployee implements Task {
    private final String fullName;

    public SearchEmployee(String fullName) {
        this.fullName = fullName;
    }

    public static SearchEmployee called(String fullName) {
        return new SearchEmployee(fullName);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(MainMenu.DIRECTORY_MENU),
                Enter.theValue(fullName).into(DirectoryPage.EMPLOYEE_NAME_INPUT),
                Click.on(DirectoryPage.SEARCH_BUTTON)
        );
    }
}
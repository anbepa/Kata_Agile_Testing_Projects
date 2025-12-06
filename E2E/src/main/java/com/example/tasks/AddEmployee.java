package com.example.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Upload;
import net.serenitybdd.screenplay.annotations.Subject;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.example.ui.MainMenu;
import com.example.ui.PimPage;
import com.example.ui.AddEmployeePage;

/**
 * Adds a new employee via the PIM module.  The flow selects the PIM menu,
 * navigates to the Add Employee tab, enters the supplied first and last
 * names and uploads a profile photograph.  The photograph is loaded from
 * the classpath under src/test/resources/images.  After entering the
 * information the task clicks the Save button.
 */
@Subject("add a new employee")
public class AddEmployee implements Task {
    private final String firstName;
    private final String lastName;
    private final String photoFileName;

    public AddEmployee(String firstName, String lastName, String photoFileName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.photoFileName = photoFileName;
    }

    public static AddEmployee withDetails(String firstName, String lastName, String photoFileName) {
        return new AddEmployee(firstName, lastName, photoFileName);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        // Build the absolute path to the photo in the resources directory.  Using
        // Paths.get will work across operating systems.
        Path photo = Paths.get("src/test/resources/images", photoFileName);
        actor.attemptsTo(
                Click.on(MainMenu.PIM_MENU),
                Click.on(PimPage.ADD_EMPLOYEE_TAB),
                Enter.theValue(firstName).into(AddEmployeePage.FIRST_NAME_FIELD),
                Enter.theValue(lastName).into(AddEmployeePage.LAST_NAME_FIELD),
                // Upload the file by sending the path to the hidden input
                Upload.theFile(photo).to(AddEmployeePage.UPLOAD_INPUT),
                Click.on(AddEmployeePage.SAVE_BUTTON)
        );
    }
}
package com.example.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Upload;
import net.serenitybdd.screenplay.annotations.Subject;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.example.userinterfaces.MainMenu;
import com.example.userinterfaces.PimPage;
import com.example.userinterfaces.AddEmployeePage;
import com.example.userinterfaces.PersonalDetailsPage;

/**
 * Tarea para agregar un nuevo empleado en el módulo PIM
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
        // Construir la ruta absoluta a la foto en el directorio de recursos
        Path photo = Paths.get("src/test/resources/images", photoFileName);
        actor.attemptsTo(
                Click.on(MainMenu.PIM_MENU),
                Click.on(PimPage.ADD_EMPLOYEE_TAB),
                Enter.theValue(firstName).into(AddEmployeePage.FIRST_NAME_FIELD),
                Enter.theValue(lastName).into(AddEmployeePage.LAST_NAME_FIELD),
                Upload.theFile(photo).to(AddEmployeePage.UPLOAD_INPUT),
                Click.on(AddEmployeePage.SAVE_BUTTON),
                net.serenitybdd.screenplay.waits.WaitUntil
                        .the(PersonalDetailsPage.PERSONAL_DETAILS_HEADER,
                                net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible())
                        .forNoMoreThan(10).seconds());
    }
}
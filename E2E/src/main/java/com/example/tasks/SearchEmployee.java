package com.example.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.annotations.Subject;

import com.example.userinterfaces.MainMenu;
import com.example.userinterfaces.DirectoryPage;

/**
 * Tarea para buscar un empleado en el Directorio
 */
@Subject("search for an employee in the directory")
public class SearchEmployee implements Task {
    private final String searchTerm;

    public SearchEmployee(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public static SearchEmployee called(String searchTerm) {
        return net.serenitybdd.screenplay.Tasks.instrumented(SearchEmployee.class, searchTerm);
    }

    public static SearchEmployee searchFor(String searchTerm) {
        return called(searchTerm);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(MainMenu.DIRECTORY_MENU),
                Enter.theValue(searchTerm).into(DirectoryPage.EMPLOYEE_NAME_INPUT),

                // Espera para que se inicialice el autocompletado
                net.serenitybdd.screenplay.Task.where("{0} waits for autocomplete cooldown",
                        actor1 -> {
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                            }
                        }),

                // Esperar a que aparezca el dropdown
                net.serenitybdd.screenplay.waits.WaitUntil.the(
                        net.serenitybdd.screenplay.targets.Target.the("dropdown")
                                .located(org.openqa.selenium.By.cssSelector(".oxd-autocomplete-dropdown")),
                        net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible()).forNoMoreThan(10)
                        .seconds(),

                // Esperar a que la primera opción sea visible
                net.serenitybdd.screenplay.waits.WaitUntil.the(
                        net.serenitybdd.screenplay.targets.Target.the("first autocomplete option")
                                .locatedBy(
                                        "//div[@role='listbox']//div[contains(@class,'oxd-autocomplete-option')][1]"),
                        net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible()).forNoMoreThan(10)
                        .seconds(),

                // Hacer clic en la primera opción de la lista
                Click.on(net.serenitybdd.screenplay.targets.Target.the("first autocomplete option")
                        .locatedBy("//div[@role='listbox']//div[contains(@class,'oxd-autocomplete-option')][1]")),

                Click.on(DirectoryPage.SEARCH_BUTTON));
    }
}
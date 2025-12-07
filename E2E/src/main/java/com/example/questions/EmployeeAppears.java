package com.example.questions;

import com.example.userinterfaces.DirectoryPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class EmployeeAppears implements Question<Boolean> {

    private final String fullName;

    public EmployeeAppears(String fullName) {
        this.fullName = fullName;
    }

    public static EmployeeAppears inTheDirectory(String fullName) {
        return new EmployeeAppears(fullName);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        DirectoryPage.RESULT_BY_NAME.of(fullName).resolveFor(actor)
                .withTimeoutOf(20, java.time.temporal.ChronoUnit.SECONDS).waitUntilVisible();
        return true;
    }
}

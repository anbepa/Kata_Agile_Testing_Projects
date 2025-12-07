package com.example.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.annotations.Subject;
import com.example.userinterfaces.LoginPage;

/**
 * Screenplay task that logs into the OrangeHRM application using the
 * administrator credentials. The credentials are passed in as arguments
 * rather than being hardcoded to encourage reuse and configurability.
 */
@Subject("log in as an administrator")
public class Login implements Task {
    private final String username;
    private final String password;
    private final String baseUrl;

    public Login(String username, String password, String baseUrl) {
        this.username = username;
        this.password = password;
        this.baseUrl = baseUrl;
    }

    public static Login withCredentials(String username, String password, String baseUrl) {
        return new Login(username, password, baseUrl);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String fullUrl = baseUrl + (baseUrl.endsWith("/") ? "" : "/") + "web/index.php/auth/login";
        actor.attemptsTo(
                Open.url(fullUrl),
                Enter.theValue(username).into(LoginPage.USERNAME_FIELD),
                Enter.theValue(password).into(LoginPage.PASSWORD_FIELD),
                Click.on(LoginPage.LOGIN_BUTTON));
    }
}
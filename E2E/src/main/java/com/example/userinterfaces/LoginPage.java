package com.example.userinterfaces;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

/**
 * Locators for the OrangeHRM login page. When using Screenplay you
 * encapsulate locators in Target objects which carry a human‑readable
 * description. These locators are based on the current structure of
 * https://opensource-demo.orangehrmlive.com but can be adjusted if the
 * underlying HTML changes.
 */
@DefaultUrl("/web/index.php/auth/login")
public class LoginPage extends PageObject {

        /**
         * Username input field.
         */
        public static final Target USERNAME_FIELD = Target.the("username field")
                        .located(By.name("username"));

        /**
         * Password input field.
         */
        public static final Target PASSWORD_FIELD = Target.the("password field")
                        .located(By.name("password"));

        /**
         * Login button. The login form uses a submit button to authenticate.
         */
        public static final Target LOGIN_BUTTON = Target.the("login button")
                        .located(By.cssSelector("button[type='submit']"));
}
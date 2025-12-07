package com.example.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

/**
 * Elements on the Personal Details page, which is displayed after successfully
 * adding a new employee.
 */
public class PersonalDetailsPage {
    /**
     * Header text identifying the Personal Details page.
     */
    public static final Target PERSONAL_DETAILS_HEADER = Target.the("Personal Details header")
            .located(By.xpath("//h6[text()='Personal Details']"));
}

package com.example.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

/**
 * Locators for the Add Employee form within the PIM module.  Fields are
 * identified by their "name" attributes when available.  A generic file
 * input is used for uploading the profile photograph.
 */
public class AddEmployeePage {
    /** Input for the first name. */
    public static final Target FIRST_NAME_FIELD = Target.the("first name field")
            .located(By.name("firstName"));

    /** Input for the middle name.  Optional. */
    public static final Target MIDDLE_NAME_FIELD = Target.the("middle name field")
            .located(By.name("middleName"));

    /** Input for the last name. */
    public static final Target LAST_NAME_FIELD = Target.the("last name field")
            .located(By.name("lastName"));

    /** Hidden file input used to upload the employee's photograph. */
    public static final Target UPLOAD_INPUT = Target.the("photo upload input")
            .located(By.cssSelector("input[type='file']"));

    /** Save button used to commit the new employee. */
    public static final Target SAVE_BUTTON = Target.the("save button")
            .located(By.xpath("//button[contains(.,'Save')]"));
}
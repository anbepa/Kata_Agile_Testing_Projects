package com.example.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

/**
 * Targets for the main sidebar navigation within OrangeHRM.  Each menu
 * item is represented as a Target so that actors can click on them.
 */
public class MainMenu {
    /** PIM module in the left hand navigation. */
    public static final Target PIM_MENU = Target.the("PIM menu item")
            .located(By.xpath("//span[text()='PIM']"));

    /** Directory module in the left hand navigation. */
    public static final Target DIRECTORY_MENU = Target.the("Directory menu item")
            .located(By.xpath("//span[text()='Directory']"));
}
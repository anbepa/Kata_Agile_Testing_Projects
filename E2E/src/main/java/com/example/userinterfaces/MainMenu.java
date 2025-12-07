package com.example.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

/**
 * Elementos del menú principal de navegación
 */
public class MainMenu {
        public static final Target PIM_MENU = Target.the("PIM menu item")
                        .located(By.xpath("//span[text()='PIM']"));

        public static final Target DIRECTORY_MENU = Target.the("Directory menu item")
                        .located(By.xpath("//span[text()='Directory']"));
}
package com.example.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

/**
 * Elementos de la página de búsqueda del Directorio
 */
public class DirectoryPage {

        public static final Target EMPLOYEE_NAME_INPUT = Target.the("employee name input")
                        .located(By.xpath("//input[@placeholder='Type for hints...']"));

        public static final Target SEARCH_BUTTON = Target.the("search button")
                        .located(By.xpath("//button[contains(.,'Search')]"));

        // Target dinámico para buscar un empleado específico en los resultados
        public static final Target RESULT_BY_NAME = Target.the("result card for {0}")
                        .locatedBy("//p[contains(@class, 'orangehrm-directory-card-header')][normalize-space()='{0}']");
}
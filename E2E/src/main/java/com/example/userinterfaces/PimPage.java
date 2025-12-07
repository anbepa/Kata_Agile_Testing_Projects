package com.example.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

/**
 * Elements related to the PIM module. Includes navigation to the Add
 * Employee tab from within the PIM module.
 */
public class PimPage {
        /**
         * Top tab for the Employee List within PIM. Not used in the flow but
         * provided for completeness.
         */
        public static final Target EMPLOYEE_LIST_TAB = Target.the("employee list tab")
                        .located(By.xpath("//a[contains(@href,'viewEmployeeList')]"));

        /**
         * Top tab used to navigate to the Add Employee form.
         */
        public static final Target ADD_EMPLOYEE_TAB = Target.the("add employee tab")
                        .located(By.xpath("//a[contains(.,'Add Employee')]"));
}
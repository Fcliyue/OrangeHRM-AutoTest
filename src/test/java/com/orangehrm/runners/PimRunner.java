package com.orangehrm.runners;

import com.orangehrm.listeners.RetryListener;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;

@Listeners(RetryListener.class)
@CucumberOptions(
        features = "src/test/resources/features/add_employee.feature",
        glue = {"com.orangehrm.stepdefinitions",
                "com.orangehrm.hooks"},
        plugin = {
                "html:target/cucumber-reports/PIMTestReport.html",
                "json:target/cucumber-reports/PIMTestReport.json",
                "pretty",
                "timeline:target/cucumber-reports/timeline/"
        },
        monochrome = true,
        tags = ""
)

public class PimRunner extends AbstractTestNGCucumberTests {
        @Override
        @DataProvider(parallel = true)
        public Object[][] scenarios(){
                return super.scenarios();
        }

}


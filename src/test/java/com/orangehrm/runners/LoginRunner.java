package com.orangehrm.runners;

import com.orangehrm.listeners.RetryListener;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;

@Listeners(RetryListener.class)
@CucumberOptions(
        features = "src/test/resources/features/user_login.feature",
        glue = {"com.orangehrm.stepdefinitions",
        "com.orangehrm.hooks"},
        plugin = {
                "html:target/cucumber-reports/LoginTestReport.html",
                "json:target/cucumber-reports/LoginTestReport.json",
                "pretty",
                "timeline:target/cucumber-reports/timeline/"
        },
        monochrome = true,
        tags = ""
)

public class LoginRunner extends AbstractTestNGCucumberTests {
        @Override
        @DataProvider(parallel = true)
        public Object[][] scenarios(){
                return super.scenarios();
        }

}


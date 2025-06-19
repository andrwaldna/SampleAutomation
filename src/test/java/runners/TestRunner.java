package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "stepdefinitions",
        plugin = {"pretty", "html:reports/cucumber-html-report", "json:reports/cucumber-json-report.json"},
        monochrome = true,
        tags = "@AutomationPractice")
public class TestRunner {
}

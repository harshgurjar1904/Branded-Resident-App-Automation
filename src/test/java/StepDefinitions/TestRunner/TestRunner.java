package StepDefinitions.TestRunner;

import StepDefinitions.Setup.BaseClass;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/resources/Features",glue={"StepDefinitions"}, monochrome = true,  plugin = {"pretty","html:target/htmlReports.html"},tags="@LoginTest")
public class TestRunner extends BaseClass {

}

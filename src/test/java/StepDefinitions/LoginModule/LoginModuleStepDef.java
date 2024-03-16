package StepDefinitions.LoginModule;

import StepDefinitions.TestRunner.TestRunner;
import io.cucumber.java.en.*;
import org.digivalet.Modules.LoginModule.LoginPage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class LoginModuleStepDef extends TestRunner {

    @Given("I am on login page")
    public void iAmOnLoginPage() {
        System.out.println("I am on Login Page");
    }

    @When("I am entering email address")
    public void iAmEnteringEmailAddress() {

        LoginPage loginDriver = new LoginPage(driver);
        loginDriver.sendEmailAddress(emailAddress);

    }

    @And("I am entering OTP")
    public void iAmEnteringOTP() throws InterruptedException, MessagingException, IOException, GeneralSecurityException {

        LoginPage loginDriver = new LoginPage(driver);
        loginDriver.sendOTP(emailAddress,password);

    }

    @And("I click on Login button")
    public void iClickOnLoginButton() {

        LoginPage loginDriver = new LoginPage(driver);
        loginDriver.tapOnLoginButton();

    }

    @Then("I am on Home screen")
    public void iAmOnHomeScreen() {
        System.out.println("I am on Home screen");

    }

}

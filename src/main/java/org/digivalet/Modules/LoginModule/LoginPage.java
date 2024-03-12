package org.digivalet.Modules.LoginModule;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.digivalet.Utils.ResidentAppUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends ResidentAppUtils {
    AndroidDriver driver;
    public LoginPage(AndroidDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }
    @AndroidFindBy(xpath="//android.widget.TextView[@resource-id=\"com.paragon.sensonic:id/login_email\"]")
    private WebElement emailLabelLocator;

    @AndroidFindBy(xpath="//android.widget.EditText[@resource-id=\"com.paragon.sensonic:id/mobile_edit\"]")
    private WebElement emailFieldLocator;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.paragon.sensonic:id/login_btn\"]")
    private WebElement loginButtonLocator;

    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id=\"com.paragon.sensonic:id/otp_view\"]")
    private WebElement otpFieldLocator;



    public void sendEmailAddress(String email){
        emailLabelLocator.click();
        emailFieldLocator.sendKeys(email);
    }

    public void tapOnLoginButton(){
        loginButtonLocator.click();
    }

    public void sendOTP() throws InterruptedException {
        String otp="258963";
        otpFieldLocator.sendKeys(otp);
        Thread.sleep(6000);
    }




}

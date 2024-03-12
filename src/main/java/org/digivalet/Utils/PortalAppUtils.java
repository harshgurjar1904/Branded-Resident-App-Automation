package org.digivalet.Utils;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PortalAppUtils extends AndroidActions {
    public PortalAppUtils(AndroidDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    @AndroidFindBy(xpath="//android.widget.FrameLayout[@content-desc='More']")
    private WebElement moreLocator;
    @AndroidFindBy(xpath="//android.widget.TextView[@resource-id=\"com.paragon.sensonicstaff:id/save_button\"]")
    private WebElement saveButtonLocator;

    @AndroidFindBy(xpath="//android.widget.TextView[@resource-id=\"com.paragon.sensonicstaff:id/done_btn\"]")
    private WebElement doneButtonLocator;

    @AndroidFindBy(xpath="//android.widget.TextView[@content-desc=\"Filter\"]")
    private WebElement filterButtonLocator;
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"android:id/button1\"]")
    private WebElement okButtonLocator;


    public void openFeature(String featureName) {
        driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.paragon.sensonicstaff:id/label_feature_name\" and @text='"+featureName+"']")).click();
    }
    public void tapOnAddButton() {
        driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Add\"]")).click();
    }
    public void searchOnSearchbar(String value){
        driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"com.paragon.sensonicstaff:id/search_bar\"]")).sendKeys(value);
    }

    public void tapOnMore(){
        moreLocator.click();
    }

    public void tapONSaveButton(){
        saveButtonLocator.click();
    }

    public void tapONDoneButton(){
        doneButtonLocator.click();
    }

    public void tapONFilterButton(){
        filterButtonLocator.click();
    }

    public void selectParticularDate(String date){
        driver.findElement(By.xpath("//android.view.View[@content-desc=\""+date+"\"]")).click();
        okButtonLocator.click();
    }
    public void restartFromMoreSection(){
        driver.executeScript("mobile:startActivity", ImmutableMap.of("intent","com.paragon.sensonicstaff/com.paragon.sensonicstaff.ui.activities.dashboard.DashboardActivity"));
        tapOnMore();
    }

    public void selectParticularTimeSlot(String givenTimeSlot) throws InterruptedException {
        Thread.sleep(3000);
       WebElement timeSlotBox=driver.findElement(By.xpath("//android.widget.ListView"));
       List<WebElement> timeSlots=timeSlotBox.findElements(By.className("android.widget.LinearLayout"));
       int noOfTimeSlot= timeSlots.size();
       System.out.println("No of time slot "+noOfTimeSlot);
       int temp=0;
       while(temp<noOfTimeSlot){
           List<WebElement> timeSlot=timeSlots.get(temp).findElements(By.className("android.widget.TextView"));
           if(timeSlot.get(0).getText().contains(givenTimeSlot)){
               System.out.println("time slot "+timeSlot.get(0).getText());
               timeSlot.get(0).click();
               break;
           }
           temp++;
       }
    }
}

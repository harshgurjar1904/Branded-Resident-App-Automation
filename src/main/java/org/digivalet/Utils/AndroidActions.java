package org.digivalet.Utils;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import java.util.*;

public class AndroidActions extends AppiumUtils {
	public AndroidDriver driver;
	public AndroidActions(AndroidDriver driver) {
		this.driver=driver;
	}
	
	public void scrollToTextAndTap(String value) {
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+value+"\"));"));
		driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.paragon.sensonicstaff:id/label_name\" and @text='"+value+"']")).click();
	}
	public void scrollToText(String value) {
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+value+"\"));"));
	}

	public void tapOnCoordinate(int x, int y) {
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
				"x",x,
				"y",y,
				"duartion",500
				));

	}

	public void selectTime(String time) throws InterruptedException {

		Map<Integer, List<Integer>> xYCoordinateForHour=new HashMap<Integer,List<Integer>>();
		xYCoordinateForHour.put(1,  new ArrayList<Integer>(Arrays.asList(664,1047)));
		xYCoordinateForHour.put(2,  new ArrayList<Integer>(Arrays.asList(752,1135)));
		xYCoordinateForHour.put(3,  new ArrayList<Integer>(Arrays.asList(779,1254)));
		xYCoordinateForHour.put(4,  new ArrayList<Integer>(Arrays.asList(752,1370)));
		xYCoordinateForHour.put(5,  new ArrayList<Integer>(Arrays.asList(664,1457)));
		xYCoordinateForHour.put(6,  new ArrayList<Integer>(Arrays.asList(535,1494)));
		xYCoordinateForHour.put(7,  new ArrayList<Integer>(Arrays.asList(420,1453)));
		xYCoordinateForHour.put(8,  new ArrayList<Integer>(Arrays.asList(332,1374)));
		xYCoordinateForHour.put(9,  new ArrayList<Integer>(Arrays.asList(304,1250)));
		xYCoordinateForHour.put(10,  new ArrayList<Integer>(Arrays.asList(341,1139)));
		xYCoordinateForHour.put(11,  new ArrayList<Integer>(Arrays.asList(429,1042)));
		xYCoordinateForHour.put(12,  new ArrayList<Integer>(Arrays.asList(544,1015)));

		int hour=Integer.parseInt(time.substring(0,2));
		int minutes=Integer.parseInt(time.substring(3,5));
		System.out.println("hour "+hour +" minutes "+minutes);

		minutes=minutes/5;
		int xH= xYCoordinateForHour.get(hour).get(0);
		int yH= xYCoordinateForHour.get(hour).get(1);
		int xM= xYCoordinateForHour.get(minutes).get(0);
		int yM= xYCoordinateForHour.get(minutes).get(1);
		tapOnCoordinate(xH,yH);
		Thread.sleep(1000);
		tapOnCoordinate(xM,yM);
		System.out.println("hour "+xH+"  "+yH +" minutes "+xM+"  "+yM );
		String amPm=time.substring(6,8);
		System.out.println("type "+amPm);
		if(amPm.contains("AM")){
			driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.paragon.sensonicstaff:id/mdtp_am_label\"]")).click();
		}
		else{
			driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.paragon.sensonicstaff:id/mdtp_pm_label\"]")).click();
		}

		driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.paragon.sensonicstaff:id/mdtp_ok\"]")).click();
	}

	public void scroll(int startX, int startY, int endX, int endY) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("mobile:touchAction", "{\"action\":\"press\",\"options\":{\"x\":" + startX + ",\"y\":" + startY + "}}");
		js.executeScript("mobile:touchAction", "{\"action\":\"moveTo\",\"options\":{\"x\":" + endX + ",\"y\":" + endY + "}}");
		js.executeScript("mobile:touchAction", "{\"action\":\"release\"}");
	}



}

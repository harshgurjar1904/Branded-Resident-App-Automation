package org.digivalet.Utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class AppiumUtils extends AbstractTestNGCucumberTests {
	public static List<HashMap<String,String>> getJsonData(String jsonFilePath) throws IOException{
	
		String jsonContent=FileUtils.readFileToString(new File(jsonFilePath),StandardCharsets.UTF_8);
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String,String>> data=mapper.readValue(jsonContent,new TypeReference<List<HashMap<String,String>>>(){});
		return data;
		
	}
	public static String getData(String path,int n, String key) throws IOException {
		List<HashMap<String,String>> data=getJsonData(path);
		return data.get(n).get(key);
	}


	public static AppiumDriverLocalService startAppiumServer(String systemUserName, String ipAddress, int portNO) {
		//below code is used to start the appium server through code
		AppiumDriverLocalService service= new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\"+systemUserName+"\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js")).withIPAddress(ipAddress).usingPort(portNO).build();
		service.start(); //start the server
		return service;
	}
	
}

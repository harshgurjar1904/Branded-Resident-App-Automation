package org.digivalet.Utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
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



	public static AppiumDriverLocalService startAppiumServer(String mainPath,String ipAddress, int portNO) {
		//below code is used to start the appium server through code
		// Set the path to your Android SDK
		String androidHome = "C:\\Users\\harsh\\AppData\\Local\\Android\\Sdk";

		// Set the AVD name (emulator name)
		String avdName = "Testing AVD"; // Change this to match your emulator
		int avdPort = 5555;


		AppiumDriverLocalService service= new AppiumServiceBuilder().withAppiumJS(new File(mainPath)).withIPAddress(ipAddress).usingPort(portNO).build();
		service.start(); //start the server

//		startEmulator(androidHome, avdName,avdPort);
//		// Wait for the emulator to start
//		waitForEmulator(avdName);

		return service;
	}
	private static void startEmulator(String androidHome, String avdName, int avdPort) {
		try {
			String emulatorPath = androidHome + "/emulator/emulator";
			ProcessBuilder processBuilder = new ProcessBuilder(emulatorPath, "-avd","-port", avdName,String.valueOf(avdPort));
			Process process = processBuilder.start();
			process.waitFor(); // Wait for the emulator to start
			System.out.println("Emulator started successfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void waitForEmulator(String avdName) {
		try {
			String adbCommand = "adb devices";
			Process process = Runtime.getRuntime().exec(adbCommand);
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			boolean emulatorFound = false;
			while ((line = reader.readLine()) != null) {
				if (line.contains(avdName) && line.contains("device")) {
					emulatorFound = true;
					break;
				}
			}

			if (!emulatorFound) {
				// Wait for the emulator to become online
				Thread.sleep(5000); // Wait for 5 seconds before checking again
				waitForEmulator(avdName); // Recursive call to check again
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

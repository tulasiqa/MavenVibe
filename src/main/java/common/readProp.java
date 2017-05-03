package common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;


// public class readProp extends LoginCredentials{
public class readProp extends locators{

	private static Properties configProp = new Properties();
	private static String propFilePath;
	private static File file;
	private static FileInputStream fileInput;
	public static String key;
	public static String value;
	public static String[] dataValue;
	public static String[] keyValue;
	
	
	public static String getEnv(String appUrl){
		System.out.println("inside getEnv() method.");
		String urlParts[] = appUrl.split("/");
		System.out.println("urlParts[2] =" +urlParts[2]);
		String envName = urlParts[2];
		String envParts[] = envName.split("\\.");
		System.out.println("envParts[0] =" +envParts[0]);
	return envParts[0];
}

public static String getLocatorForEnvironment(String appUrl, String locatorName) throws IOException{
	System.out.println("inside getLocatorForEnvironment() method.");
	String envName = getEnv(appUrl);
	System.out.println("envName ="+envName);
	try{
		switch(envName){
		case "branch-1-9":
			propFilePath = projectPath + "\\properties\\branch-1-9.properties";
			break;
		case "branch-1-10":
			propFilePath = projectPath + "\\properties\\branch-1-10.properties";
			break;
		case "branch-1-11":
			propFilePath = projectPath + "\\properties\\branch-1-11.properties";
			break;
		case "branch-2-0":
			propFilePath = projectPath + "\\properties\\branch-2-0.properties";
			break;
		case "idlife":
			propFilePath = projectPath + "\\properties\\idlife.properties";
			break;
		case "idlife-stage":
			propFilePath = projectPath + "\\properties\\idlife-stage.properties";
			break;
		case "idlife-stage2":
			propFilePath = projectPath + "\\properties\\idlife-stage2.properties";
			break;
		case "idlife-rc":
			propFilePath = projectPath + "\\properties\\idlife-rc.properties";
			break;
		case "mannatech-stage":
			propFilePath = projectPath + "\\properties\\mannatech-stage.properties";
			break;
		case "master":
			propFilePath = projectPath + "\\properties\\master.properties";
			break;
		case "release":
			propFilePath = projectPath + "\\properties\\release-master.properties";
			break;
		case "moab":
			propFilePath = projectPath + "\\properties\\moab.properties";
			System.out.println("propFilePath =" +propFilePath);
			break;
		case "tupperware-stage":
			propFilePath = projectPath + "\\properties\\tupperware-stage.properties";
			System.out.println("propFilePath =" +propFilePath);
			break;
		case "monat-stage":
			propFilePath = projectPath + "\\properties\\monat-stage.properties";
			break;
		case "avon-stage2":
			propFilePath = projectPath + "\\properties\\avon-stage2.properties";
			break;	
		case "lpgn-prod":
			propFilePath = projectPath + "\\properties\\lpgn-prod.properties";
			break;	
		case "monat-rc":
			propFilePath = projectPath + "\\properties\\monat-rc.properties";
			break;

		}
		
	} catch(Exception e){
		e.getMessage();
	}
	
	
	try {
		file = new File(propFilePath);
		fileInput = new FileInputStream(file);
		configProp.load(fileInput);
		// fileInput.close();

		Enumeration enuKeys = configProp.keys();
		while (enuKeys.hasMoreElements()) {
			key = (String) enuKeys.nextElement();
			if(key.trim().equalsIgnoreCase(locatorName)){
				value = configProp.getProperty(key);
				System.out.println("matched key =" + key);
				System.out.println("matched value  =" + value);
				break;
			}
		}
	
	} catch (FileNotFoundException e) {
			e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	
			return value;
}	
}

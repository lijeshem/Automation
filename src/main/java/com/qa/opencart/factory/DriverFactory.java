package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver driver;
	Properties prop;
	public static String highlight;
	public OptionsManager optionsManager;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	/**
	 * This method is used to initialize the webdriver based on the given browser
	 * 
	 * @param browserName
	 * @return this will return the driver
	 */
	public WebDriver init_driver(Properties prop) {
		String browserName = prop.getProperty("browser").trim();
		String browserVersion = prop.getProperty("browserversion").trim();
		System.out.println("The browser name is : " + browserName);
		System.out.println("Browser version is : "+browserVersion);

		highlight = prop.getProperty("highlight").trim();
		optionsManager = new OptionsManager(prop);

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			if(Boolean.parseBoolean(prop.getProperty("remote"))){
				init_remoteDriver("chrome",browserVersion);
			}else {
				// driver = new ChromeDriver(optionsManager.getChromOptions());
				tlDriver.set(new ChromeDriver(optionsManager.getChromOptions()));
			}
			
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			if(Boolean.parseBoolean(prop.getProperty("remote"))){
				init_remoteDriver("firefox",browserVersion);
			}else {
			// driver = new FirefoxDriver(optionsManager.getFireFoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFireFoxOptions()));
			}
		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			// driver = new EdgeDriver();
			tlDriver.set(new EdgeDriver());
		} else {
			System.out.println("Please pass the correctbrowser: " + browserName);
		}

		getDriver().manage().window().fullscreen();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url").trim());
		return getDriver();

	}

	private void init_remoteDriver(String browser, String browserVersion) {
		
		System.out.println("Running the test on Remote grid server : "+browser);
		if(browser.equalsIgnoreCase("chrome")) {
			//Selenium 3.x
			//DesiredCapabilities cap = DesiredCapabilities.chrome();
			DesiredCapabilities cap = new DesiredCapabilities();
			//cap.setCapability(ChromeOptions.CAPABILITY, optionsManager.getChromOptions());
			// soon it will get deprecated -->cap.setBrowserName("chrome");
			cap.setCapability("browserName", "chrome");
			cap.setCapability("browserVersion", browserVersion);
			cap.setCapability("enableVNC", true);
			cap.setCapability(ChromeOptions.CAPABILITY, optionsManager.getChromOptions());
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), cap));
			}catch(MalformedURLException e) {
				e.printStackTrace();
			}
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			//DesiredCapabilities cap = DesiredCapabilities.firefox();
			DesiredCapabilities cap = new DesiredCapabilities();
			//cap.setCapability(FirefoxOptions.FIREFOX_OPTIONS, optionsManager.getFireFoxOptions());
			// soon it will get deprecated --> cap.setBrowserName("firefox");
			cap.setCapability("browserName", "firefox");
			cap.setCapability("browserVersion", browserVersion);
			cap.setCapability("enableVNC", true);
			cap.setCapability(FirefoxOptions.FIREFOX_OPTIONS, optionsManager.getFireFoxOptions());
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), cap));
			}catch(MalformedURLException e) {
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * it will return the thread local copy of the driver
	 */

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	/**
	 * This method is used to intitialize the properties
	 * 
	 * @return properties
	 */

	public Properties init_prop() {
		prop = new Properties();
		FileInputStream fip = null;
		String envName = System.getProperty("env");
		if (envName == null) {
			System.out.println("Automation is running on PRODUCTION");
			try {
				fip = new FileInputStream("./src/test/resources/config/config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Automation is running on :" + envName);
			try {
				switch (envName.toLowerCase()) {
				case "qa":
					fip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case "staging":
					fip = new FileInputStream("./src/test/resources/config/staging.config.properties");
					break;
				default:
					System.out.println("Please pass the correct environment name");
					break;
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		try {
			prop.load(fip);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return prop;

		/*
		 * 
		 * try { fip = new
		 * FileInputStream("./src/test/resources/config/config.properties"); try {
		 * prop.load(fip); } catch (IOException e) { e.printStackTrace(); } } catch
		 * (FileNotFoundException e) { e.printStackTrace(); } return prop;
		 */
		
		/**
		 * This method will take the screen shot and return the path of the screen shot
		 */
	}
	
	public String getScreenshot() {
		File src = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/screenshots/"+System.currentTimeMillis()+".png";
		File desitnation = new File(path);
		try {
			FileUtils.copyFile(src, desitnation);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

}

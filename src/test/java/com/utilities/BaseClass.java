package com.utilities;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClass {
	private String frameworkDir = System.getProperty("user.dir");
	private String webDriverPath = frameworkDir + File.separator + "resources" + File.separator + "chromedriver.exe";
	
	// create instance for web-driver
	public WebDriver driver;

	// To launch browser
	public void launchBrowser(String url) {
		System.setProperty("webdriver.chrome.driver", webDriverPath);
		driver = new ChromeDriver();
		
		// System.setProperty("webdriver.firefox.marionette","D:\\Centrallibrary\\Drivers\\geckodriver.exe");
		// driver=new FirefoxDriver();
		
		// To Maximize Window
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(url);
	}

	// Explicit wait method for element clickable
	public WebElement waitForExpectedElement(WebDriver driver, final By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
}

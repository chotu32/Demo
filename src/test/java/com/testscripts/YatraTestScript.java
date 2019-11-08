package com.testscripts;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.pageobjects.YatraFlightDetailsPageObject;
import com.pageobjects.YatraPageObject;
import com.pageobjects.YatraTravellerDetailsPageObject;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.utilities.BaseClass;
import com.utilities.Config;
import com.utilities.Utilities;

public class YatraTestScript extends BaseClass {
	// Create instance of Config Class
	Config con = new Config();
	// Create Instance of YatraPageObject Class
	YatraPageObject input = new YatraPageObject(driver);
	// Create Instance of YatraFlightPageObject Class
		YatraFlightDetailsPageObject flightObject = new YatraFlightDetailsPageObject(driver);
	// Create Instance of YatraTravellerPageObject Class
		YatraTravellerDetailsPageObject travellerObject = new YatraTravellerDetailsPageObject(driver);
	// Create Instance of Utilities Class
	Utilities utilities = new Utilities();
	// store current working directory path
		public static String pth = System.getProperty("user.dir");
		// Create Reports Folder In Current Working Directory Path
		public static String reportFilePath = pth + "/Reports/";
		//Create Instance for ExtentReports Class
		public ExtentReports reports;
		// create variable for extent test
		ExtentTest test;
	
		// creating object for logger class
		Logger logger = Logger.getLogger(YatraTestScript.class);

	// Constructor Calling
	public YatraTestScript() throws Exception {
		// Calling Config File in Constructor
		con.loadPropertyFile();
		// Calling Log4j.propertie file
		PropertyConfigurator.configure("./Log4j/log4j.properties");
	}
	// Declared BeforeClass TestNG annotation
	@BeforeSuite()
	// Declared Open method
	public void open() {
		// Calling LaunchBrowser
		launchBrowser(con.getProperty("url"));
		logger.info("Browser launched & url opened");
	}

	@Test(priority = 1)
	public void flightJourneyDate() throws InterruptedException {
		test = utilities.reportsFile("Goibibo - flight booking information");
		
		Thread.sleep(5000);
		input.sendSourcePlace(con.getProperty("from"), driver);
		logger.info("Entered Source Place : Hyderabad");
		test.log(LogStatus.INFO, "Entered Source Place : Hyderabad");
		//calls the method to take the screenshot
		//Utilities.captureScreenshot(driver, "Entered Source Place : Hyderabad");
		
		Thread.sleep(5000);
		input.sendDestinationPlace(con.getProperty("to"), driver);
		logger.info("Entered Destination Place : Tirupati");
		
		input.clickFromDate(driver);
		logger.info("Clicked On Depart Date");
		String getDate = input.fromDateValidation(driver);
		if(getDate.equalsIgnoreCase("29"))
			test.log(LogStatus.PASS, "Clicked on Depart Date");
		else
			test.log(LogStatus.FAIL, "Clicked on Depart Date");
		
		//calls the method to take the screenshot
		//Utilities.captureScreenshot(driver, "Clicked On Depart Date");
		
		input.selectFromDate(driver);
		logger.info("Selected Depart Date : Sat, 15 Sep");
		test.log(LogStatus.INFO, "Selected Depart Date : Sat, 15 Sep");
		//calls the method to take the screenshot
		//Utilities.captureScreenshot(driver, "Selected Depart Date : Sat, 15 Sep");
		
		Thread.sleep(2000);
		input.sendDestinationPlace(con.getProperty("to"), driver);
		logger.info("Entered Destination Place : Tirupati");
		test.log(LogStatus.INFO, "Entered Destination Place : Tirupati");
		//calls the method to take the screenshot
		//Utilities.captureScreenshot(driver, "Entered Destination Place : Tirupati");
		
		input.clickSearchBtn(driver);
		logger.info("Clicked on Search Button");
		//test.log(LogStatus.INFO, "Clicked on Search Button");
		String getText = input.getSetGoBtnValidation(driver);
		if(getText.equalsIgnoreCase("Set Fare Alerts"))
			test.log(LogStatus.PASS, "Clicked on Set_Go_Btn");
		else
			test.log(LogStatus.FAIL, "Clicked on Set_Go_Btn");
		//calls the method to take the screenshot
		//Utilities.captureScreenshot(driver, "Click ed on Search Button");
		// Report Ends
		utilities.endReport();
	}
	
	@Test (priority = 2)
	public void flightList() throws InterruptedException {
		test = utilities.reportsFile("Goibibo - Flight Information");
		flightObject.bottomScroll(driver);
		logger.info("Scrolled Bottom Of The Page");
		
		flightObject.clickViewAllFightsBtn(driver);
		logger.info("Clicked on View All Flights Button");
		//test.log(LogStatus.INFO, "Clicked on View All Flights Button");
		//calls the method to take the screenshot
		//Utilities.captureScreenshot(driver, "Clicked on View All Flights Button");
		
		flightObject.bottomScroll(driver);
		logger.info("Scrolled Bottom Of The Page");
		
		int ar[] = flightObject.getPriceList(driver);
		test.log(LogStatus.INFO, "Lowest Flight Ticket Rate  : " + ar[0]);
		test.log(LogStatus.INFO, "Highest Flight Ticket Rate : " + ar[1]);
		//System.out.println("Lowest rate : " + ar[0]);
		//System.out.println("Highest rate : " + ar[1]);
		logger.info("Got Price-List Of Flights ");
		
		flightObject.highestFlightTicketSelect(driver);
		logger.info("Highest Flight Ticket Selected");
		//test.log(LogStatus.INFO, "Highest Flight Ticket Selected");
		
		String getTicketDetails = flightObject.bookingValidation(driver);
		if(getTicketDetails.equalsIgnoreCase("ticket DEtails"))
			test.log(LogStatus.PASS, "Highest Flight Ticket Selected :" + ar[1]);
		else
			test.log(LogStatus.FAIL, "Highest Flight Ticket Selected");
		//calls the method to take the screenshot
		//Utilities.captureScreenshot(driver, "Highest Flight Ticket Selected");
		
		// Report Ends
		utilities.endReport();
	}
	
	@Test (priority = 3)
	public void travellerDetails() throws InterruptedException {
		test = utilities.reportsFile("Goibibo - Traveller Information");
		
		travellerObject.selectTitle(con.getProperty("title"), driver);
		logger.info("Title Selected : Mr");
		
		
		travellerObject.sendFirstName(con.getProperty("firstName"), driver);
		logger.info("Entered First Name : Karthik");
		
		
		travellerObject.sendLastName(con.getProperty("lastName"), driver);
		logger.info("Entered lastName Name : Kumar");
		
		
		travellerObject.sendEmail(con.getProperty("email"), driver);
		logger.info("Entered Email : karthik@gmail.com");
		
		
		travellerObject.sendMobileNumber(con.getProperty("mobileNumber"), driver);
		logger.info("Entered Mobile Number : 8142243634");
		
		
		travellerObject.clickProceedBtn(driver);
		logger.info("Clicked On Proceed-To-Payment Button");
		String getTicketText = travellerObject.proceedBtnValidation(driver);
		if(getTicketText.equalsIgnoreCase("View DEtaIls"))
			test.log(LogStatus.PASS, "Clicked On Proceed-To-Payment Button");
		else
			test.log(LogStatus.FAIL, "Clicked On Proceed-To-Payment Button");
		String str[] = new String[5];
		 str = travellerObject.getContactDetails(driver);
		logger.info("Got Contact Details");
		test.log(LogStatus.INFO, "Got Contact Details");
		
		test.log(LogStatus.INFO, "Title Selected : " + str[0]);
		test.log(LogStatus.INFO, "Entered First Name : " + str[1]);
		test.log(LogStatus.INFO, "Entered lastName Name : " + str[2]);
		test.log(LogStatus.INFO, "Entered Email : " + str[3]);
		test.log(LogStatus.INFO, "Entered Mobile Number : " + str[4]);
		
		//calls the method to take the screenshot
		Utilities.captureScreenshot(driver, "Displayed Contact Details");
		// Report Ends
		utilities.endReport();
	}
	
	@AfterSuite()
	public void close() {
		// to close current browser
		driver.close();
		logger.info("Browser closed");
	}
}
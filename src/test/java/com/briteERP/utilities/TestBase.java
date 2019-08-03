package com.briteERP.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import  java.io.IOException;
import java.util.concurrent.TimeUnit;
public class TestBase {
    //should be public/protected !!!!
    public WebDriver driver;
    public Actions action;
    protected ExtentReports report;
    protected ExtentHtmlReporter htmlReporter;
    protected ExtentTest extentLogger;

    @BeforeTest
    public void testSetup() {
        report = new ExtentReports();
        String pathToReport = System.getProperty("user.dir")+"/test-output/report.html";
        htmlReporter = new ExtentHtmlReporter(pathToReport);
        report.attachReporter(htmlReporter);
        report.setSystemInfo("OS",System.getProperty("osname"));
        htmlReporter.config().setDocumentTitle("BriteERP_Project");
    }

    //public class TestBase {
        //should be public/protected !!!!
        //public WebDriver driver;
       // public Actions action;  //we dont need to initiliaze again action class in our test class
    @BeforeMethod
    public void setup() {
        driver = Driver.getDriver();
        action = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(Long.valueOf(ConfigurationReader.getProperty("implicitwait")), TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(ConfigurationReader.getProperty("url"));
    }

    @AfterMethod
    public void teardown(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            // if test fails get a screenshot and save the location to the image
            String pathToTheScrennshot = SeleniumUtils.getScreenshot(result.getName());
            extentLogger.fail(result.getName());
            try {
                extentLogger.addScreenCaptureFromPath(pathToTheScrennshot);
            } catch (IOException e) {
                e.printStackTrace();
            }
            extentLogger.fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SKIP) {
            extentLogger.skip("Test case skipped" + result.getName());
        }
        Driver.closeDriver();
    }
   @AfterTest
    public void tearDownTest() {
        report.flush();

    }
}








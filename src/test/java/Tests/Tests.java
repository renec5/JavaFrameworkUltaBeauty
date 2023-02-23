package Tests;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import ConfigFiles.ReportResult;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.HomePage;
import pages.LoginPage;
import pages.SearchResultsPage;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class Tests {
	
	WebDriver driver;
	ExtentReports HTMLReport;
	ExtentTest ReportManager;
	String baseURL;
	HomePage HP;
	LoginPage LP;
	SearchResultsPage SRP;
	
  
  @BeforeMethod(alwaysRun=true)
  public void beforeMethod(Method method) throws IOException {
	  baseURL = "https://www.ulta.com/";
	  String testName = method.getName();
	  HTMLReport = ReportResult.startReport(testName);
	  ReportResult.Log("pass", "Report Result created successfully", false);
	  WebDriverManager.chromedriver().setup();
	  driver = new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	  driver.get(baseURL);
	  
  }

  @AfterMethod(alwaysRun=true)
  public void afterMethod() {
	  driver.quit();
	  ReportResult.HTMLReport.endTest(ReportResult.ReportManager);
	  ReportResult.HTMLReport.flush();
  }

  @BeforeClass(alwaysRun=true)
  public void beforeClass() {
  }

  @AfterClass(alwaysRun=true)
  public void afterClass() {
  }
  
  @Test(groups = { "sanity", "regression" })
  public void Test1() throws IOException {
	  HP = new HomePage(driver);
	  SRP = new SearchResultsPage(driver);
	  HP.searchProduct("lipstick");
	  HP.clickMagnifierGlassBtn();
	  Assert.assertTrue(SRP.validateSearchResults());
  }
  
  @Test(groups = { "regression" })
  public void TestInvalidLogin() throws IOException {
	  HP = new HomePage(driver);
	  LP = new LoginPage(driver);
	  HP.clickLoginLink();
	  LP.validInvalidLogin("test@gmail.com", "123", "invalid");
	  
  }

}

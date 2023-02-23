package ConfigFiles;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ReportResult {
	
	static WebDriver Driver;
	public static ExtentReports HTMLReport;
	public static ExtentTest ReportManager;
	
	public ReportResult(WebDriver driver) {
		Driver = driver;
	}
	
	public static ExtentReports startReport(String testName) {
		 Date date = new Date();
		 HTMLReport = new ExtentReports("/Users/rene.cortes/Desktop/reports/" + testName + "-" + Long.toString(date.getTime()) + ".html", false);
		 ReportManager = HTMLReport.startTest(testName);
		 return HTMLReport;
	}
	
	public static String takeSS() throws IOException {
		Date date = new Date();
		String ssName = "Screenshot-" + Long.toString(date.getTime()) + ".png";
		String directory = System.getProperty("user.dir") + "/Screenshots/";
		File screenshot = ((TakesScreenshot)Driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, new File(directory + ssName));
		return directory + ssName;
	}
	
	public static void Log(String status, String description, boolean attachSS) throws IOException {
		if (attachSS) {
			String ssPath = takeSS();
			String imagePath = ReportManager.addScreenCapture(ssPath);
			switch (status.toLowerCase()) {
			case "pass":
				ReportManager.log(LogStatus.PASS, description);
				ReportManager.log(LogStatus.PASS, description, imagePath);
				break;
			case "fail":
				ReportManager.log(LogStatus.FAIL, description);
				ReportManager.log(LogStatus.FAIL, description, imagePath);
				break;
			case "info":
				ReportManager.log(LogStatus.INFO, description);
				ReportManager.log(LogStatus.INFO, description, imagePath);
				break;
			}
		}
		else {
			switch (status.toLowerCase()) {
			case "pass":
				ReportManager.log(LogStatus.PASS, description);
				break;
			case "fail":
				ReportManager.log(LogStatus.FAIL, description);
				break;
			case "info":
				ReportManager.log(LogStatus.INFO, description);
				break;
			}
		}
	}
	
	

}

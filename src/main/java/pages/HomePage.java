package pages;

import java.io.IOException;
import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;

import ConfigFiles.ReportResult;

public class HomePage {
	
	WebDriver driver;
	WebDriverWait wait;
	ExtentReports HTMLReport;
	ReportResult RR;
	LoginPage LP;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
		RR = new ReportResult(driver);
		LP = new LoginPage(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="input[type='search']")
	WebElement searchBar;
	
	@FindBy(css="button[type='submit']")
	WebElement magnifierGlassBtn;
	
	@FindBy(id="opennav")
	WebElement navMenuBtn;

	@FindBy(css="a[data-nav-description=\"h - find a store\"]")
	WebElement findAStoreLink;

	@FindBy(css="a[data-nav-description=\"h - email & text signup\"]")
	WebElement eamilAndTextSignupLink;
	
	@FindBy(css="a[data-nav-description=\"h - gift cards\"]")
	WebElement giftCardsLink;
	
	@FindBy(css="a[data-nav-description='h - sign in']")
	WebElement signInLink;
	
	@FindBy(xpath="//button[contains(text(),'Accept Cookies')]")
	WebElement acceptCookies;
	
	@FindBy(css="div.DesktopHeader__NavigationBar__item.DesktopHeader__NavigationBar__item--rewardsChevron--text")
	WebElement rewardsDropDown;
	
	
	public void searchProduct(String productToSearch) throws IOException {
		acceptCookies();
		searchBar.sendKeys(productToSearch);
		ReportResult.Log("pass", "Product searched correctly", true);
	}
	
	public void clickLoginLink() throws IOException{
		try {
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(signInLink));
			element.click();
			// acceptCookies();
			signInLink.click();
			ReportResult.Log("pass", "Sign in link has been clicked", false);
			Assert.assertTrue(LP.forgotPasswordLink.isDisplayed());
			ReportResult.Log("pass", "Login Page displayed", true);
		}catch (Exception e) {
			ReportResult.Log("fail", "Login Page could NOT be reached", true);
		}
	}
	
	public void clickMagnifierGlassBtn() throws IOException{
		try{
			magnifierGlassBtn.click();
			ReportResult.Log("pass", "Search triggered", false);
		}catch (Exception e) {
			ReportResult.Log("fail", "Search Button could NOT be clicked", false);
		}
	}
	
	public void acceptCookies() throws IOException {
		try {
			if (acceptCookies.isDisplayed()) {
				ReportResult.Log("info", "Cookies request displayed", true);
			}
			acceptCookies.click();
			ReportResult.Log("pass", "Cookies accepted", false);
		}catch (Exception e) {
			ReportResult.Log("info", "No Cookies to accept/Reject", false);
		}
	}

}

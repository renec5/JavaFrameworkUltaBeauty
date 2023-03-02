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

import ConfigFiles.CommonMethods;
import ConfigFiles.ReportResult;

public class HomePage {
	
	WebDriver driver;
	WebDriverWait wait;
	ExtentReports HTMLReport;
	ReportResult RR;
	LoginPage LP;
	CommonMethods CM;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
		RR = new ReportResult(driver);
		LP = new LoginPage(driver);
		CM = new CommonMethods(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="input#search")
	private WebElement searchBar;
	
	@FindBy(css="button[aria-label='Submit']")
	private WebElement magnifierGlassBtn;
	
	@FindBy(id="opennav")
	private WebElement navMenuBtn;

	@FindBy(css="a[data-nav-description=\"h - find a store\"]")
	private WebElement findAStoreLink;

	@FindBy(css="a[data-nav-description=\"h - email & text signup\"]")
	private WebElement eamilAndTextSignupLink;
	
	@FindBy(css="a[data-nav-description=\"h - gift cards\"]")
	private WebElement giftCardsLink;
	
	@FindBy(xpath="//span[contains(text(),'Join')]//parent::button")
	private WebElement signInLink;
	
	@FindBy(xpath="//button[contains(text(),'Accept Cookies')]")
	private WebElement acceptCookies;
	
	@FindBy(css="div.DesktopHeader__NavigationBar__item.DesktopHeader__NavigationBar__item--rewardsChevron--text")
	private WebElement rewardsDropDown;
	
	
	public void searchProduct(String productToSearch) throws IOException {
		CM.acceptCookies();
		searchBar.sendKeys(productToSearch);
		ReportResult.Log("pass", "Product searched correctly", true);
	}
	
	public void clickLoginLink() throws IOException{
		try {
			CM.acceptCookies();
			CM.waitAndClick(signInLink, "Sign In link");
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

}

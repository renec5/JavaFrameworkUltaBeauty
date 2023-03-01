package ConfigFiles;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonMethods {

	WebDriver driver;
	WebDriverWait wait;
	
	String acceptCookiesBtnLocator = "//button[contains(text(),'Accept Cookies')]";
	@FindBy(xpath="//button[contains(text(),'Accept Cookies')]")
	private WebElement acceptCookiesBtn;
	
	public CommonMethods(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getElementExplicitWait(String locator, String locatorType) {
		WebElement element = null;
		switch(locatorType.toLowerCase()) {
		case("id"):
			element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(locator)));
			break;
		case ("xpath"):
			element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
			break;
		case ("css"):
			element = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locator)));
			break;
		case ("class"):
			element = wait.until(ExpectedConditions.presenceOfElementLocated(By.className(locator)));
			break;
		case ("link"):
			element = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(locator)));
			break;
		case ("partial"):
			element = wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(locator)));
			break;
		case ("name"):
			element = wait.until(ExpectedConditions.presenceOfElementLocated(By.name(locator)));
			break;
		case ("tag"):
			element = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(locator)));
			break;
		}
		return element;
	}
	
	public void acceptCookies() throws IOException {
		try {
			if (acceptCookiesBtn.isDisplayed()) {
				ReportResult.Log("info", "Cookies request displayed", true);
				this.waitAndClick(acceptCookiesBtn, "Accept Cookies Button");
			}
		}catch (Exception e) {
			ReportResult.Log("info", "No Cookies to accept/Reject", false);
		}
	}
	
	public void waitAndClick(WebElement element, String elementToClick) throws IOException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element)).click();
			ReportResult.Log("pass", elementToClick + " has been clicked successfully", false);
		}catch (Exception e) {
			ReportResult.Log("fail", elementToClick + " could NOT be clicked", true);
		}
	}
}

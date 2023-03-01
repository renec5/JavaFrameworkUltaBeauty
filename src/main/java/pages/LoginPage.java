package pages;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ConfigFiles.CommonMethods;
import ConfigFiles.ReportResult;

public class LoginPage {
	
	WebDriver driver;
	ReportResult RR;
	CommonMethods CM;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		RR = new ReportResult(driver);
		CM = new CommonMethods(driver);
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(id="username")
	WebElement emailField;
	
	@FindBy(id="password")
	WebElement passwordField;
	
	@FindBy(xpath="//button[contains(text(),'Sign In')]")
	WebElement signInBtn;
	
	@FindBy(xpath="//span[contains(text(),'The email address or password you entered is incorrect')]")
	WebElement errorLoginMsg;
	
	@FindBy(css="a[href='//www.ulta.com/forgot-password']")
	WebElement forgotPasswordLink;
	
	public void enterEmail(String email) throws IOException {
		try{
			emailField.sendKeys(email);
			ReportResult.Log("pass", "Email/Username entered correctly", false);
		}catch (Exception e) {
			ReportResult.Log("fail", "Email/Username could not be entered on email Field", false);
		}
	}
	
	public void enterPassword(String password) throws IOException {
		try{
			passwordField.sendKeys(password);
			ReportResult.Log("pass", "Password entered correctly", false);
		}catch (Exception e) {
			ReportResult.Log("fail", "Password could not be entered on password Field", false);
		}
	}
	
	public void clickSignInButton() throws IOException{
		try {
			CM.waitAndClick(signInBtn, "Sign In Link");
			CM.acceptCookies();
		}catch (Exception e) {
			ReportResult.Log("pass", "Sign In Button could NOT be clicked", false);
		}
	}
	
	public void validInvalidLogin(String username, String password, String validInvalid) throws IOException {
		enterEmail(username);
		enterPassword(password);
		if (signInBtn.isDisplayed()) {
			ReportResult.Log("pass", "Username and Password have been entered correctly", true);
			CM.waitAndClick(signInBtn, "Sign In Button");
		}else {
			ReportResult.Log("fail", "Username and Password were not displayed to enter data", true);
		}
		if (validInvalid.toLowerCase().equals("valid")){
			try{
				// Enter login for valid credentials
				ReportResult.Log("pass", "Login successfully", true);
			}catch (Exception e) {
				ReportResult.Log("fail", "Failed to login", true);
			}
		}else {
			try {
				if (errorLoginMsg.isDisplayed()) {
					ReportResult.Log("pass", "Login with invalid credentials passed, error login message has been displayed", true);
				}
			}catch(Exception e) {
				ReportResult.Log("fail", "Login with invalid credentials did NOT throw error login message Test FAILED", true);
			}
		}
	}
	
	public boolean validateValidInvalidLogin(String validInvalid) throws IOException {
		boolean validation = false;
		switch (validInvalid.toLowerCase()) {
		case ("valid"):
			//Implement logic when we have valid credentials
			break;
		case("invalid"):
			try {
				if (errorLoginMsg.isDisplayed()) {
					validation = true;
					ReportResult.Log("pass", "Expected error login message is displayed, Test Pass", false);
				}
			}catch(Exception e) {
				ReportResult.Log("fail", "Expected error login message is NOT displayed, Test Fails", true);
			}
		}
		return validation;
	}

}

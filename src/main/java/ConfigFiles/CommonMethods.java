package ConfigFiles;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonMethods {

	WebDriver driver;
	WebDriverWait wait;
	
	public CommonMethods(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
	}
	
	public WebElement getElement(WebElement ele) {
		WebElement element = null;
		try {
			
		}catch (Exception e){
			
		}
		return element;
	}
}

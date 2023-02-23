package pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ConfigFiles.ReportResult;

public class SearchResultsPage {
	
	WebDriver driver;
	ReportResult RR;
	
	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		RR = new ReportResult(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="span.search-res-number")
	WebElement searchResultNumber;
	
	public boolean validateSearchResults() throws IOException {
		 int resultsQty = -1;
		 boolean flag = false;
		try {
			resultsQty = Integer.parseInt(searchResultNumber.getText());
			if (resultsQty > 0) {
				flag = true;
				ReportResult.Log("pass", "There are " + resultsQty + " Found" , true);
			}
		}catch (Exception e) {
			ReportResult.Log("fail", "Something went wrong and there are no search results for the specified criteria", true);
		}
		System.out.println(resultsQty);
		return flag;
	}

}

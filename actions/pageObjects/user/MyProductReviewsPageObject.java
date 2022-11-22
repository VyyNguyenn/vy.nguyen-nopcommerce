package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.MyProductReviewPageUI;

public class MyProductReviewsPageObject extends BasePage{
	private WebDriver driver;
	
	public MyProductReviewsPageObject(WebDriver mapDriver) {
		driver = mapDriver;
	}

	public String getMyReviewPageTitle() {
		waitForElementVisible(driver, MyProductReviewPageUI.PAGE_TITLE);
		return getElementText(driver, MyProductReviewPageUI.PAGE_TITLE);
	}

}

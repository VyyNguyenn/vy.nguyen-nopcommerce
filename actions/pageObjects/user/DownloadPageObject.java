package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.DownloadPageUI;

public class DownloadPageObject extends BasePage{
	private WebDriver driver;
	
	public DownloadPageObject(WebDriver mapDriver) {
		driver = mapDriver;
	}

	public String getDownloadPageTitle() {
		waitForElementVisible(driver, DownloadPageUI.PAGE_TITLE);
		return getElementText(driver, DownloadPageUI.PAGE_TITLE);
	}
	

}

package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.BackInStockPageUI;

public class BackInStockPageObject extends BasePage {
	private WebDriver driver;
	
	public BackInStockPageObject(WebDriver mapDriver) {
		driver = mapDriver;
	}

	public String getBackInStockPageTitle() {
		waitForElementVisible(driver, BackInStockPageUI.PAGE_TITLE);
		return getElementText(driver, BackInStockPageUI.PAGE_TITLE);
	}
	
	

}

package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.OrdersPageUI;

public class OrdersPageObject extends BasePage{
	private WebDriver driver;
	
	public OrdersPageObject(WebDriver mapDriver) {
		driver = mapDriver;
	}

	public String getOrdersPageTitle() {
		waitForElementVisible(driver, OrdersPageUI.PAGE_TITLE);
		return getElementText(driver, OrdersPageUI.PAGE_TITLE);
	}

}

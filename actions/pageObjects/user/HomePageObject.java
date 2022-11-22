package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGenerator;
import pageUIs.user.HomePageUI;

public class HomePageObject extends BasePage {
	private WebDriver driver;
	
	public HomePageObject(WebDriver mapDriver) {
		driver = mapDriver;
	}

	public String getHomePageURL() {
		waitForURLTobe(driver, "https://demo.nopcommerce.com/");
		return getCurrentURL(driver);
	}
	
	public RegisterPageObject clickRegisterLink() {
		waitForElementClickable(driver, HomePageUI.REGISTER_LINK);
		clickOnElement(driver, HomePageUI.REGISTER_LINK);
		return PageGenerator.getRegisterPage(driver);
	}

	public LoginPageObject clickLoginLink() {
		waitForElementClickable(driver, HomePageUI.LOGIN_LINK);
		clickOnElement(driver, HomePageUI.LOGIN_LINK);
		return PageGenerator.getLoginPage(driver);
	}

	public CustomerInforPageObject clickMyAccountLink() {
		waitForElementClickable(driver, HomePageUI.MY_ACCOUNT_LINK);
		clickOnElement(driver, HomePageUI.MY_ACCOUNT_LINK);
		return PageGenerator.getMyAccountPage(driver);
	}

}

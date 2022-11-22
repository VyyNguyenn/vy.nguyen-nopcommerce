package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.ChangePasswordPageUI;

public class ChangePasswordPageObject extends BasePage{
	private WebDriver driver;
	
	public ChangePasswordPageObject(WebDriver mapDriver) {
		driver = mapDriver;
	}

	public void enterOldPassword(String password) {
		sendkeyToElement(driver, ChangePasswordPageUI.OLD_PASSWORD, password);
	}
	
	public void enterNewPassword(String updatedPassword) {
		sendkeyToElement(driver, ChangePasswordPageUI.NEW_PASSWORD, updatedPassword);
	}
	
	public void enterConfirmedNewPassword(String updatedPassword) {
		sendkeyToElement(driver, ChangePasswordPageUI.CONFIRMED_NEW_PASSWORD, updatedPassword);
	}

	public void clickChangePassword() {
		waitForElementClickable(driver, ChangePasswordPageUI.CHANGE_PASSWORD_BUTTON);
		clickOnElement(driver, ChangePasswordPageUI.CHANGE_PASSWORD_BUTTON);
	}
	
	public String getSuccessMessage() {
		waitForElementVisible(driver, ChangePasswordPageUI.SUCCESS_MESSAGE);
		return getElementText(driver, ChangePasswordPageUI.SUCCESS_MESSAGE);
	}

	public String getChangePasswordPageTitle() {
		waitForElementVisible(driver, ChangePasswordPageUI.PAGE_TITLE);
		return getElementText(driver, ChangePasswordPageUI.PAGE_TITLE);
	}

	public void closeSuccessBar() {
		waitForElementClickable(driver, ChangePasswordPageUI.CLOSE_SUCCESS_BUTTON);
		clickOnElement(driver, ChangePasswordPageUI.CLOSE_SUCCESS_BUTTON);
		waitForElementInvisible(driver, ChangePasswordPageUI.CLOSE_SUCCESS_BUTTON);
	}

	
}

package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGenerator;
import pageUIs.user.RegisterPageUI;

public class RegisterPageObject extends BasePage{
	private WebDriver driver;
	
	public RegisterPageObject(WebDriver mapDriver) {
		driver = mapDriver;
	}

	public void clearFirstNameTextbox() {
		clearTextbox(driver, RegisterPageUI.FIRST_NAME_TEXTBOX);
	}

	public void clearLastNameTextbox() {
		clearTextbox(driver, RegisterPageUI.LAST_NAME_TEXTBOX);
	}

	public void clearEmailTextbox() {
		clearTextbox(driver, RegisterPageUI.EMAIl_TEXTBOX);
	}

	public void clearPasswordTextbox() {
		clearTextbox(driver, RegisterPageUI.PASSWORD_TEXTBOX);
	}

	public void clearConfirmedPasswordTextbox() {
		clearTextbox(driver, RegisterPageUI.CONFIRMED_PASSWORD_TEXTBOX);
	}

	public void clickRegisterButton() {
		clickOnElement(driver, RegisterPageUI.RESGITER_BUTTON);
	}

	public void enterFirstName(String firstName) {
		sendkeyToElement(driver, RegisterPageUI.FIRST_NAME_TEXTBOX, firstName);
	}

	public void enterLastName(String lastName) {
		sendkeyToElement(driver, RegisterPageUI.LAST_NAME_TEXTBOX, lastName);
	}

	public void enterEmail(String email) {
		sendkeyToElement(driver, RegisterPageUI.EMAIl_TEXTBOX, email);
	}

	public void enterPassword(String password) {
		sendkeyToElement(driver, RegisterPageUI.PASSWORD_TEXTBOX, password);
	}

	public void enterConfirmedPassword(String confirmedPassword) {
		sendkeyToElement(driver, RegisterPageUI.CONFIRMED_PASSWORD_TEXTBOX, confirmedPassword);
	}

	public String getEmailErrorMessage() {
		return getElementText(driver, RegisterPageUI.EMAIL_VALIDATION_MESSAGE);
	}

	public String getPasswordErrorMessage() {
		return getElementText(driver, RegisterPageUI.PASSWORD_VALIDATION_MESSAGE);
	}

	public String getConfirmedPasswordErrorMessage() {
		return getElementText(driver, RegisterPageUI.CONFIRMED_PASSWORD_VALIDATION_MESSAGE);
	}

	public String getFirstNameErrorMessage() {
		return getElementText(driver, RegisterPageUI.FIRST_NAME_VALIDATION_MESSAGE);
	}
	public String getLastNameErrorMessage() {
		return getElementText(driver, RegisterPageUI.LAST_NAME_VALIDATION_MESSAGE);
	}
	
	public String getRegisterSuccessMessage() {
		return getElementText(driver, RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
	}

	public String getRegisterErrorMessage() {
		return getElementText(driver, RegisterPageUI.REGISTER_ERROR_MESSAGE);
	}

	public HomePageObject logout() {
		waitForElementClickable(driver, RegisterPageUI.LOG_OUT_LINK);
		clickOnElement(driver, RegisterPageUI.LOG_OUT_LINK);
		return PageGenerator.getHomePage(driver);
	}

	public CustomerInforPageObject clickMyAccountLink() {
		waitForElementClickable(driver, RegisterPageUI.LOG_OUT_LINK);
		clickOnElement(driver, RegisterPageUI.LOG_OUT_LINK);
		return PageGenerator.getMyAccountPage(driver);
	}
}

package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.AddressPageUI;

public class AddressPageObject extends BasePage{
	private WebDriver driver;
	
	public AddressPageObject(WebDriver mapDriver) {
		driver = mapDriver;
	}

	public void clickAddNewButton() {
		waitForElementClickable(driver, AddressPageUI.ADD_NEW_BUTTON);
		clickOnElement(driver, AddressPageUI.ADD_NEW_BUTTON);
	}

	public void updateFirstName(String updatedFirstName) {
		sendkeyToElement(driver, AddressPageUI.FIRST_NAME_TEXTBOX, updatedFirstName);
	}

	public void updateLastName(String updatedLastName) {
		sendkeyToElement(driver, AddressPageUI.LAST_NAME_TEXTBOX, updatedLastName);
	}

	public void updateEmail(String updatedEmail) {
		sendkeyToElement(driver, AddressPageUI.EMAIl_TEXTBOX, updatedEmail);
	}

	public void selectCountry(String country) {
		selectItemInDropdown(driver, AddressPageUI.COUNTRY_DROPDOWN_LIST, country);
	}
	
	public void selectState(String state) {
		waitForElementInvisible(driver, AddressPageUI.STATES_PROGRESS_LOAD_ICON);
		selectItemInDropdown(driver, AddressPageUI.STATE_DROPDOWN_LIST, state);
	}	

	public void enterCity(String city) {
		sendkeyToElement(driver, AddressPageUI.CITY_TEXTBOX, city);
	}

	
	public void enterAddress1(String address1) {
		sendkeyToElement(driver, AddressPageUI.ADDRESS1_TEXTBOX, address1);
	}

	public void enterZipCode(String zipCode) {
		sendkeyToElement(driver, AddressPageUI.ZIP_CODE_TEXTBOX, zipCode);
	}
	
	public void enterPhoneNumber(String phoneNumber) {
		sendkeyToElement(driver, AddressPageUI.PHONE_NUMBER_TEXTBOX, phoneNumber);
	}

	public String getCustomerName() {
		waitForElementVisible(driver, AddressPageUI.CUSTOMER_NAME);
		return getElementText(driver, AddressPageUI.CUSTOMER_NAME);
	}
	
	public String getLastName() {
		waitForElementVisible(driver, AddressPageUI.LAST_NAME_TEXTBOX);
		return getElementAttribute(driver, AddressPageUI.LAST_NAME_TEXTBOX, "value");
	}

	public String getAddress1() {
		waitForElementVisible(driver, AddressPageUI.ADDRESS1);
		return getElementText(driver, AddressPageUI.ADDRESS1);
	}

	public String getCityStateZip() {
		waitForElementVisible(driver, AddressPageUI.CITY_STATE_ZIP);
		return getElementText(driver, AddressPageUI.CITY_STATE_ZIP);
	}	

	public String getEmail() {
		waitForElementVisible(driver, AddressPageUI.CUSTOMER_EMAIL);
		return getElementText(driver, AddressPageUI.CUSTOMER_EMAIL);
	}

	public void clickSaveButton() {
		waitForElementClickable(driver, AddressPageUI.SAVE_BUTTON);
		clickOnElement(driver, AddressPageUI.SAVE_BUTTON);
	}

	public String getAddressPageTitle() {
		waitForElementVisible(driver, AddressPageUI.PAGE_TITLE);
		return getElementText(driver, AddressPageUI.PAGE_TITLE);
	}
}

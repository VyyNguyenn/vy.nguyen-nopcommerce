package pageObjects.user;

import org.openqa.selenium.WebDriver;
import commons.BasePage;
import pageUIs.user.CustomerInfoPageUI;

public class CustomerInforPageObject extends BasePage{
	private WebDriver driver;
	
	public CustomerInforPageObject(WebDriver mapDriver) {
		driver = mapDriver;
	}

	public void chooseGender() {
		waitForElementClickable(driver, CustomerInfoPageUI.FEMALE_GENDER);
		clickOnElement(driver, CustomerInfoPageUI.FEMALE_GENDER);
	}

	public void updateDOB(String dobd, String dobm, String doby) {
		selectItemInDropdown(driver, CustomerInfoPageUI.DOB_DAY, dobd);
		selectItemInDropdown(driver, CustomerInfoPageUI.DOB_MONTH, dobm);
		selectItemInDropdown(driver, CustomerInfoPageUI.DOB_YEAR, doby);
	}

	public void updateFirstName(String updatedFirstName) {
		sendkeyToElement(driver, CustomerInfoPageUI.FIRST_NAME_TEXTBOX, updatedFirstName);
	}

	public void updateLastName(String updatedLastName) {
		sendkeyToElement(driver, CustomerInfoPageUI.LAST_NAME_TEXTBOX, updatedLastName);
	}

	public void updateEmail(String updatedEmail) {
		sendkeyToElement(driver, CustomerInfoPageUI.EMAIl_TEXTBOX, updatedEmail);
	}

	public void updateCompanyName(String updatedCompanyName) {
		sendkeyToElement(driver, CustomerInfoPageUI.COMPANY_NAME_TEXTBOX, updatedCompanyName);
	}
	

	public void clickSaveButton() {
		waitForElementClickable(driver, CustomerInfoPageUI.SAVE_BUTTON);
		clickOnElement(driver, CustomerInfoPageUI.SAVE_BUTTON);
	}

	public String getFirstName() {
		waitForElementVisible(driver, CustomerInfoPageUI.FIRST_NAME_TEXTBOX);
		return getElementAttribute(driver, CustomerInfoPageUI.FIRST_NAME_TEXTBOX, "value");				
	}
	
	public String getLastName() {
		waitForElementVisible(driver, CustomerInfoPageUI.LAST_NAME_TEXTBOX);
		return getElementAttribute(driver, CustomerInfoPageUI.LAST_NAME_TEXTBOX, "value");
	}
	
	public String getEmail() {
		waitForElementVisible(driver, CustomerInfoPageUI.EMAIl_TEXTBOX);
		return getElementAttribute(driver, CustomerInfoPageUI.EMAIl_TEXTBOX, "value");
	}
	
	public String getCompanyName() {
		waitForElementVisible(driver, CustomerInfoPageUI.COMPANY_NAME_TEXTBOX);
		return getElementAttribute(driver, CustomerInfoPageUI.COMPANY_NAME_TEXTBOX, "value");
	}

}

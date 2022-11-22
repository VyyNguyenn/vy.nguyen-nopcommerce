package nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGenerator;
import pageObjects.user.AddressPageObject;
import pageObjects.user.BackInStockPageObject;
import pageObjects.user.ChangePasswordPageObject;
import pageObjects.user.DownloadPageObject;
import pageObjects.user.HomePageObject;
import pageObjects.user.LoginPageObject;
import pageObjects.user.CustomerInforPageObject;
import pageObjects.user.MyProductReviewsPageObject;
import pageObjects.user.OrdersPageObject;
import pageObjects.user.RegisterPageObject;
import pageObjects.user.RewardPointsPageObject;

public class MyAccount extends BaseTest{
	private WebDriver driver;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	private CustomerInforPageObject myAccountPage;
	private AddressPageObject addressPage;
	private ChangePasswordPageObject changePasswordPage;
	private MyProductReviewsPageObject myReviewPage;
	private OrdersPageObject ordersPage;
	private BackInStockPageObject backInStockPage;
	private DownloadPageObject downloadPage;
	private RewardPointsPageObject rewardPointsPage;
	private String registeredEmail, password, firstName, lastName, updatedFirstName, updatedLastName, updatedEmail, updatedCompanyName, 
					address1, zipCode, updatedPassword, day, month, year, phoneNumber, country, state, city ;
	
	
	@Parameters({"browserName", "environment"})
	@BeforeClass
	public void BeforeClass(String browserName, String environment) {		
		driver = getBrowser(browserName, environment);
	
		homePage = PageGenerator.getHomePage(driver);
		
		registeredEmail = "registered" + getRandomNumber() + "@mail.com";		
		password = "123456";	
		firstName = "Vy";
		lastName = "Nguyen";
		
		updatedFirstName = "updated Vy";
		updatedLastName = "updated Nguyen";
		updatedEmail = "updated" + getRandomNumber() + "@gmail.com";
		updatedCompanyName = "Comp";
		day = "10";
		month = "December";
		year = "1996";
		
		address1 = "Ho Chi Minh";
		zipCode = "70000";
		country = "United States";
		state = "Kansas";
		city = "HCM";
		phoneNumber = "1234567890";
		
		updatedPassword = "123456a";
		
		//Pre-condition: Successfully registered and logged in
		homePage.openHeaderLink(driver, "ico-register");
		registerPage = PageGenerator.getRegisterPage(driver);		
		registerPage.enterFirstName(firstName);
		registerPage.enterLastName(lastName);
		registerPage.enterEmail(registeredEmail);
		registerPage.enterPassword(password);
		registerPage.enterConfirmedPassword(password);
		registerPage.clickRegisterButton();
		System.out.println(registeredEmail);
	}
	
	@Test
	public void MA_01_Update_Info() {
		System.out.println("MA_01: Verify update customer information");
		registerPage.openHeaderLink(driver, "ico-account");
		myAccountPage = PageGenerator.getMyAccountPage(driver);
		myAccountPage.chooseGender();
		myAccountPage.updateFirstName(updatedFirstName);
		myAccountPage.updateLastName(updatedLastName);
		myAccountPage.updateDOB(day, month, year);
		myAccountPage.updateEmail(updatedEmail);
		myAccountPage.updateCompanyName(updatedCompanyName);
		myAccountPage.clickSaveButton();
		
		Assert.assertEquals(myAccountPage.getFirstName(), updatedFirstName);
		Assert.assertEquals(myAccountPage.getLastName(), updatedLastName);
		Assert.assertEquals(myAccountPage.getEmail(), updatedEmail);
		Assert.assertEquals(myAccountPage.getCompanyName(), updatedCompanyName);
		
	}
	
	@Test
	public void MA_02_Add_Address() {
		System.out.println("MA_02: Verify user can add new address");
		myAccountPage.openMyAccountSideLink(driver, "Addresses");
		addressPage = PageGenerator.getAddressPage(driver);
		addressPage.clickAddNewButton();
		addressPage.updateFirstName(updatedFirstName);
		addressPage.updateLastName(updatedLastName);
		addressPage.updateEmail(updatedEmail);
		addressPage.selectCountry(country);
		addressPage.selectState(state);
		addressPage.enterCity(city);
		addressPage.enterAddress1(address1);
		addressPage.enterZipCode(zipCode);
		addressPage.enterPhoneNumber(phoneNumber);
		addressPage.clickSaveButton();
		
		Assert.assertEquals(addressPage.getCustomerName(), updatedFirstName + " " + updatedLastName);
		Assert.assertEquals(addressPage.getEmail(), "Email: " + updatedEmail);
		Assert.assertEquals(addressPage.getAddress1(), address1);
		Assert.assertEquals(addressPage.getCityStateZip(), city + ", " + state + ", " + zipCode);
		
	}
	
	@Test
	public void MA_03_Change_Password() {
		System.out.println("MA_03: Verify user can change password");
		addressPage.openMyAccountSideLink(driver, "Change password");
		changePasswordPage = PageGenerator.getChangePasswordPage(driver);
		changePasswordPage.enterOldPassword(password);
		changePasswordPage.enterNewPassword(updatedPassword);
		changePasswordPage.enterConfirmedNewPassword(updatedPassword);
		changePasswordPage.clickChangePassword();
		
		Assert.assertEquals(changePasswordPage.getSuccessMessage(), "Password was changed");
		
		changePasswordPage.closeSuccessBar();
		changePasswordPage.openHeaderLink(driver, "ico-logout");
		homePage = PageGenerator.getHomePage(driver);
		homePage.openHeaderLink(driver, "ico-login");
		loginPage = PageGenerator.getLoginPage(driver);
		loginPage.enterEmail(updatedEmail);
		loginPage.enterPassword(updatedPassword);
		homePage = loginPage.clickLoginButton();
		homePage.openHeaderLink(driver, "ico-account");
		myAccountPage = PageGenerator.getMyAccountPage(driver);
		
	}
	
	@Test
	public void MA_04_Account_Navigation() {
		System.out.println("MA_04: Verify navigating to side menu pages");
		myAccountPage.openMyAccountSideLink(driver, "Addresses");
		addressPage = PageGenerator.getAddressPage(driver);
		Assert.assertEquals(addressPage.getAddressPageTitle(), "My account - Addresses");
		
		addressPage.openMyAccountSideLink(driver, "Change password");
		changePasswordPage = PageGenerator.getChangePasswordPage(driver);
		Assert.assertEquals(changePasswordPage.getChangePasswordPageTitle(), "My account - Change password");
		
		changePasswordPage.openMyAccountSideLink(driver, "My product reviews");
		myReviewPage = PageGenerator.getMyProductReviewPage(driver);
		Assert.assertEquals(myReviewPage.getMyReviewPageTitle(), "My account - My product reviews");
		
		myReviewPage.openMyAccountSideLink(driver, "Orders");
		ordersPage = PageGenerator.getOrdersPage(driver);
		Assert.assertEquals(ordersPage.getOrdersPageTitle(), "My account - Orders");
		
		ordersPage.openMyAccountSideLink(driver, "Back in stock subscriptions");
		backInStockPage = PageGenerator.getBackInStockPage(driver);
		Assert.assertEquals(backInStockPage.getBackInStockPageTitle(), "My account - Back in stock subscriptions");
		
		backInStockPage.openMyAccountSideLink(driver, "Downloadable products");
		downloadPage = PageGenerator.getDownloadPage(driver);
		Assert.assertEquals(downloadPage.getDownloadPageTitle(),"My account - Downloadable products");
		
		downloadPage.openMyAccountSideLink(driver, "Reward points");
		rewardPointsPage = PageGenerator.getRewardPointsPage(driver);
		Assert.assertEquals(rewardPointsPage.getRewardPointsPageTitle(), "My account - Reward points");
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}

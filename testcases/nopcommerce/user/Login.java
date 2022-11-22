package nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGenerator;
import pageObjects.user.HomePageObject;
import pageObjects.user.LoginPageObject;
import pageObjects.user.RegisterPageObject;

public class Login extends BaseTest{
	private WebDriver driver;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	private String registeredEmail, unregisteredEmail, invalidEmail, password, wrongPassword, firstName, lastName;
	
	@Parameters({"browserName", "environment"})
	@BeforeClass
	public void BeforeClass(String browserName, String environment) {		
		driver = getBrowser(browserName, environment);
	
		homePage = PageGenerator.getHomePage(driver);
		
		registeredEmail = "registered" + getRandomNumber() + "@mail.com";
		unregisteredEmail = "unRegistered" + getRandomNumber() + "@mail.com";
		invalidEmail = "not an email";
		password = "123456";
		wrongPassword = "123456a";
		firstName = "Vy";
		lastName = "Nguyen";
		
		//Pre-condition: Successfully registered an account
		homePage.openHeaderLink(driver, "ico-register");
		registerPage = PageGenerator.getRegisterPage(driver);
		registerPage.enterFirstName(firstName);
		registerPage.enterLastName(lastName);
		registerPage.enterEmail(registeredEmail);
		registerPage.enterPassword(password);
		registerPage.enterConfirmedPassword(password);
		registerPage.clickRegisterButton();
		System.out.println(registeredEmail);
		registerPage.openHeaderLink(driver, "ico-logout");
		homePage = PageGenerator.getHomePage(driver);
	}
	
	@Test
	public void Log_01_Empty_Data() {
		System.out.println("Log_01: Verify login without enter email and password");
		homePage.openHeaderLink(driver, "ico-login");
		loginPage = PageGenerator.getLoginPage(driver);
		loginPage.clearEmail();
		loginPage.clearPassword();
		loginPage.clickLoginButton();
		
		Assert.assertEquals(loginPage.getEmailErrorMessage(), "Please enter your email");
	}
	
	@Test
	public void Log_02_Invalid_Email() {
		System.out.println("Log_02: Verify login with invalid email");
		
		loginPage.enterEmail(invalidEmail);
		loginPage.enterPassword(password);
		loginPage.clickLoginButton();
		
		Assert.assertEquals(loginPage.getEmailErrorMessage(), "Wrong email");
	}
	
	@Test
	public void Log_03_Unregistered_Email() {
		System.out.println("Log_03: Verify login with unregistered email");
		
		loginPage.enterEmail(unregisteredEmail);
		loginPage.enterPassword(password);
		loginPage.clickLoginButton();
		
		Assert.assertEquals(loginPage.getLoginErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	}
	
	@Test
	public void Log_04_Empty_Password() {
		System.out.println("Log_04: Verify login without entering password");
		
		loginPage.enterEmail(registeredEmail);
		loginPage.clearPassword();
		loginPage.clickLoginButton();
		
		Assert.assertEquals(loginPage.getLoginErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}
	
	@Test
	public void Log_05_Wrong_Password() {
		System.out.println("Log_05: Verify login with wrong password");
		
		loginPage.enterEmail(registeredEmail);
		loginPage.enterPassword(wrongPassword);
		loginPage.clickLoginButton();
		
		Assert.assertEquals(loginPage.getLoginErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}
	
	@Test
	public void Log_06_Successful() {
		System.out.println("Log_06: Verify login successfully");
		
		loginPage.enterEmail(registeredEmail);
		loginPage.enterPassword(password);
		homePage = loginPage.clickLoginButton();
		
		Assert.assertEquals(homePage.getHomePageURL(), "https://demo.nopcommerce.com/");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}

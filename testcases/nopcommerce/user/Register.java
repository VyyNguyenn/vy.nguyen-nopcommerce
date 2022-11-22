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
import pageObjects.user.RegisterPageObject;

public class Register extends BaseTest{
	private WebDriver driver;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	
	private String firstName, lastName, validEmail, invalidEmail, password;
	
	@Parameters({"browserName", "environment"})
	@BeforeClass
	public void BeforeClass(String browserName, String environment) {		
		driver = getBrowser(browserName, environment);

		homePage = PageGenerator.getHomePage(driver);
		
		firstName = "Vy";
		lastName = "Nguyen";
		validEmail = "valid" + getRandomNumber() + "@email.com";
		invalidEmail = "notanemail";
		password = "123456";
	}
	
	@Test
	public void Reg_01_Empty_Data() {
		System.out.println("Reg_01: Verify register with empty data");
		homePage.openHeaderLink(driver,"ico-register");
		registerPage = PageGenerator.getRegisterPage(driver);
		registerPage.clearFirstNameTextbox();
		registerPage.clearLastNameTextbox();
		registerPage.clearEmailTextbox();
		registerPage.clearPasswordTextbox();
		registerPage.clearConfirmedPasswordTextbox();
		registerPage.clickRegisterButton();
		
		Assert.assertEquals(registerPage.getFirstNameErrorMessage(), "First name is required.");
		Assert.assertEquals(registerPage.getLastNameErrorMessage(), "Last name is required.");
		Assert.assertEquals(registerPage.getEmailErrorMessage(), "Email is required.");
		Assert.assertEquals(registerPage.getPasswordErrorMessage(), "Password is required.");
		Assert.assertEquals(registerPage.getConfirmedPasswordErrorMessage(), "Password is required.");
	}
	
	@Test
	public void Reg_02_Invalid_Email() {
		System.out.println("Reg_02: Verify register with invalid email");
	
		registerPage.enterFirstName(firstName);
		registerPage.enterLastName(lastName);
		registerPage.enterEmail(invalidEmail);
		registerPage.enterPassword(password);
		registerPage.enterConfirmedPassword(password);
		registerPage.clickRegisterButton();
		
		Assert.assertEquals(registerPage.getEmailErrorMessage(), "Wrong email");
	}
	
	@Test
	public void Reg_03_Unacceptable_Password() {
		System.out.println("Reg_03: Verify register with password has less than 6 characters");
		
		registerPage.enterFirstName(firstName);
		registerPage.enterLastName(lastName);
		registerPage.enterEmail(validEmail);
		registerPage.enterPassword("12345");
		registerPage.enterConfirmedPassword("12345");
		registerPage.clickRegisterButton();
		
		Assert.assertEquals(registerPage.getPasswordErrorMessage(), "Password must meet the following rules:\nmust have at least 6 characters");
	}
	
	@Test
	public void Reg_04_ConfirmedPassword_Not_Match() {
		System.out.println("Reg_04: Verify register with password and confirmed password not match");
		
		registerPage.enterFirstName(firstName);
		registerPage.enterLastName(lastName);
		registerPage.enterEmail(validEmail);
		registerPage.enterPassword(password);
		registerPage.enterConfirmedPassword("123457");
		registerPage.clickRegisterButton();
		
		Assert.assertEquals(registerPage.getConfirmedPasswordErrorMessage(), "The password and confirmation password do not match.");
	}
	
	@Test
	public void Reg_05_Success() {
		System.out.println("Reg_05: Verify register successfully");
		
		registerPage.enterFirstName(firstName);
		registerPage.enterLastName(lastName);
		registerPage.enterEmail(validEmail);
		registerPage.enterPassword(password);
		registerPage.enterConfirmedPassword(password);
		registerPage.clickRegisterButton();
		
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		
		registerPage.openHeaderLink(driver, "ico-logout");
		homePage = PageGenerator.getHomePage(driver);
	}
	
	@Test
	public void Reg_06_Existing_Email() {
		System.out.println("Reg_06: Verify register with existing email");
		homePage.openHeaderLink(driver, "ico-register");
		registerPage = PageGenerator.getRegisterPage(driver);
		registerPage.enterFirstName(firstName);
		registerPage.enterLastName(lastName);
		registerPage.enterEmail(validEmail);
		registerPage.enterPassword(password);
		registerPage.enterConfirmedPassword(password);
		registerPage.clickRegisterButton();
		
		Assert.assertEquals(registerPage.getRegisterErrorMessage(), "The specified email already exists");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}

package pageUIs.user;

public class RegisterPageUI {
	public static final String LOG_OUT_LINK = "xpath=//a[@class='ico-logout']";
	public static final String MY_ACCOUNT_LINK = "xpath=//a[@class='ico-account']";
	
	public static final String FIRST_NAME_TEXTBOX = "id=FirstName";
	public static final String LAST_NAME_TEXTBOX = "css=input[id='LastName']";
	public static final String EMAIl_TEXTBOX = "xpath=//input[@id='Email']";
	public static final String PASSWORD_TEXTBOX = "xpath=//input[@id='Password']";
	public static final String CONFIRMED_PASSWORD_TEXTBOX = "xpath=//input[@id='ConfirmPassword']";
	
	public static final String RESGITER_BUTTON = "CSS=button[id='register-button']";
	
	public static final String FIRST_NAME_VALIDATION_MESSAGE = "xpath=//span[@id='FirstName-error']";
	public static final String LAST_NAME_VALIDATION_MESSAGE = "xpath=//span[@id='LastName-error']";
	public static final String EMAIL_VALIDATION_MESSAGE = "xpath=//span[@id='Email-error']";
	public static final String PASSWORD_VALIDATION_MESSAGE = "xpath=//span[@id='Password-error']";
	public static final String CONFIRMED_PASSWORD_VALIDATION_MESSAGE = "xpath=//span[@id='ConfirmPassword-error']";
	public static final String REGISTER_SUCCESS_MESSAGE = "xpath=//div[@class='result']";
	public static final String REGISTER_ERROR_MESSAGE = "xpath=//div[@class='message-error validation-summary-errors']";

}

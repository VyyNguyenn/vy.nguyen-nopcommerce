package pageUIs.user;

public class ChangePasswordPageUI {
	public static final String PAGE_TITLE = "xpath=//div[@class='page-title']";
	
	public static final String OLD_PASSWORD = "id=OldPassword";
	public static final String NEW_PASSWORD = "id=NewPassword";
	public static final String CONFIRMED_NEW_PASSWORD = "id=ConfirmNewPassword";
	
	public static final String CHANGE_PASSWORD_BUTTON = "xpath=//button[contains(@class,'change-password-button')]";
	
	public static final String SUCCESS_MESSAGE = "xpath=//div[@class='bar-notification success']";
	public static final String SUCCESS_NOTIFICATION_BAR = "class=bar-notification-container";
	public static final String CLOSE_SUCCESS_BUTTON = "class=close";
}

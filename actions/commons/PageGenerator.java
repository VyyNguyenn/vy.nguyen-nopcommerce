package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.user.AddressPageObject;
import pageObjects.user.BackInStockPageObject;
import pageObjects.user.ChangePasswordPageObject;
import pageObjects.user.DownloadPageObject;
import pageObjects.user.HomePageObject;
import pageObjects.user.LoginPageObject;
import pageObjects.user.CustomerInforPageObject;
import pageObjects.user.OrdersPageObject;
import pageObjects.user.MyProductReviewsPageObject;
import pageObjects.user.RegisterPageObject;
import pageObjects.user.RewardPointsPageObject;

public class PageGenerator {
	
	public static HomePageObject getHomePage(WebDriver driver) {
		return new HomePageObject(driver);
	}
	
	public static RegisterPageObject getRegisterPage(WebDriver driver) {
		return new RegisterPageObject(driver);
	}
	
	public static LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}
	
	public static CustomerInforPageObject getMyAccountPage(WebDriver driver) {
		return new CustomerInforPageObject(driver);
	}
	
	public static AddressPageObject getAddressPage(WebDriver driver) {
		return new AddressPageObject(driver);
	}
	
	public static OrdersPageObject getOrdersPage(WebDriver driver) {
		return new OrdersPageObject(driver);
	}
	
	public static DownloadPageObject getDownloadPage(WebDriver driver) {
		return new DownloadPageObject(driver);
	}
	
	public static BackInStockPageObject getBackInStockPage(WebDriver driver) {
		return new BackInStockPageObject(driver);
	}
	
	public static RewardPointsPageObject getRewardPointsPage(WebDriver driver) {
		return new RewardPointsPageObject(driver);
	}
	
	public static ChangePasswordPageObject getChangePasswordPage(WebDriver driver) {
		return new ChangePasswordPageObject(driver);
	}
	
	public static MyProductReviewsPageObject getMyProductReviewPage(WebDriver driver) {
		return new MyProductReviewsPageObject(driver);
	}

}

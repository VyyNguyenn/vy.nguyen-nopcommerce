package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.RewardPointsPageUI;

public class RewardPointsPageObject extends BasePage{
	private WebDriver driver;
	
	public RewardPointsPageObject(WebDriver mapDriver) {
		driver = mapDriver;
	}

	public String getRewardPointsPageTitle() {
		waitForElementVisible(driver, RewardPointsPageUI.PAGE_TITLE);
		return getElementText(driver, RewardPointsPageUI.PAGE_TITLE);
	}

}

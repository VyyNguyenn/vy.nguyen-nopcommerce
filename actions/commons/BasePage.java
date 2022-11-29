package commons;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.user.AddressPageObject;
import pageObjects.user.BackInStockPageObject;
import pageObjects.user.ChangePasswordPageObject;
import pageObjects.user.DownloadPageObject;
import pageObjects.user.HomePageObject;
import pageObjects.user.CustomerInforPageObject;
import pageObjects.user.MyProductReviewsPageObject;
import pageObjects.user.OrdersPageObject;
import pageObjects.user.RewardPointsPageObject;
import pageUIs.user.BasePageUI;

public class BasePage {
	
	protected void openURL(WebDriver driver, String urlValue) {
		driver.get(urlValue);
	}
	
	protected String getCurrentURL(WebDriver driver) {
		return driver.getCurrentUrl();
	}
	
	protected boolean waitForURLTobe(WebDriver driver, String url) {
		return new WebDriverWait(driver, 20).until(ExpectedConditions.urlToBe(url));
	}
	
	protected String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}
	
	protected String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}
	
	protected void backToPreviousPage(WebDriver driver) {
		driver.navigate().back();
	}
	
	protected void forwardToNextPage(WebDriver driver) {
		driver.navigate().forward();
	}
	
	protected void refreshPage(WebDriver driver) {
		driver.navigate().refresh();
	}
	
	protected void waitForAlertPresence(WebDriver driver) {
		new WebDriverWait(driver, 20).until(ExpectedConditions.alertIsPresent());
	}
	
	protected void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	
	protected void dismissAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	
	protected String getAlertText(WebDriver driver) {
		return driver.switchTo().alert().getText();
	}
	
	protected void sendkeyAlert(WebDriver driver, String keyToAlert) {
		driver.switchTo().alert().sendKeys(keyToAlert);
	}
	
	protected void switchWindowByID(WebDriver driver, String parentWindowID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindowID : allWindows) {
			if (!runWindowID.equals(parentWindowID)) {
				driver.switchTo().window(runWindowID);
				break;
			}
		}
	}
	
	protected void switchByTitle (WebDriver driver, String windowTitle) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			driver.switchTo().window(runWindow);
			String currentTitle = driver.getTitle();
			if (currentTitle.equals(windowTitle)) {
				break;
			}
		}
	}
	
	protected void closeWindow (WebDriver driver, String parentWindowID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindowID : allWindows) {
			if (!runWindowID.equals(parentWindowID)) {
				driver.switchTo().window(runWindowID);
				driver.close();
			}
		}
		driver.switchTo().window(parentWindowID);
	}

	protected void sleepInSecond (long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	protected By getByLocator(String locatorType) {
		By by = null;
		//System.out.println("Locator:" + locatorType);
		if (locatorType.toUpperCase().startsWith("ID")) {
			by = By.id(locatorType.substring(3));
		} else if(locatorType.toUpperCase().startsWith("CLASS")) {
			by = By.className(locatorType.substring(6));
		} else if(locatorType.toUpperCase().startsWith("NAME")) {
			by = By.name(locatorType.substring(5));
		} else if(locatorType.toUpperCase().startsWith("CSS")) {
			by = By.cssSelector(locatorType.substring(4));
		}  else if(locatorType.toUpperCase().startsWith("XPATH")) {
			by = By.xpath(locatorType.substring(6));
		} else {
			throw new RuntimeException("Locator Type is not supported!");
		}
		return by;
	}
	
	protected String getDynamicXpath(String locatorType, String... dynamicValue) {
		if (locatorType.toUpperCase().startsWith("XPATH")) {
			locatorType = String.format(locatorType, (Object[]) dynamicValue);
		}
		return locatorType;
	}
	
	protected WebElement getElement (WebDriver driver, String locator) {
		return driver.findElement(getByLocator(locator));
	}
	
	protected List<WebElement> getListElement (WebDriver driver, String locator) {
		return driver.findElements(getByLocator(locator));
	}
	
	protected void clickOnElement (WebDriver driver, String locator) {
		getElement(driver, locator).click();
	}
	
	protected void clickOnElement (WebDriver driver, String locator, String... dynamicValue) {
		getElement(driver, getDynamicXpath(locator, dynamicValue)).click();
	}
	
	protected void clearTextbox (WebDriver driver, String locator) {
		getElement(driver, locator).clear();
	}	
	
	protected String getElementText(WebDriver driver, String locator) {
		return getElement(driver, locator).getText();
	}
	
	protected String getElementText(WebDriver driver, String locator, String... dynamicValue) {
		return getElement(driver, getDynamicXpath(locator, dynamicValue)).getText();
	}
	
	protected void sendkeyToElement (WebDriver driver, String locator, String keyword) {
		clearTextbox(driver, locator);
		getElement(driver, locator).sendKeys(keyword);
	}
	
	protected void selectItemInDropdown(WebDriver driver, String dropdownLocator, String itemValue) {
		new Select(getElement(driver, dropdownLocator)).selectByVisibleText(itemValue);
	}
	
	protected String getFirstSelectedItemInDropdown(WebDriver driver, String dropdownLocator) {
		return new Select(getElement(driver, dropdownLocator)).getFirstSelectedOption().getText();
	}
	
	protected void selectFromCustomDropdownList(WebDriver driver, String dropdownLocator, String itemLocator, String expectedItem) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 15);
		getElement(driver, dropdownLocator).click(); 		
		
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(itemLocator)));
		List <WebElement> allItems = driver.findElements(By.xpath(itemLocator));
		
		for (WebElement item : allItems) {
			String actualItem = item.getText().trim();
			if (actualItem.equals(expectedItem)) {
				explicitWait.until(ExpectedConditions.elementToBeClickable(item));
				item.click();
				System.out.println("Selected item: "+ actualItem);
				sleepInSecond(2);
				break;
			}
		}
	}
	
	protected boolean isDropdownMultiple (WebDriver driver, String locator) {
		return new Select(getElement(driver, locator)).isMultiple();
	}
	
	protected String getElementAttribute (WebDriver driver, String locator, String attributeName) {
		return getElement(driver, locator).getAttribute(attributeName);
	}
	
	protected String getCSSValue (WebDriver driver, String locator, String propertyName) {
		return getElement(driver, locator).getCssValue(propertyName);
	}
	
	protected String convertRGBToHex (String rgbValue) {
		return Color.fromString(rgbValue).asHex();
	}
	
	protected String convertHexToRGB (String hexValue) {
		return Color.fromString(hexValue).asRgb();
	}
	
	protected int getElementsSize (WebDriver driver, String locator) {
		return getListElement(driver, locator).size();
	}
	
	protected void checkCheckbox(WebDriver driver, String locator) {
		if (!getElement(driver, locator).isSelected()){
			getElement(driver, locator).click();
		}
	}
	
	protected void unCheckCheckbox(WebDriver driver, String locator) {
		if (getElement(driver, locator).isSelected()){
			getElement(driver, locator).click();
		}
	}
	
	protected boolean isElementDisplayed (WebDriver driver, String locator) {
		return getElement(driver, locator).isDisplayed();
	}
	
	protected boolean isElementSelected (WebDriver driver, String locator) {
		return getElement(driver, locator).isSelected();
	}
	
	protected boolean isElementEnabled (WebDriver driver, String locator) {
		return getElement(driver, locator).isEnabled();
	}
	
	protected void switchToFrame(WebDriver driver, String locator) {
		driver.switchTo().frame(getElement(driver, locator));
	}
	
	protected void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	
	protected void doubleClickOnElement(WebDriver driver, String locator) {
		new Actions(driver).doubleClick(getElement(driver, locator)).perform();
	}
	
	protected void hoverOnElement(WebDriver driver, String locator) {
		new Actions(driver).moveToElement(getElement(driver, locator)).perform();
	}
	
	protected void rightClickOnElement(WebDriver driver, String locator) {
		new Actions(driver).contextClick(getElement(driver, locator)).perform();
	}
	
	protected void dragAndDrop(WebDriver driver, String locatorSource, String locatorTarget) {
		new Actions(driver).dragAndDrop(getElement(driver, locatorSource), getElement(driver, locatorTarget)).perform();
	}
	
	protected void sendKeyboardToElement(WebDriver driver, String locator, Keys key) {
		new Actions(driver).sendKeys(getElement(driver, locator), key).perform();
	}
	
	protected Object executeJSForElement (WebDriver driver, String javaScript, String locator) {
		return ((JavascriptExecutor) driver).executeScript(javaScript, getElement(driver, locator));
	}
	
	protected Object executeJSForBrowser(WebDriver driver, String executionScript) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(executionScript);
	}
	
	protected String getInnerText(WebDriver driver) {
		return (String) executeJSForBrowser(driver, "return document.documentElement.innerText");
	}
	
	protected boolean verifyInnerText(WebDriver driver, String expectedText) {
		 String actualInnerText = (String) executeJSForBrowser(driver, "return document.documentElement.innerText");
		 boolean result = false;
		 if (actualInnerText.contains(expectedText)) {
			 result = true;
		 }
		return result;		
	}
	
	protected void scrollToBottomOfPage(WebDriver driver) {
		executeJSForBrowser(driver, "window.scrollTo(0, document.body.scrollHeight)");
	}
	
	protected void scrollToElement(WebDriver driver, String locator) {
		executeJSForElement(driver, "arguments[0].scrollIntoView();", locator);
	}
	
	protected void highlightElement(WebDriver driver, String locator) {
		 ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", getElement(driver, locator), "style", "border: 2px solid red; border-style: dashed;");
	}
	
	protected void clickOnElementByJS(WebDriver driver, String locator) {
		executeJSForElement(driver, "arguments[0].click();", locator);
	}
	
	protected void sendKeyToElementByJS(WebDriver driver, String locator, String value) {
		executeJSForElement(driver, "arguments[0].setAttribute('value', '" + value + "')", locator);
	}
	
	protected void removeAttribute(WebDriver driver, String locator, String attributeToRemove) {
		executeJSForElement(driver, "arguments[0].removeAttribute('" + attributeToRemove + "');", locator);
	}
	
	protected String getElementValidationMessage(WebDriver driver, String locator) {
		return (String) executeJSForElement(driver,"return arguments[0].validationMessage;", locator);
	}

	protected void waitForElementVisible(WebDriver driver, String locator) {	
		new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
	}
	
	protected void waitForElementVisible(WebDriver driver, String locator, String... dynamicValue) {	
		new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(locator, dynamicValue))));
	}
	
	protected void waitForElementClickable(WebDriver driver, String locator) {	
		new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
	}
	
	protected void waitForElementClickable(WebDriver driver, String locator, String... dynamicValue) {	
		new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locator, dynamicValue))));
	}
	
	protected void waitForElementInvisible(WebDriver driver, String locator) {	
		new WebDriverWait(driver, 20).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
	}
	
	protected void waitForElementPresence(WebDriver driver, String locator) {	
		new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(getByLocator(locator)));
	}	
	
	public void openHeaderLink(WebDriver driver, String pageName) {
		waitForElementClickable(driver, BasePageUI.HEADER_LINK, pageName);
		clickOnElement(driver, BasePageUI.HEADER_LINK, pageName);
	}
	
	public void openMyAccountSideLink(WebDriver driver, String pageName) {
		waitForElementClickable(driver, BasePageUI.MY_ACCOUNT_SIDE_LINK, pageName);
		clickOnElement(driver, BasePageUI.MY_ACCOUNT_SIDE_LINK, pageName);
	}
	
	public CustomerInforPageObject openMyAccountPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.MY_ACCOUNT_LINK);
		clickOnElement(driver, BasePageUI.MY_ACCOUNT_LINK);
		return PageGenerator.getMyAccountPage(driver);
	}
	
	public HomePageObject logout(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.LOG_OUT_LINK);
		clickOnElement(driver, BasePageUI.LOG_OUT_LINK);
		return PageGenerator.getHomePage(driver);
	}
	
	public AddressPageObject openAddressPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.ADDRESS_PAGE_LINK);
		clickOnElement(driver, BasePageUI.ADDRESS_PAGE_LINK);
		return PageGenerator.getAddressPage(driver);
	}
	
	public OrdersPageObject openOrdersPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.ORDERS_PAGE_LINK);
		clickOnElement(driver, BasePageUI.ORDERS_PAGE_LINK);
		return PageGenerator.getOrdersPage(driver);
	}
	
	public DownloadPageObject openDownloadPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.DOWNLOAD_PAGE_LINK);
		clickOnElement(driver, BasePageUI.DOWNLOAD_PAGE_LINK);
		return PageGenerator.getDownloadPage(driver);
	}
	
	public BackInStockPageObject openBackInStockPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.BACK_IN_STOCK_PAGE_LINK);
		clickOnElement(driver, BasePageUI.BACK_IN_STOCK_PAGE_LINK);
		return PageGenerator.getBackInStockPage(driver);
	}
	
	public RewardPointsPageObject openRewardPointsPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.REWARD_POINT_PAGE_LINK);
		clickOnElement(driver, BasePageUI.REWARD_POINT_PAGE_LINK);
		return PageGenerator.getRewardPointsPage(driver);
	}
	
	public ChangePasswordPageObject openChangePasswordPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.CHANGE_PASSWORD_PAGE_LINK);
		clickOnElement(driver, BasePageUI.CHANGE_PASSWORD_PAGE_LINK);
		return PageGenerator.getChangePasswordPage(driver);
	}
	
	public MyProductReviewsPageObject openMyReviewsPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.MY_PRODUCT_REVIEW_PAGE_LINK);
		clickOnElement(driver, BasePageUI.MY_PRODUCT_REVIEW_PAGE_LINK);
		return PageGenerator.getMyProductReviewPage(driver);
	}

	
}

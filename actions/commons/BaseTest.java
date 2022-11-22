package commons;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseTest {
	//private String projectPath = System.getProperty("user.dir");
	private WebDriver driver;
	
	protected int getRandomNumber() {
		return new Random().nextInt(999);
	}
	
	protected WebDriver getBrowser(String browserName, String environment) {
		switch (browserName) {
		case "firefox": 
			WebDriverManager.firefoxdriver().setup();
			//System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
			driver = new FirefoxDriver();			
			break;
		case "chrome": 
			WebDriverManager.chromedriver().setup();
			//System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			driver = new ChromeDriver();			
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			//System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
			driver = new EdgeDriver();			
			break;
		case "h_chrome":
			WebDriverManager.chromedriver().setup();
			//System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
			options.addArguments("window-size=1920*1080");
			driver = new ChromeDriver(options);
			break;
		case "h_firefox":
			WebDriverManager.firefoxdriver().setup();
			//System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.addArguments("--headless");
			firefoxOptions.addArguments("window-size=1920*1080");
			driver = new FirefoxDriver(firefoxOptions);
			break;
		default:
			throw new RuntimeException("Please correct browser name!");
		}
		
		driver.manage().window().maximize();
		driver.get(getEnvironmentURL(environment));
		return driver;
	}

	protected String getEnvironmentURL(String environment) {
		String url = null;
		switch (environment) {
		case "user":
			url = GlobalVariables.USER_PAGE_URL;
			break;
		case "admin":
			url = GlobalVariables.ADMIN_PAGE_URL;
			break;
		default:
			throw new RuntimeException("Environment is not supported!");
		}
		return url;
	}
}

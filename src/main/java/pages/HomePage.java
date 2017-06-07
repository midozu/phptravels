package pages;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import interfaces.HomePageUI;

public class HomePage extends AbstractPage {
	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	public SignUpPage openSignUpPage(WebDriver driver) throws InterruptedException {
		clickToElement(driver, HomePageUI.MY_ACCOUNT_MENU);
		Thread.sleep(1000);
		clickToElement(driver, HomePageUI.SIGN_UP_MENU);
		return PageFactory.getSignUpPage(driver);
	}

	public LogInPage openLogInPage(WebDriver driver) throws InterruptedException {
		clickToElement(driver, HomePageUI.MY_ACCOUNT_MENU);
		Thread.sleep(1000);
		clickToElement(driver, HomePageUI.LOGIN_MENU);
		return PageFactory.getLoginPage(driver);
	}

}

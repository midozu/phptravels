package pages;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import interfaces.LogInPageUI;
import interfaces.SignUpPageUI;

public class LogInPage extends AbstractPage {
	WebDriver driver;

	public LogInPage(WebDriver driver) {
		this.driver = driver;
	}

	public void submitLogin() {
		clickToElement(driver, LogInPageUI.LOGIN_BUTTON);
	}

	public String getErrorMessage() {
		return getTextOfElement(driver, LogInPageUI.MESSAGE);
	}

	public void enterValueToField(String locator, String value) {
		typeToElement(driver, LogInPageUI.DYNAMIC_FIELD, locator, value);
	}

	public boolean isCurrentURL() {
		try {
			return getURL(driver).contains("http://www.phptravels.net/account/");
		} catch (Exception e) {
			return false;
		}
	}

	public String getWelcomeMessage() {
		return getTextOfElement(driver, SignUpPageUI.WELCOME_TXT);
	}

}

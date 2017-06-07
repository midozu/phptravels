package pages;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import interfaces.SignUpPageUI;

public class SignUpPage extends AbstractPage {
	WebDriver driver;

	public SignUpPage(WebDriver driver) {
		this.driver = driver;
	}

	public void submitSignUp() {
		clickToElement(driver, SignUpPageUI.SIGNUP_BUTTON);
	}

	public void enterValueToField(String locator, String value) {
		typeToElement(driver, SignUpPageUI.DYNAMIC_FIELD, locator, value);
	}

	// get text

	public String getErrorMesssage() {
		return getTextOfElement(driver, SignUpPageUI.MESSAGE);
	}

	public String getErrorMessageField() {
		return getErrorMesssage();
	}

	public boolean verifyErrorMesssageEmtpyFields() {
		try {
			return getErrorMesssage().contains(
					"The Email field is required.\nThe Password field is required.\nThe Password field is required.\nThe First name field is required.\nThe Last Name field is required.");
		} catch (Exception e) {
			return false;
		}
	}

	public String getWelcomeMessage() {
		return getTextOfElement(driver, SignUpPageUI.WELCOME_TXT);
	}

	public void logout() {
		clickToElement(driver, SignUpPageUI.MY_ACCOUNT_MENU);
		clickToElement(driver, SignUpPageUI.SIGNOUT_BUTTON);
		navigateToURL(driver, "http://www.phptravels.net/register");
	}

	public void createAccount(String username, String passw) {
		typeToElement(driver, SignUpPageUI.DYNAMIC_FIELD, "Email", username);
		typeToElement(driver, SignUpPageUI.DYNAMIC_FIELD, "Password", passw);
		typeToElement(driver, SignUpPageUI.DYNAMIC_FIELD, "Confirm Password", "123123");
		typeToElement(driver, SignUpPageUI.DYNAMIC_FIELD, "First Name", "Truong");
		typeToElement(driver, SignUpPageUI.DYNAMIC_FIELD, "Last Name", "Thanh");
		clickToElement(driver, SignUpPageUI.SIGNUP_BUTTON);
	}

	public MyAccountPage openMyAccountPage(WebDriver driver) {
		return PageFactory.getMyAccountPage(driver);
	}

}

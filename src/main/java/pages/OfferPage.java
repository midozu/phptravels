package pages;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import interfaces.OfferPageUI;

public class OfferPage extends AbstractPage {
	WebDriver driver;

	public OfferPage(WebDriver driver) {
		this.driver = driver;
	}

	public void selectOffer(String value) {
		clickToElement(driver, OfferPageUI.DYNAMIC_OFFER_ITEM, value);
	}

	public void enterValueToField(String locator, String value) {
		typeToElement(driver, OfferPageUI.DYNAMIC_FIELD, locator, value);
	}

	public void enterValueToFieldMsg(String locator, String value) {
		typeToElement(driver, OfferPageUI.DYNAMIC_FIELD2, locator, value);
	}

	public void submitContact() {
		clickToElement(driver, OfferPageUI.CONTACT_BUTTON);
	}

	public String getMessageSubmit() {
		return getTextOfElement(driver, OfferPageUI.MESSAGE_SUBMIT);
	}
}

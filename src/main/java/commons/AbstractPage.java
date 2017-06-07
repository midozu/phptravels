package commons;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import interfaces.AbstractPageUI;
import pages.HomePage;
import pages.PageFactory;

/**
 * @author ThanhTruong
 * @para browser, url, run on local, link from testng.xml
 *
 */
public class AbstractPage {
	WebDriver driver;
	WebElement element;
	LogEvent LOG = new LogEvent();
	int timeout = 30;

	// common functions
	public void typeToElement(WebDriver driver, String controlName, String value) {
		waitForDynamicControlVisible(driver, controlName, value, timeout);
		element = driver.findElement(By.xpath(controlName));
		element.clear();
		element.sendKeys(value);
	}

	public void typeToElement(WebDriver driver, String controlName, String locator, String value) {
		String newLocator = String.format(controlName, locator);
		waitForControlVisible(driver, newLocator, timeout);
		element = driver.findElement(By.xpath(newLocator));
		element.clear();
		element.sendKeys(value);
	}

	public void clearTextOfElement(WebDriver driver, String controlName) {
		waitForControlVisible(driver, controlName, timeout);
		element = driver.findElement(By.xpath(controlName));
		element.clear();
	}

	public void pressTABkey(WebDriver driver) {
		element.sendKeys(Keys.TAB);
	}

	public void clickToElement(WebDriver driver, String controlName) {
		waitForControlVisible(driver, controlName, timeout);
		element = driver.findElement(By.xpath(controlName));
		element.click();
	}

	public void clickToElement(WebDriver driver, String controlName, String value) {
		String newControl = String.format(controlName, value);
		waitForControlVisible(driver, newControl, timeout);
		element = driver.findElement(By.xpath(newControl));
		element.click();
	}

	public void selectDropdownlist(WebDriver driver, String controlName, String value) {
		waitForDynamicControlVisible(driver, controlName, value, timeout);
		Select select = new Select(driver.findElement(By.xpath(controlName)));
		select.selectByVisibleText(value);
	}

	public void acceptAlert(WebDriver driver) {
		waitForAlert(driver, timeout);
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	public void navigateToURL(WebDriver driver, String url) {
		driver.navigate().to(url);
	}

	public String getTextAlert(WebDriver driver) {
		waitForAlert(driver, timeout);
		Alert alert = driver.switchTo().alert();
		return alert.getText();
	}

	public void waitForDynamicControlVisible(WebDriver driver, String locator, String value, int timeout) {
		String newLocator = String.format(locator, value);
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(newLocator)));
		} catch (Exception e) {
			LOG.error("Not Found Element: " + newLocator);
			LOG.error(e.getMessage());
		}
	}

	public void waitForControlVisible(WebDriver driver, String controlName, int timeout) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(controlName)));
		} catch (Exception e) {
			LOG.error("Not Found Element: " + controlName);
			LOG.error(e.getMessage());
		}
	}

	public void waitForAlert(WebDriver driver, int timeout) {
		try {
			new WebDriverWait(driver, timeout).ignoring(NoAlertPresentException.class)
					.until(ExpectedConditions.alertIsPresent());
		} catch (Exception e) {
			LOG.error("Not Found Alert");
			e.printStackTrace();
		}
	}

	public void verifyElementDisplayed(WebDriver driver, String controlName, String value) {
		String newLocator = String.format(controlName, value);
		WebElement element = driver.findElement(By.xpath(newLocator));
		element.isDisplayed();
	}

	public String getURL(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	// get text
	public String getTextOfElement(WebDriver driver, String controlName) {
		waitForControlVisible(driver, controlName, timeout);
		element = driver.findElement(By.xpath(controlName));
		return element.getText();
	}

	public String getTextOfElement(WebDriver driver, String controlName, String value) {
		String newLocator = String.format(controlName, value);
		waitForControlVisible(driver, newLocator, timeout);
		element = driver.findElement(By.xpath(newLocator));
		return element.getText();
	}

	public void scrollPageToElement(WebDriver driver, String controlName) {
		waitForControlVisible(driver, controlName, timeout);
		element = driver.findElement(By.xpath(controlName));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
	}

	public void scrollPageToElement(WebDriver driver, String controlName, String value) {
		String newControl = String.format(controlName, value);
		waitForControlVisible(driver, newControl, timeout);
		element = driver.findElement(By.xpath(newControl));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
	}

	// open each page
	public HomePage openHomePage(WebDriver driver) {
		clickToElement(driver, AbstractPageUI.HOME_PAGE);
		return PageFactory.getHomePage(driver);
	}
}

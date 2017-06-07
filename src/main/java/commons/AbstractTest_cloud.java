package commons;

import java.net.URL;

import org.apache.commons.lang3.RandomStringUtils;
import org.automationtesting.excelreport.Xl;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * @author ThanhTruong for running on cloud - browserstack
 * @para browser, url, link from testng_cloud.xml
 */
public class AbstractTest_cloud {
	// using browserstack
	WebDriver driver;

	public static final String URL = "https://" + Constants.USERNAME + ":" + Constants.AUTOMATE_KEY
			+ "@hub-cloud.browserstack.com/wd/hub";

	public WebDriver openBrowser(String browser, String url) throws Exception {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("browser", browser);
		caps.setCapability("browser_version", "47.0");
		caps.setCapability("os", "Windows");
		caps.setCapability("os_version", "7");
		caps.setCapability("browserstack.debug", "true");
		caps.setCapability("project", "net.phptravels");

		WebDriver driver = new RemoteWebDriver(new URL(URL), caps);
		driver.get(url);
		return driver;
	}

	public void closeBrowser(WebDriver driver) throws Exception {
		Xl.generateReport(Constants.EXCEL_PATH, Constants.EXCEL_NAME);
		try {
			driver.quit();
			System.gc();
			if (driver.toString().toLowerCase().contains("chrome")) {
				String cmd = "taskkill /F /IM chromedriver.exe";
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			}
			if (driver.toString().toLowerCase().contains("internetexplorer")) {
				String cmd = "taskkill /F /IM IEDriverServer.exe";
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void refreshBrowser() {
		driver.navigate().refresh();
	}

	public static String randomString(int number) {
		return RandomStringUtils.randomAlphabetic(number);
	}
}

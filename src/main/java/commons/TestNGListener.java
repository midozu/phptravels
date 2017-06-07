package commons;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListener implements ITestListener {
	LogEvent LOG = new LogEvent();

	public void onFinish(ITestContext arg0) {

	}

	public void onStart(ITestContext arg0) {

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public void onTestFailure(ITestResult result) {
		LOG.info("====> FAILED");
		LOG.endTestCase();
	}

	public void onTestSkipped(ITestResult result) {
		LOG.info("====> SKIPPED");
		LOG.endTestCase();
	}

	public void onTestStart(ITestResult result) {
		LOG.startTestCase(result.getName());
	}

	public void onTestSuccess(ITestResult result) {
		LOG.info("====> PASSED");
		LOG.endTestCase();
	}
}

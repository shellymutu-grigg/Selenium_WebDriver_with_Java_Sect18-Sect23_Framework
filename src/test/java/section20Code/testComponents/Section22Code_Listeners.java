package section20Code.testComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import section20Code.resources.Section22Code_ExtentReporterNG;

public class Section22Code_Listeners extends Section20Code_BaseTest implements ITestListener {

	// ExtentReports object initialisation
	ExtentReports extentReports = Section22Code_ExtentReporterNG.getReportObject();

	// Test Object
	ExtentTest extentTest;

	// Thread safe option
	ThreadLocal<ExtentTest> extentThreadTest = new ThreadLocal();

	@Override
	public void onTestStart(ITestResult result) {

		// Generate the test object
		extentTest = extentReports.createTest(result.getMethod().getMethodName());

		// Assign unique test ID
		extentThreadTest.set(extentTest);
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		// Log the successful result
		extentTest.log(Status.PASS, "Test sucessful");
	}

	@Override
	public void onTestFailure(ITestResult result) {

		String filePath = null;

		// Fail the test
		extentThreadTest.get().fail(result.getThrowable());

		// Obtain webDriver details
		try {
			webDriver = (WebDriver) result.getTestClass().getRealClass().getField("webDriver")
					.get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Take screenshot
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(), webDriver);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Attach screenshot to report
		extentThreadTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());

		// Log test failure
		extentTest.log(Status.FAIL, "Test Failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// not implemented
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// not implemented
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		onTestFailure(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// not implemented
	}

	@Override
	public void onFinish(ITestContext context) {

		// Terminate extent report object
		extentReports.flush();
	}
}

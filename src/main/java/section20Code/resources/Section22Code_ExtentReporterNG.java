package section20Code.resources;

import java.text.MessageFormat;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Section22Code_ExtentReporterNG {

	public static ExtentReports getReportObject() {
		// ExtentSparkReporter
		String pathnameString = System.getProperty("user.dir") + "//reports//index.html";
		System.out.println(
				MessageFormat.format("Report director: {0}", System.getProperty("user.dir") + "/reports/index.html"));
		ExtentSparkReporter reporter = new ExtentSparkReporter(pathnameString);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");

		// ExtentReports
		ExtentReports extentReports = new ExtentReports();
		extentReports.attachReporter(reporter);
		extentReports.setSystemInfo("Tester", "Shelly Mutu-Grigg");
		return extentReports;
	}
}

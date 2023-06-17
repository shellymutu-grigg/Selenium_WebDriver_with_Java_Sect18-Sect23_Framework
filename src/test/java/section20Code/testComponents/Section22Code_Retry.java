package section20Code.testComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Section22Code_Retry implements IRetryAnalyzer {

	// Global variables
	int count = 0;
	int maxTries = 1;

	@Override
	public boolean retry(ITestResult result) {
		if (count < maxTries) {
			count++;
			// Re-run a test in the event of a failure
			return true;
		}
		return false;
	}

}

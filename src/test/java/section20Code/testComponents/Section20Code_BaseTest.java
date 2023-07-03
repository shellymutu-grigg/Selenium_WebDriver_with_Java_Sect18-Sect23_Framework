package section20Code.testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import io.github.bonigarcia.wdm.WebDriverManager;
import section19Code.pageObjects.Section19Code_LandingPage;

public class Section20Code_BaseTest {

	public WebDriver webDriver;
	public Section19Code_LandingPage landingPage;

	public WebDriver initializeDriver() throws IOException {

		// Properties Class
		Properties properties = new Properties();
		FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")
				+ "//src//main//java//section20Code//resources//Section20Code_GlobalData.properties");
		properties.load(fileInputStream);

		// If the value is sent via Maven commands use that value otherwise use the data
		// in the GlobalData.properties file
		String browserNameString = System.getProperty("browser") != null ? System.getProperty("browser")
				: properties.getProperty("browser");

		if (browserNameString.contains("chrome")) {
			ChromeOptions chromeOptions = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if (browserNameString.contains("headless")) {
				chromeOptions.addArguments("headless");
			}
			webDriver = new ChromeDriver(chromeOptions);

			// Even when running in headless mode ensure screen is maximised to view all
			// elements
			webDriver.manage().window().setSize(new Dimension(1440, 900));

		} else if (browserNameString.equalsIgnoreCase("firefox")) {
			webDriver = new FirefoxDriver();
		} else if (browserNameString.equalsIgnoreCase("edge")) {
			webDriver = new EdgeDriver();
		}

		webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		webDriver.manage().window().maximize();
		return webDriver;

	}

	public List<HashMap<String, String>> getJsonDataToMap(String filePathString) throws IOException {

		// Read the JSON file
		String jsonContentString = FileUtils.readFileToString(new File(filePathString), StandardCharsets.UTF_8);

		// Convert String to HashMap
		ObjectMapper mapper = new ObjectMapper();
		// List<Hashmap<String, String>> dataHashMap =
		// mapper.readValue(jsonContentString,
		// new TypeReference<List<Hashmap<String, String>>>() {
		// });

		List<HashMap<String, String>> dataHashMap = new Gson().fromJson(jsonContentString,
				new TypeToken<List<HashMap<String, String>>>() {
				}.getType());

		return dataHashMap;
	}

	public String getScreenshot(String testCaseName, WebDriver webDriver) throws IOException {

		TakesScreenshot screenshotAction = (TakesScreenshot) webDriver;
		File screenshotTakenFile = screenshotAction.getScreenshotAs(OutputType.FILE);
		File screenshOutputFile = new File(System.getProperty("user.dir") + "//Desktop//" + testCaseName + ".png");
		FileUtils.copyFile(screenshotTakenFile, screenshOutputFile);
		return System.getProperty("user.dir") + "//Desktop//" + testCaseName + ".png";

	}

	@BeforeMethod(alwaysRun = true)
	protected Section19Code_LandingPage launchApplication() throws IOException {
		webDriver = initializeDriver();
		landingPage = new Section19Code_LandingPage(webDriver);
		landingPage.navigate();
		return landingPage;

	}

	@AfterMethod(alwaysRun = true)
	public void teardown() {
		webDriver.close();
	}

}

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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
		String broswerNameString = properties.getProperty("browser");

		if (broswerNameString.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			webDriver = new ChromeDriver();
		} else if (broswerNameString.equalsIgnoreCase("firefox")) {
			webDriver = new FirefoxDriver();
		} else if (broswerNameString.equalsIgnoreCase("edge")) {
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

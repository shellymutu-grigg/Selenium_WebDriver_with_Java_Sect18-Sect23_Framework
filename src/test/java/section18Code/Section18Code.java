package section18Code;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Section18Code {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver webDriver = new ChromeDriver();
		webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// Global variables
		String productNameString = "ZARA COAT 3";

		// Navigate to page
		webDriver.get("https://rahulshettyacademy.com/client/");
		webDriver.manage().window().maximize();

		// Login to site
		webDriver.findElement(By.id("userEmail")).sendKeys("shellymutugrigg@gmail.com");
		webDriver.findElement(By.id("userPassword")).sendKeys("gazxHSwK$oBbd*c43t4S");
		webDriver.findElement(By.id("login")).click();

		WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

		// Generate list of products on page
		List<WebElement> products = webDriver.findElements(By.cssSelector(".mb-3"));

		WebElement finalProduct = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productNameString))
				.findFirst().orElse(null);

		finalProduct.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		// Wait for spinner to appear
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

		// Wait for spinner to disappear
		wait.until(ExpectedConditions.invisibilityOf(webDriver.findElement(By.cssSelector(".ng-animating"))));

		Thread.sleep(2000);

		// Navigate to cart
		webDriver.findElement(By.cssSelector("[routerlink*='cart']")).click();

		List<WebElement> cartProducts = webDriver.findElements(By.cssSelector(".cartSection h3"));

		// See if any element matches
		boolean match = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productNameString));
		Assert.assertTrue(match);

		webDriver.findElement(By.cssSelector(".totalRow button")).click();

		Actions action = new Actions(webDriver);
		action.sendKeys(webDriver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build()
				.perform();

		// Wait for pop-up to display
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

		// Select second item
		webDriver.findElement(By.cssSelector(".ta-item:nth-of-type(1)")).click();

		// Place order
		webDriver.findElement(By.cssSelector(".action__submit")).click();

		String confirmMessageString = webDriver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessageString.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

		webDriver.close();

	}

}

package section19Code.abstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import section19Code.pageObjects.Section19Code_CartPage;

public class Section19Code_AbstractComponents {

	WebDriver webDriver;

	@FindBy(css = "[routerlink*='cart']")
	WebElement cartHeader;

	public Section19Code_AbstractComponents(WebDriver webDriver) {
		this.webDriver = webDriver;
		PageFactory.initElements(webDriver, this);
	}

	public void waitForElementToAppear(By findBy) {

		WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}

	protected void waitForElementToDisappear(WebElement spinner) throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(spinner));

		// Add in additional delay
		Thread.sleep(2000);
	}

	public Section19Code_CartPage goToCartPage() {
		cartHeader.click();
		Section19Code_CartPage cartPage = new Section19Code_CartPage(webDriver);
		return cartPage;
	}

}

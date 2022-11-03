package learningAutomation.pageElements;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObject.CartPage;
import pageObject.CheckoutPage;
import pageObject.ConfirmationPage;
import pageObject.LandingPage;
import pageObject.ProductCatalogue;

public class ErrorValidationTest extends BaseTest {
	
	@Test
	public void submitOrder() throws IOException, InterruptedException {
		
// To validate Login page validation
		landingPage.loginApplication("shivamyadav6797@gmail.com","54321Ss#");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMsg());
	}
}

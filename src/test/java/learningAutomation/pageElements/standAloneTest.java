package learningAutomation.pageElements;

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

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObject.LandingPage;

public class standAloneTest {

	public static void main(String[] args) {
		//Chrome driver will be automatically downloaded in our system
		//based on version
		String productName="ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("shivamyadav6797@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("54321Ss#");
		driver.findElement(By.id("login")).click();
		//Explicit wait so that page items loaded fully
		WebDriverWait  wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
		//Verify your productName 
		WebElement prod=products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		//Click add to Cart
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		//Explicit wait after clicking Add to Cart to get appear toast msg
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		//Explicit wait to get disappear toast msg
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		//Click on Cart button
		driver.findElement(By.cssSelector("[routerlink='/dashboard/cart']")).click();
		// getting cartProducts
		List<WebElement> cartProducts=driver.findElements(By.cssSelector(".cartSection h3"));
		//Our selected item is in the cart or not
		Boolean match=cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		//Click on Checkout button
		driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
		// Country selection from suggestive dropdown
		Actions a=new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")),"india").build().perform();
		//Explicit Wait untill all options get visible
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		//Click on India
		driver.findElement(By.cssSelector("button[class*='ta-item']:last-of-type")).click();
		//Placing order
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='btnn action__submit ng-star-inserted']")));
		driver.findElement(By.xpath("//a[@class='btnn action__submit ng-star-inserted']")).click()	;
		// receiving confirmation text
		String confirmationMsg=driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmationMsg.equalsIgnoreCase("THANK YOU FOR THE ORDER"));
		//driver.quit();
		
		
	}

}

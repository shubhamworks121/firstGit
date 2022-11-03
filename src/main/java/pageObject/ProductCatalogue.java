package pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponent.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
    WebDriver driver;
	public ProductCatalogue(WebDriver driver)
	{
		super(driver);
		//initialization
		this.driver=driver;
		//By doing this all the method will know about driver
		PageFactory.initElements(driver, this);
	} 
	
	//List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
	@FindBy(css=".mb-3")
	List<WebElement> products;
	// Toast msg to be disappear
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	// Explict wait on basis of this items to be visible
	By productsBy= By.cssSelector(".mb-3");
	By addToCart=By.cssSelector(".card-body button:last-of-type");
	By toastMsg=By.cssSelector("#toast-container");
	
	public List<WebElement> getProductsList(){
		waitForElementToAppear(productsBy);
		return products;
	}
	public WebElement getProductByName(String productName)
	{
		WebElement prod=getProductsList().stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName) throws InterruptedException
	{
		WebElement prod=getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMsg);
		waitForElementToDisappear(spinner);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

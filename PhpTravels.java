package phptravels;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PhpTravels {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		//Using Edge Browser
		WebDriver driver = new ChromeDriver();  

		//Navigating the URL
		driver.navigate().to("https://phptravels.com/demo/");

		//Maximizing the window 
		driver.manage().window().maximize(); 
		
		//Using pageLoadTimeout 
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

		//Using ImplicitlyWait for page to wait for click
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		//Locators are used for filling the form
		driver.findElement(By.name("first_name")).sendKeys("Dharaneesh kumar");
		driver.findElement(By.name("last_name")).sendKeys("Dhamotharan");
		driver.findElement(By.name("business_name")).sendKeys("Travels");
		WebElement mail =driver.findElement(By.xpath(("//input[@placeholder='Email']")));
		mail.sendKeys("sddharaneeshkumar@gmail.com");

		//Adding the numbers
		String number1 = driver.findElement(By.xpath("//span[@id='numb1']")).getText();
		String number2 = driver.findElement(By.xpath("//span[@id='numb2']")).getText();

		//String to Integer Conversion
		int result = Integer.parseInt(number1) + Integer.parseInt(number2);

		//Sending the result
		driver.findElement(By.xpath("//input[@id='number']")).sendKeys(String.valueOf(result));

		//Clicking Submit button
		driver.findElement(By.xpath("//button[text()='Submit']")).click();
		
		//Using WebDriverWait
		WebElement thanksMessage = driver.findElement(By.xpath("//strong[text()=' Thank you!']"));
		WebElement successMessage = driver.findElement(By.xpath("//p[@class='text-center cw']"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		wait.until(ExpectedConditions.visibilityOf(thanksMessage));
		wait.until(ExpectedConditions.visibilityOf(successMessage));

		String thanks = driver.findElement(By.xpath("//strong[text()=' Thank you!']")).getText();
		String success = driver.findElement(By.xpath("//p[@class='text-center cw']")).getText();

		
		//Validating the page
		String verify = driver.findElement(By.xpath(" //h2[contains(text(),'Demo Request Form')]")).getText(); 
		if(verify.contains("Demo"))
		{                                  
			System.out.println("Form submitted ");
			System.out.println(verify);
		} 
		else 
		{
			System.out.println("Form not submitted");
		}
								
		//Taking Screenshot
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		Thread.sleep(5000);
		
		File source = screenshot.getScreenshotAs(OutputType.FILE);
		File destination = new File("D:\\dharaneesh\\FINAL YEAR PROJECT\\guvi\\Task 22\\Phptravel.png");   
		
		try {
			FileUtils.copyFile(source, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Closing the driver
		driver.close();  
	}

}
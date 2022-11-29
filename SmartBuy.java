import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SmartBuy {

	public WebDriver driver;
	public int NumberofTry = 3;
	public int NoOfItemsInInventory = 4;
	SoftAssert softassertProcess = new SoftAssert();

	@BeforeTest()

	public void this_is_before_test() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://smartbuy-me.com/smartbuystore/");
		driver.findElement(By.xpath("/html/body/main/header/div[2]/div/div[2]/a")).click();
		driver.manage().window().maximize();
	}

//	@Test()
//
//	public void Samsung_50_inch() {
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

//		for (int i = 0; i < NumberofTry; i++) {
//
//			if (i < NoOfItemsInInventory) {
//				driver.findElement(By.xpath(
//						"//*[@id=\"newtab-Featured\"]/div/div[1]/div/div/div/div[3]/div/div[3]/div[1]/div/div/form[1]/div[1]/button"))
//						.click();
//				driver.findElement(By.xpath("//*[@id=\"addToCartLayer\"]/a[2]")).click();
//			} else {
//				break;
//			}
//
//		}
//		driver.findElement(
//				By.xpath("/html/body/main/header/div[4]/div/nav/div/div[3]/div/ul/li[1]/div/div/div[1]/a/div[1]/span"))
//				.click();
//
//		String totalprice = driver.findElement(By.xpath("//*[@id=\"cboxLoadedContent\"]/div/div/div[2]/div[1]/h4[2]"))
//				.getText();
//
//		String the_single_item_price = driver
//				.findElement(By.xpath(
//						"//*[@id=\"newtab-Featured\"]/div/div[1]/div/div/div/div[3]/div/div[2]/div[2]/div/div/span[3]"))
//				.getText();
//
//		String[] the_updated_single_item_price = the_single_item_price.split("JOD");
//
//		String the_final_single_item_price_in_string = the_updated_single_item_price[0].trim();
//
//		String updated = the_final_single_item_price_in_string.replace(",", ".");
//
//		Double final_price = Double.parseDouble(updated);
//
//		String[] the_updated_total_price = totalprice.split("JOD");
//		String Updated_Total_Price = the_updated_total_price[0].trim();
//
//		String Updated_Total_Price_Remove_Dot = Updated_Total_Price.substring(0, Updated_Total_Price.lastIndexOf("."));
//
//		String updated_total_price_to_string = Updated_Total_Price_Remove_Dot.trim().replace(",", ".");
//
//		Double final_total_price = Double.parseDouble(updated_total_price_to_string);
//
//		String title = driver.getTitle();
//
//		softassertProcess.assertEquals(title, "Smart Buy | Homepage For Web");
//
//		softassertProcess.assertEquals(final_price * NumberofTry, final_total_price);
//
//		softassertProcess.assertAll();
	// }

	@Test()

	public void Discount_Percentage_Check_Correct() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// old price before discount
		WebElement oldPrice = driver.findElement(By
				.xpath("//*[@id=\"newtab-Featured\"]/div/div[1]/div/div/div/div[2]/div/div[2]/div[2]/div/div/span[2]"));

		// discount percentage
		WebElement discount = driver.findElement(By
				.xpath("//*[@id=\"newtab-Featured\"]/div/div[1]/div/div/div/div[2]/div/div[2]/div[2]/div/div/span[1]"));

		// new price after discount
		WebElement newPrice = driver.findElement(By
				.xpath("//*[@id=\"newtab-Featured\"]/div/div[1]/div/div/div/div[2]/div/div[2]/div[2]/div/div/span[3]"));

		//

		String[] the_updated_old_price = oldPrice.getText().split("JOD");
		String Updated_old_Price = the_updated_old_price[0].trim();
		Double final_old_price = Double.parseDouble(Updated_old_Price);

		//
		String[] the_updated_discount = discount.getText().split("%");
		String Updated_discount = the_updated_discount[0].trim();
		Double final_discount = Double.parseDouble(Updated_discount);

		//
		String[] the_updated_new_price = newPrice.getText().split("JOD");
		String Updated_new_Price = the_updated_new_price[0].trim();
		Double final_new_price = Double.parseDouble(Updated_new_Price);

		Double discount_percentage_amount = (final_old_price * final_discount) / 100;

		Double final_new_price_after_discount = final_old_price - Math.round(discount_percentage_amount);

		softassertProcess.assertEquals(final_new_price_after_discount, final_new_price);

		softassertProcess.assertAll();

	}



}

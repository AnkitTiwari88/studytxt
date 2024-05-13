package WorldClock;

import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverSetUp {
	// Variable of type WebDriver
		public static WebDriver driver;
		
		// Method to Handle the Driver of different types
		public static WebDriver getWebDriver(String url) {
			System.out.println("Choose the browser below");
			System.out.println("1.Chrome");
			System.out.println("2.Edge");
			System.out.println("Enter the option number: ");
			  Scanner sc=new Scanner(System.in);
			  byte b=sc.nextByte();
			  if(b==1) {
				// Creating the Object of the ChromeDriver
				  driver=new ChromeDriver();
				  driver.get(url);
			  }else if(b==2) {
				// Creating the Object of the EdgeDriver
				  driver=new EdgeDriver();
				  driver.get(url);
			  }
			  sc.close();
			// Returning the Driver
			return driver;
		}
}

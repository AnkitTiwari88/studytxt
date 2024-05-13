package WorldClock;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Clock {
	public WebDriver driver;
	public WebDriverWait wait;
	Clock (WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
		// ***** User Info ******
		public void clickOnUserIcon() throws InterruptedException {
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		// Selecting xpath of the div tag containing img tag 
		WebElement user= wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("O365_MainLink_MePhoto"))));
		// Waiting Until The Setting Icon is visible
	    wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//*[@id='O365_MainLink_Settings']/div"))));
	    Thread.sleep(3000);
		user.click();
		}
		
		
//		@FindBy(xpath="//*[@id='mectrl_headerPicture']")
		@FindBy(id ="mectrl_headerPicture")
		WebElement clickback;
		public void clickBackUser() {
			clickback.click();
		}
		
		
		@FindBy(xpath="//*[@id='mectrl_currentAccount_primary']")
		WebElement username;
		public String getUserName() {
			return username.getText();
		}
		
		
		@FindBy(xpath="//*[@id='mectrl_currentAccount_secondary']")
		WebElement userId;
		public String getUserId() {
			return userId.getText();
		}
		
		// ******* World Clock *******
		
		
		// Validating the World Clock
		@FindBy(xpath="//*[@id='CaptionElementView' and contains(text(),'World Clock')]")
		WebElement Title_clock;
		public String checkClockBox(){
			String present=Title_clock.getText();
			return present;
		}
		
		//===================== Bangalore, India (IST) =============================
		
		@FindBy(xpath="(//*[@data-automation-id=\"clock-card-location\"])[1]") 
		WebElement indiaLocation;
		@FindBy(xpath="//*[@id=\"vpc_WebPart.WorldClockWebPart.internal.60655e4a-73c8-49d0-9571-c762791557af\"]/div/div/div[2]/div/div/div/div/div/div/div[1]/div/div/div/div[2]/div[1]")
		WebElement indiaTime;
		@FindBy(xpath="//*[@id=\"vpc_WebPart.WorldClockWebPart.internal.60655e4a-73c8-49d0-9571-c762791557af\"]/div/div/div[2]/div/div/div/div/div/div/div[1]/div/div/div/div[2]/div[2]/div[2]")
		WebElement India_Day_date;
		public String checkIndiaClock() {
			String IndiaTitle=indiaLocation.getText();	
			return IndiaTitle;
		}
		public String checkIndiaTime() { 
			return indiaTime.getText();
		}
		public String chechIndiaDate() {
			return India_Day_date.getText();
		}
		
		//========================================== London =================================== 
		
		@FindBy(xpath="(//*[@data-automation-id='clock-card-location'])[2]")
		WebElement londonLocation;

		@FindBy(xpath="//*[@id=\"vpc_WebPart.WorldClockWebPart.internal.60655e4a-73c8-49d0-9571-c762791557af\"]/div/div/div[2]/div/div/div/div/div/div/div[2]/div/div/div/div[2]/div[1]/span[1]")
		WebElement LondonTime;
		

		@FindBy(xpath="//*[@id=\"vpc_WebPart.WorldClockWebPart.internal.60655e4a-73c8-49d0-9571-c762791557af\"]/div/div/div[2]/div/div/div/div/div/div/div[2]/div/div/div/div[2]/div[2]/div[2]")
		WebElement lon_date;

		@FindBy(xpath="//*[@id=\"vpc_WebPart.WorldClockWebPart.internal.60655e4a-73c8-49d0-9571-c762791557af\"]/div/div/div[2]/div/div/div/div/div/div/div[2]/div/div/div/div[2]/div[2]/div[1]")
		WebElement London_India_Gap;
		public String checkLondonClock() {
			String lontitle=londonLocation.getText();
			return lontitle;
		}
		public String checkLondonTime() {
			String lontime = LondonTime.getText();
			return lontime;
		}
		public String checkLondonDate() {
			String londate = lon_date.getText();
			return londate;
		}
		public String checkLondonGapTime() {
			String longap=London_India_Gap.getText();
			return longap;
		
		}	
		
		//============================================= NewYork =====================================
		
		@FindBy(xpath="(//*[@data-automation-id='clock-card-location'])[3]")
		WebElement NewYorkLocation;

		@FindBy(xpath="//*[@id=\"vpc_WebPart.WorldClockWebPart.internal.60655e4a-73c8-49d0-9571-c762791557af\"]/div/div/div[2]/div/div/div/div/div/div/div[3]/div/div/div/div[2]/div[1]/span[1]")
		WebElement NewYorkTime;

		@FindBy(xpath="//*[@id=\"vpc_WebPart.WorldClockWebPart.internal.60655e4a-73c8-49d0-9571-c762791557af\"]/div/div/div[2]/div/div/div/div/div/div/div[3]/div/div/div/div[2]/div[2]/div[2]") 
		WebElement ny_date;

		@FindBy(xpath="//*[@id=\"vpc_WebPart.WorldClockWebPart.internal.60655e4a-73c8-49d0-9571-c762791557af\"]/div/div/div[2]/div/div/div/div/div/div/div[3]/div/div/div/div[2]/div[2]/div[1]")
		WebElement NewYorkIndiaGap;
		public String newYorkClock() {
			String nytitle=NewYorkLocation.getText();
			return nytitle;
		}
		public String checkNewYorkTime() {
			String nytime = NewYorkTime.getText();
			return nytime;
		}
		public String checkNewYorkDate() {
			String nydate = ny_date.getText();
			return nydate;
		}
		public String checkNewYorkIndiaGapTime() {
			String nygap=NewYorkIndiaGap.getText();
			return nygap;
		}
}

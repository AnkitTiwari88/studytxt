package WorldClock;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import WorldClock.Clock;
import WorldClock.DriverSetUp;
import WorldClock.ExcelUtils;
import WorldClock.OneCognizantApps;

@Listeners(WorldClock.ExtentReportManager.class)
//Here Listeners Annotation take ExtentReportManager as a paremeter This means that the ExtentReportManager class will listen to the events of the test methods in the WorldClock class, allowing it to generate reports based on those events.
public class TestMain {
public static WebDriver driver;

public static Clock wc;
public static String webtime;
public static OneCognizantApps one;
public static ExcelUtils ex=new ExcelUtils();

public static void screenshot(String name) {
		TakesScreenshot ss = ((TakesScreenshot) driver);
		File src=ss.getScreenshotAs(OutputType.FILE);
		try {
			File target=new File("./reports/Screenshot/"+name+".png");
			FileUtils.copyFile(src, target);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}


@BeforeSuite
public void openPage() {
	  driver=DriverSetUp.getWebDriver("https://be.cognizant.com/");
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	  driver.manage().deleteAllCookies();
	  System.out.println("OpenPage is Successfully Passed");
}


@Test(priority=1)
	public void userInfo() throws InterruptedException, IOException {
		wc=new Clock(driver);
		wc.clickOnUserIcon();
		Thread.sleep(3000);
		TestMain.screenshot("userProfile");
		// Printing User Data
		System.out.println("---- 1.UserData ----");
		System.out.println(wc.getUserName());
		System.out.println(wc.getUserId());
		ex.TestExcelData("1.UserData", 0);
		ex.TestExcelData(wc.getUserName(), 1);
		ex.TestExcelData(wc.getUserId(), 2);
		wc.clickBackUser();
		
		// Scrolling Down By targeting the(SeeAll) using xpath
		WebElement seeAll=driver.findElement(By.xpath("//*[@id=\"spPageCanvasContent\"]/div/div/div/div/div/div/div[2]/div/div[1]"));
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();",seeAll);
		System.out.println("UserInfo is Successfully Captured");
		ex.TestExcelData("UserInfo is Successfully Captured", 3);
	}


@Test(priority = 2)
	public static void Test_WorldClock_Title() throws IOException, InterruptedException {
		wc=new Clock(driver);
		System.out.println("---- 2.WorldClock ----");
		ex.TestExcelData("2.WorldClock", 4);
		System.out.println("Captured Text From WebPage:"+wc.checkClockBox().toLowerCase());
		ex.TestExcelData(wc.checkClockBox().toLowerCase(), 5);
		Assert.assertEquals(wc.checkClockBox().toLowerCase(), "world clock");
		TestMain.screenshot("worldclock");
		System.out.println("Test_WorldClock_Title is Successfully Passed");
		ex.TestExcelData("Test_WorldClock_Title is Successfully Passed",6);
	}
	

	//======================== India Validation ============================

	@Test(priority = 3)
	void bangloreTimeZone() throws IOException, InterruptedException {
		// IST
		Date d = new Date();
		SimpleDateFormat t=new SimpleDateFormat("z");
		String a=(t.format(d)).toUpperCase();
		System.out.println("System TimeZone:"+a);
		ex.TestExcelData("System TimeZone:"+a, 7);
		wc=new Clock(driver);
		String TimeZone=wc.checkIndiaClock();
		int start = TimeZone.indexOf("(") + 1;
		int end = TimeZone.indexOf(")"); 
		String result = (TimeZone.substring(start, end)).toUpperCase(); 
		System.out.println("WebPage TimeZone:"+result);
		ex.TestExcelData("WebPage TimeZone:"+result, 8);
		Assert.assertEquals(result, a);
		System.out.println("bangloreTimeZone is Successfully Passed");
		ex.TestExcelData("bangloreTimeZone is Successfully Passed", 9);
	}

	
	@Test(priority = 4)
	void bangaloreWatchTitle() throws IOException, InterruptedException {
		System.out.println(wc.checkIndiaClock());
		Assert.assertEquals(wc.checkIndiaClock().toLowerCase(), "bangalore, india (ist)");
		System.out.println("bangaloreWatchTitle is Successfully Passed");
		ex.TestExcelData(wc.checkIndiaClock(), 10);
		ex.TestExcelData("bangaloreWatchTitle is Successfully Passed", 11);
	}
	
	
	@Test(priority = 5)
	public static void bangaloreTime() throws IOException, InterruptedException {
		// Getting the date
		Date currentTime = new Date();
		// Formatting the in hours:minutes:(pm/am)
		SimpleDateFormat timeformat=new SimpleDateFormat("h:mma");
		String formatedtime=timeformat.format(currentTime); 
		System.out.println("Time of System:"+formatedtime.toLowerCase());
		webtime=wc.checkIndiaTime();
		System.out.println("Time of WebPage:"+webtime.toLowerCase());
		Assert.assertEquals(webtime.toLowerCase(), formatedtime.toLowerCase());
		System.out.println("bangaloreTime is Successfully Passed");
		ex.TestExcelData("Time of System:"+formatedtime.toLowerCase(),12);
		ex.TestExcelData("Time of WebPage:"+webtime.toLowerCase(),13);
		ex.TestExcelData("bangaloreTime is Successfully Passed",12);
	}
	
	
	@Test(priority = 6)
	public static void bangaloreDate() throws IOException, InterruptedException {
		// Getting the local date -> yyyy-mm-dd
		LocalDate currentSysDate=LocalDate.now();
		// Formatting the date in the desired pattern
		DateTimeFormatter date_formatter=DateTimeFormatter.ofPattern("EEEE, M/d/yyyy");
		// Converting the date as per the format
		String formattedDate=currentSysDate.format(date_formatter);
		System.out.println("Date of System:"+formattedDate.toLowerCase());
		String webdate=wc.chechIndiaDate();
		System.out.println("Date of WebPage:"+webdate.toLowerCase());
		Assert.assertEquals(webdate.toLowerCase(), formattedDate.toLowerCase());
		System.out.println("bangaloreDate is Successfully Passed");
		ex.TestExcelData("Date of System:"+formattedDate.toLowerCase(), 13);
		ex.TestExcelData("Date of WebPage:"+webdate.toLowerCase(), 14);
		ex.TestExcelData("bangaloreDate is Successfully Passed", 15);
	}
	

	// ======================== London Validation ============================
	
	
		@Test(priority = 7)
		void londonWatchTitle() {
			Assert.assertEquals(wc.checkLondonClock().toLowerCase(), "london, uk (bst)");
		}
		@Test(priority = 8)
		void londonTime() throws IOException, InterruptedException {
			wc=new Clock(driver);
			TimeZone.setDefault(TimeZone.getTimeZone("Europe/London"));
			SimpleDateFormat time = new SimpleDateFormat("h:mm");
			Date Stime = new Date();
			String time_lon = time.format(Stime);
			System.out.println("London System time:"+wc.checkLondonTime());
			System.out.println("London Webpage time:"+time_lon);
			Assert.assertEquals(time_lon,wc.checkLondonTime());
			System.out.println("londonTime is Successfully Passed");
			ex.TestExcelData("London System time:"+wc.checkLondonTime(),16);
			ex.TestExcelData("London Webpage time:"+time_lon,17);
			ex.TestExcelData("londonTime is Successfully Passed",18);
		}
		
		
		@Test(priority = 9)
		void londonDate() throws IOException, InterruptedException {
			TimeZone.setDefault(TimeZone.getTimeZone("Europe/London"));
			SimpleDateFormat date = new SimpleDateFormat("EEEE, M/d/yyyy");
			Date date_ = new Date();
			String date_lon = date.format(date_);
			System.out.println("London Webpage date:"+wc.checkLondonDate());
			System.out.println("London System date:"+date_lon);
			Assert.assertEquals(date_lon,wc.checkLondonDate());
			System.out.println("londonDate is Successfully Passed");
			ex.TestExcelData("London Webpage date:"+wc.checkLondonDate(), 19);
			ex.TestExcelData("London System date:"+date_lon, 20);
			ex.TestExcelData("londonDate is Successfully Passed", 21);
		}
		
		
		
		@Test(priority = 10)
		void londonTimeGap() throws IOException, InterruptedException {
		    TimeZone bangloreTimeZone = TimeZone.getTimeZone("Asia/Kolkata");
		    TimeZone LondonTimeZone = TimeZone.getTimeZone("Europe/London");
		    Calendar cal = Calendar.getInstance();
		    int hoursDifference = (bangloreTimeZone.getRawOffset() - LondonTimeZone.getRawOffset()) / (60 * 60 * 1000);
		    if (LondonTimeZone.inDaylightTime(cal.getTime())) {
		        hoursDifference--;
		    }
		    int minutesDifference = (bangloreTimeZone.getRawOffset() - LondonTimeZone.getRawOffset()) / (60 * 1000) % 60;
		    String lonbanggap = hoursDifference + "h " + minutesDifference + "m " + "behind";
		    System.out.println("London System Time-Gap:" + lonbanggap);
		    System.out.println("London Webpage Time-Gap:" + wc.checkLondonGapTime());
		    Assert.assertEquals(lonbanggap, wc.checkLondonGapTime());
		    System.out.println("londonTimeGap is Successfully Passed");
		    ex.TestExcelData("London System Time-Gap:" + lonbanggap, 22);
		    ex.TestExcelData("London Webpage Time-Gap:" + wc.checkLondonGapTime(), 23);
		    ex.TestExcelData("londonTimeGap is Successfully Passed", 24);
		}
		
		
		// ======================== NewYork Validation ============================


		@Test (priority = 11)
		void NewYorkWatchTitle() throws IOException, InterruptedException {
			Assert.assertEquals(wc.newYorkClock().toLowerCase(), "new york, ny (est)");
			System.out.println("NewYorkWatchTitle is Successfully Passed");
			ex.TestExcelData("NewYorkWatchTitle is Successfully Passed", 25);
		}
		
		
		@Test(priority = 12)
		void NewYorkTime() throws IOException, InterruptedException {
			 
			TimeZone.setDefault(TimeZone.getTimeZone("America/New_York"));
			SimpleDateFormat time = new SimpleDateFormat("h:mm");
			Date time_ = new Date();
			String time_NY = time.format(time_);
			System.out.println("NewYork System time:"+wc.checkNewYorkTime());
			System.out.println("NewYork WebPage time:"+time_NY);
			Assert.assertEquals(time_NY,wc.checkNewYorkTime());	
			System.out.println("NewYorkTime is Successfully Passed");
			ex.TestExcelData("NewYork System time:"+wc.checkNewYorkTime(), 26);
			ex.TestExcelData("NewYork WebPage time:"+time_NY, 27);
			ex.TestExcelData("NewYorkTime is Successfully Passed", 28);
		}
		
		
		@Test (priority = 13)
		void NewYorkDate() throws IOException, InterruptedException {
			TimeZone.setDefault(TimeZone.getTimeZone("America/New_York"));
			SimpleDateFormat date = new SimpleDateFormat("EEEE, M/d/yyyy");
			Date date_ = new Date();
			String date_NY = date.format(date_);
			System.out.println("NewYork WebPage Date:"+wc.checkNewYorkDate());
			System.out.println("NewYork System Date:"+date_NY);
			Assert.assertEquals(date_NY,wc.checkNewYorkDate());
			System.out.println("NewYorkDate is Successfully Passed");
			ex.TestExcelData("NewYork WebPage Date:"+wc.checkNewYorkDate(), 29);
			ex.TestExcelData("NewYork System Date:"+date_NY, 30);
			ex.TestExcelData("NewYorkDate is Successfully Passed", 31);
		}
		
		
		@Test (priority = 14)
		void NewYorkTimeGap() throws IOException, InterruptedException {
			TimeZone bangloreTimeZone = TimeZone.getTimeZone("Asia/Kolkata");
			TimeZone newYorkTimeZone = TimeZone.getTimeZone("America/New_York");
			long currentTime = System.currentTimeMillis();
			int hoursDifference = (bangloreTimeZone.getOffset(currentTime) - newYorkTimeZone.getOffset(currentTime)) / (60 * 60 * 1000);
			int minutesDifference = Math.abs((bangloreTimeZone.getOffset(currentTime) - newYorkTimeZone.getOffset(currentTime)) / (60 * 1000) % 60);
			String NYbanggap = hoursDifference + "h " + minutesDifference + "m " + "behind";
			System.out.println("NewYork System Time-Gap"+NYbanggap);
			System.out.println("NewYork WebPage Time-Gap"+wc.checkNewYorkIndiaGapTime());
			Assert.assertEquals(NYbanggap, wc.checkNewYorkIndiaGapTime());
			TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
			System.out.println("NewYorkTimeGap is Successfully Passed");
			ex.TestExcelData("NewYork System Time-Gap"+NYbanggap,32);
			ex.TestExcelData("NewYork WebPage Time-Gap"+wc.checkNewYorkIndiaGapTime(),33);
			ex.TestExcelData("NewYorkTimeGap is Successfully Passed",34);
		}
		
		
		
		// ===================== One Cognizant ===========================
		
		@Test(priority=15)
		public void scroll() {
			WebElement appAndTool=driver.findElement(By.xpath("(//*[@id=\"CaptionElementView\"])[2]"));
			 JavascriptExecutor j= (JavascriptExecutor)driver;
			j.executeScript("arguments[0].scrollIntoView();",appAndTool);
			TestMain.screenshot("onecognizant");
		} 
		
		
		
		@Test(priority=16)	
		public void viewApps() throws InterruptedException{
			//view all apps
			one=new OneCognizantApps(driver);
			one.oneCognizantClick();
			Set<String> windowid = driver.getWindowHandles();
			List<String> windowsidList = new ArrayList<String> (windowid);
			for (int i =0;i<windowsidList.size();i++) {
				String title=driver.switchTo().window(windowsidList.get(i)).getTitle();
				if (title.equals("OneCognizant")) {
					break;
				}
			}
				one.hotAppsScroll();
				Thread.sleep(2000);
				TestMain.screenshot("hotsapps");
				one.hotAppsViewAllClick();
		}
		
		
		
		@Test(priority=17)
		public void appsAlphabet() throws IOException, InterruptedException {   
			 //disabled alphabet
			one=new OneCognizantApps(driver);
			int i=60;
				List<String> str= new ArrayList<String>();
				for(WebElement e:one.ele3) {
					if(e.getAttribute("role")!=null) {
						continue;
					}
					else
						str.add(e.getText());
					}
				for(String s: str) {
				System.out.println(s+" is disabled");
				ex.TestExcelData(s+" is disabled", i);
				i++;
				}
			}
		
		
		
		 @Test(priority=18)
			void randomAlphabet() throws InterruptedException, IOException {
			    Random rand=new Random();
			    List<WebElement> list = one.ele3;
			    System.out.println("*****************");
			    while(true) {
			        int n=rand.nextInt(list.size()); // 0 to (size of list) generate random numbers
			        if(list.get(n).equals("X")) {
			            continue;
			        }else if(list.get(n).equals("Y")) {
			            continue;
			        }else {
			        	// Waiting to implicit wait load
			        	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
			            // Create a new WebDriverWait instance
			            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			            // Wait until the element is clickable
			            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(list.get(n)));
			            // Click the element
			            element.click();
			            Thread.sleep(2000);
			            TestMain.screenshot("randomclick");
			            System.out.println("****** Apps of Random Click *******");
			            one.printAppsName();
			            break;
			        }
			    }
}
		 
		 
		  @AfterSuite
		  void closeBrowser() throws InterruptedException {
			  Thread.sleep(1000);
			  driver.quit();
		  }

}

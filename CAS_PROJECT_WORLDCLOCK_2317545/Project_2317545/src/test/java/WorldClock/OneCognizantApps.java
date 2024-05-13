package WorldClock;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class OneCognizantApps {
	public WebDriver driver;
	public ExcelUtils eo=new ExcelUtils();
	
	OneCognizantApps(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
//    @FindBy (xpath="(//div[@id='QuicklinksItemTitle'])[5]") WebElement ele;
	@FindBy (xpath="//div[@id='QuicklinksItemTitle' and contains(text(),\"OneCognizant\")]") WebElement ele;
    @FindBy (xpath="//div[@class='viewAllHotAppsBtn']") WebElement ele2;
    @FindBy (xpath="//div[@class ='aZHolder']/div") List<WebElement> ele3;
	public void oneCognizantClick()
	{
		ele.click();
	}
	public void hotAppsScroll()
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();",ele2);
	}
	public void hotAppsViewAllClick() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", ele2);
	}
	@FindBy (xpath="//*[@id='div_appFilteredList']/div/div") List<WebElement> apps;
	@FindBy(xpath="//*[@id='div_appFilteredList']/div/div/div/div[@class='appStoreAppText valign-wrapper']/div")
	List<WebElement> app;
	public void printAppsName() throws IOException, InterruptedException {
		eo.OneCognizantData(app);
	}
	
}

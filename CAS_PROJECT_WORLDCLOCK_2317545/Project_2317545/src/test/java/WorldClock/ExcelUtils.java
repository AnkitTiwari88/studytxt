package WorldClock;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;

public class ExcelUtils {
	
	public void TestExcelData(String str, int row) throws IOException, InterruptedException {
	    String filePath = System.getProperty("user.dir") + "\\target\\OutputDetails.xlsx";
	    XSSFWorkbook workbook;
	    XSSFSheet sheet;
	    File file = new File(filePath);
	    
	    if (file.exists()) {
	        FileInputStream fis = new FileInputStream(file);
	        workbook = new XSSFWorkbook(fis);
	        sheet = workbook.getSheetAt(0);
	        fis.close();
	    } else {
	        workbook = new XSSFWorkbook();
	        sheet = workbook.createSheet("Sheet1");
	    }
	    
	    XSSFRow currentRow = sheet.createRow(row);
	    currentRow.createCell(0).setCellValue(str);
	    
	    FileOutputStream fos = new FileOutputStream(file);
	    workbook.write(fos);
	    workbook.close();
	    fos.close(); 
	}
	
	public void OneCognizantData(List<WebElement> apps) throws IOException, InterruptedException {
		Thread.sleep(3000);
		FileOutputStream file=new FileOutputStream(System.getProperty("user.dir")+"\\target\\OneCognizantDetails.xlsx");
		XSSFWorkbook workbook=new XSSFWorkbook();
		XSSFSheet sheet=workbook.createSheet();
		for(int r=0;r<apps.size();r++)
		{
			XSSFRow currentrow=sheet.createRow(r);			
				String value=apps.get(r).getText();
				System.out.println(value);
				currentrow.createCell(0).setCellValue(value);
		}
		workbook.write(file);
		workbook.close();
		file.close();
	
	}
	
}

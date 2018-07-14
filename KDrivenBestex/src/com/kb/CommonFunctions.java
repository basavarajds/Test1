package com.kb;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CommonFunctions {
	
	
	public static String Navigate_To(WebDriver driver,String Url)
	{
		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		driver.get(Url);
		driver.manage().window().maximize();
		return null;
		
	}
	
	public static String Click_Element(WebDriver driver,String strLocatorType,String strLocatorValue) throws Exception
	{
		
		By byElement = getLocatorType(strLocatorType,strLocatorValue);
		driver.findElement(byElement).click();
		Thread.sleep(5000);
		return null;
		/*switch(strLocatorType)
		{
			case "id":
			driver.findElement(By.id(strLocatorValue)).click();
			break;
			
			case "xpath":
				driver.findElement(By.xpath(strLocatorValue)).click();
				break;
			case "linkText":
				driver.findElement(By.linkText(strLocatorValue)).click();
				break;
		}
		
		Thread.sleep(5000);
		return null;*/
		
	}
	
	public static String Sendkeys(WebDriver driver,String strLocatorType,String strLocatorValue,String param1) throws Exception
	{
		
		By byElement = getLocatorType(strLocatorType,strLocatorValue);
		driver.findElement(byElement).sendKeys(param1);
		Thread.sleep(5000);
		return null;
		
		/*switch(strLocatorType)
		{
			case "id":
			driver.findElement(By.id(strLocatorValue)).sendKeys(param1);
			break;
			
			case "xpath":
				driver.findElement(By.xpath(strLocatorValue)).sendKeys(param1);
				break;
			case "linkText":
				driver.findElement(By.linkText(strLocatorValue)).sendKeys(param1);
				break;
		}
		
		Thread.sleep(5000);
		return null;*/
		
	}

	public static void quit_driver(WebDriver driver)
	{
		driver.close();
	}
	
	public static By getLocatorType(String strLocatorType,String strLocatorValue)
	{
	 By byElement = null;
		switch (strLocatorType) { 
			case "id": 
			{
		         byElement = By.id(strLocatorValue);
		         break;
		    }
		     case "name":
		     {
		         byElement = By.name(strLocatorValue);
		         break;
		     }
		     case "classname":
		     {
		         byElement = By.className(strLocatorValue);
		         break;
		     }
		     case "linkText":
		     {
		         byElement = By.linkText(strLocatorValue);
		         break;
		     }
		     
		     case "xpath":
		    	 byElement = By.xpath(strLocatorValue);
				 break;
			
		}
		return byElement;
	}
	
	public static String[][] fetchExcelData(String path) throws IOException
	{
		
		
		File fileexcel = new File(path);
		FileInputStream fis = new FileInputStream(fileexcel);
		HSSFWorkbook wb = new HSSFWorkbook(fis);
		HSSFSheet ws = wb.getSheet("Sheet1");
		
		int rowNum = ws.getLastRowNum();
		System.out.println(rowNum);
		int colNum= ws.getRow(0).getLastCellNum();
		
		String [][] Data = new String[rowNum][colNum];
		
		for( int i=0; i < rowNum ; i++)
		{
			HSSFRow row = ws.getRow(i);
			for(int j=0;j<colNum;j++ )
			{
				HSSFCell cell = row.getCell(j);
				
				String value = cellToString(cell);
				Data[i][j] = value;
				System.out.println(Data[i][j]);
				
			}
		}
		wb.close();
		fis.close();
		
		return Data;
	}

	
	public static String cellToString(HSSFCell cell)
	{
		
		Object result;
		String strReturn = null;
		if(cell == null)
		{
			strReturn="";
		}
		else
		{
			if(cell.getCellTypeEnum() == CellType.STRING)
			{
				result = cell.getStringCellValue();
				strReturn = result.toString();
			}
			else if(cell.getCellTypeEnum() == CellType.NUMERIC)
			{
				result = cell.getNumericCellValue();
				strReturn = result.toString();
			}
			else
			{
				strReturn ="";
			}
			
		}
			
		
		
		return strReturn;
		
	}

}

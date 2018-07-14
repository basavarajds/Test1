package com.kb;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Testcasedrivdr extends CommonFunctions{

	public static void main(String[] args) throws Exception {
		
		//THis is second comment
		// TODO Auto-generated method stub
		String path = "D:\\Keyword_Driven_Framework\\ReadExcelData\\ReadExcelData.xls";
		String data[][] = fetchExcelData( path);
		WebDriver driver = null;
		System.setProperty("webdriver.chrome.driver", "D:\\Selennium_Software\\chromedriver.exe");
		driver = new ChromeDriver();
		
		for(int i=2; i <=data.length; i++ )
		{
			switch(data[i][4])
			{
				case "Navigate_To":
					 Navigate_To(driver,data[i][7]);
					 break;
				case "Sendkeys":
					Sendkeys(driver,data[i][5],data[i][6],data[i][7]);
					 break;
				case "Click_Element":
					Click_Element(driver,data[i][5],data[i][6]);
					 break;
				case "quit_driver":
					quit_driver(driver);
					 break;
			}
		}
		
	}

}

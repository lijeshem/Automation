package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	
	public static final String TEST_DATA_SHEET_PATH = "./src/test/resources/testdata/DemoAppTestData.xlsx";
	private static Workbook workBook;
	private static Sheet sheet;
	
	public static Object[][] getTestData(String sheetName) {
		
		Object data[][] = null;
		
		try {
			FileInputStream fis = new FileInputStream(TEST_DATA_SHEET_PATH);
			try {
				workBook = WorkbookFactory.create(fis);
				sheet = workBook.getSheet(sheetName);
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		
		data = new Object [sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for(int row=0;row<sheet.getLastRowNum();row++) {
			for(int column=0;column<sheet.getRow(0).getLastCellNum();column++) {
				data[row][column] = sheet.getRow(row+1).getCell(column).toString();
				
			}
		}
		
		return data;
	}

}

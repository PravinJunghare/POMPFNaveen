package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

	private static final String TESTDATA_EXCEL_SHEET_PATH = "./src/test/resources/testdata/OpenCartTestData.xlsx ";
	private static Workbook workbook;
	public static org.apache.poi.ss.usermodel.Sheet sheet;

	public static Object[][] getTestData(String sheetName) {
		Object data[][] = null;

		try {
			FileInputStream io = new FileInputStream(TESTDATA_EXCEL_SHEET_PATH);
			// For file path
			workbook = WorkbookFactory.create(io);
			// Load the in the memory and create new sheet
			sheet = workbook.getSheet(sheetName);
			// to go particular sheet

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (InvalidFormatException e) {
			//
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		// harcoded value Object[4][6] if give ;
		// getlastRowNum for rows// sheet.getRow> goto first row and give count of last
		// cell will be column

		for (int i = 0; i < sheet.getLastRowNum(); i++)// row
		{
			for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++)// column
			{
				data[i][j] = sheet.getRow(i + 1).getCell(j).toString();// covert data into string as data is in java
			}
		}
		return data;
	}

}

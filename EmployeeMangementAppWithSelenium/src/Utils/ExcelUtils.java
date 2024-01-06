package Utils;

import java.io.FileInputStream;

import java.io.FileOutputStream;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ExcelUtils {
	public static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	private static XSSFRow xRow;

	// This method is to set the File path and to open the excel file, Pass Excel Path and Sheetname as Arguments to this method .
	public static void setExcelFile(String Path, String SheetName) throws Exception {
		try {
			// Open the Excel File
			FileInputStream ExcelFile = new FileInputStream(Path);
			// Access the required test data sheet
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
		}catch(Exception e) {
			throw(e);
		}
	}
	//get number of rows
	public static int getRowsNum() {
		return ExcelWSheet.getPhysicalNumberOfRows();
		
	}
	//get number of col
	public static int getColsNum() {
		return ExcelWSheet.getRow(0).getPhysicalNumberOfCells();
		
	}
	// This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num
	public static String getCellData(int RowNum, int ColNum ) throws Exception{
		try {
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			String CellData = Cell.getStringCellValue();
			return CellData;
		}catch(Exception e){
			return "";
		}
	}
	
	// This method is to write in the Excell cell , Row num and Col num are the parameters.
	public static void setCellData(String Result, int RowNum, int ColNum) throws Exception {
		try {
			xRow = ExcelWSheet.getRow(RowNum);
			Cell = xRow.getCell(ColNum);
			if(Cell==null) {
				Cell=xRow.createCell(ColNum);
				Cell.setCellValue(Result);
			}else {
				Cell.setCellValue(Result);
			}
			
			// Constant variables Test Data path and Test Data file name
			FileOutputStream fileOut = new FileOutputStream(Constant.Path_TestData + Constant.File_TestData);
			ExcelWBook.write(fileOut);
			fileOut.flush();
			fileOut.close();
		}catch(Exception e) {
			throw(e);
		}
	}
}


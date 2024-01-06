package Utils;

import Utils.Constant;
import Utils.ExcelUtils;

public class ExcelUtilsData  {
	public static void main(String[] args) {
		

}
	public static int rowNum;
	public static int ColNum;

	public static Object[][] TestData(String path,String Sheet) throws Exception {
		ExcelUtils excel=new ExcelUtils();
		excel.setExcelFile(path,Sheet);
		int rowNum=excel.getRowsNum();
		 int ColNum=excel.getColsNum();
		Object  data [][]=new Object[rowNum-1][ColNum]; 
		for (int i = 1; i < rowNum; i++) {
			for (int j = 0; j < ColNum; j++) {
				String dataCell=excel.getCellData(i, j);
				data[i-1][j]=dataCell;	
			}	
		}
		return data;		
	}
}

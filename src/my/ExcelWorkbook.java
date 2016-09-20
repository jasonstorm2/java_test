package my;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelWorkbook {
	public static void main(String[] args) throws Exception {
		Workbook wb = null;
		InputStream instream = new FileInputStream("D:/JarStore/Activity.xlsx");	
		//excel对象
		wb = WorkbookFactory.create(instream);
		//表对象--第一张表
		Sheet readSheet = wb.getSheet("0");
		//总列数
//		int columns = readSheet.getColumns();
//		//总行数
//		int rows = readSheet.getRows();
//		//获得指定单元的对象引用
//		for (int i = 0; i < rows; i++) {
//			for (int j = 0; j < columns; j++) {
//				Cell cell = readSheet.getCell(j, i);	
//				System.out.println("列："+cell.getColumn()+"  行："+cell.getRow()+"  内容："+cell.getContents());
//			}
//		}
	}
}

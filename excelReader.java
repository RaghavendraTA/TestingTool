package testPack;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class HobsBulkUpdate {
	
	public void excelReader() throws FileNotFoundException, IOException {
		
		XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream("./some.xlsx"));
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow row = sheet.getRow(0);
		
		Iterator<Cell> cells = row.cellIterator();
		ArrayList<String> ls = new ArrayList<>();
		while(cells.hasNext()) {
			ls.add(cells.next().getStringCellValue());
		}
		
		workbook.close();
	}
}

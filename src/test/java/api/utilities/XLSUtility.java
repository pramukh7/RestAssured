package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLSUtility {
	
	public FileInputStream fileInputStream;
	public FileOutputStream fileOutputStream;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	String path;
	
	public XLSUtility (String path)
	{
		this.path = path;
	}
	
	public int getRowCount(String sheetName) throws IOException {
		sheet = getSheet(sheetName);
		int totalRow = sheet.getLastRowNum();
		workbook.close();
		fileInputStream.close();
		return totalRow;
	}
	
	public int getCellCount(String sheetName, int rowNum) throws IOException
	{
		sheet = getSheet(sheetName);
		row = sheet.getRow(rowNum);
		int totalCell = row.getLastCellNum();
		workbook.close();
		fileInputStream.close();
		return totalCell;
	}
	
	public String getCellData(String sheetName, int rowNum, int colNum) throws IOException
	{
		sheet = getSheet(sheetName);
		row = sheet.getRow(rowNum);
		cell = row.getCell(colNum);
		DataFormatter formatter = new DataFormatter();
		String data="";
		try {
			/*
			 * Returns the formatted value of a cell as a {@code String} regardless
		     * of the cell type. If the Excel format pattern cannot be parsed then the
		     * cell value will be formatted using a default format.
		     * </p>
		     * <p>When passed a null or blank cell, this method will return an empty
		     * String ("").
			 */
			data = formatter.formatCellValue(cell);
		} catch (Exception e)
		{
			data="";
		}
		workbook.close();
		fileInputStream.close();
		return data;
	}
	
	public void setCellData(String sheetName, int rowNum, int colNum, String dataToInsert) throws IOException
	{
		File file = new File(this.path);
		if(!file.exists())
		{
			workbook = new XSSFWorkbook();
			fileOutputStream = new FileOutputStream(this.path);
			workbook.write(fileOutputStream);
		}
		
		fileInputStream = new FileInputStream(this.path);
		workbook = new XSSFWorkbook(fileInputStream);
		if(workbook.getSheetIndex(sheetName) == -1)
			workbook.createSheet(sheetName);
			
		sheet = workbook.getSheet(sheetName);
		
		if(sheet.getRow(rowNum) == null)
			sheet.createRow(rowNum);
			
		row = sheet.getRow(rowNum);
		
		cell = row.createCell(colNum);
		cell.setCellValue(dataToInsert);
		fileOutputStream = new FileOutputStream(this.path);
		workbook.write(fileOutputStream);
		workbook.close();
		fileInputStream.close();
		fileOutputStream.close();
	}
	
	public void fillGreenColor(String sheetName, int rowNum, int colNum) throws IOException
	{
		sheet = getSheet(sheetName);
		row = sheet.getRow(rowNum);
		cell = row.getCell(colNum);
		
		style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		workbook.write(fileOutputStream);
		workbook.close();
		fileInputStream.close();
		fileOutputStream.close();
		
	}
	
	public void fillRedColor(String sheetName, int rowNum, int colNum) throws IOException
	{
		sheet = getSheet(sheetName);
		row = sheet.getRow(rowNum);
		cell = row.getCell(colNum);
		
		style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		workbook.write(fileOutputStream);
		workbook.close();
		fileInputStream.close();
		fileOutputStream.close();
		
	}
	
	private XSSFSheet getSheet(String sheetName) throws IOException {
			fileInputStream = new FileInputStream(this.path);
			workbook = new XSSFWorkbook(fileInputStream);
			XSSFSheet sheet = workbook.getSheet(sheetName);
			return sheet;
	}

}

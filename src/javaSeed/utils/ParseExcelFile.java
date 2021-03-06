package javaSeed.utils;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ParseExcelFile {
	

	private String ExcelData[][];
	
	@SuppressWarnings("deprecation")
	public String[][] ReadExcel(String ExcelPath, String SheetName){
		try {
			
			
			//Create Excel File instance holding reference to .xlsx file
			FileInputStream oxlsx = new FileInputStream(new File(ExcelPath));
			
			//Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook owbook = new XSSFWorkbook(oxlsx);
            
            //Get first/desired sheet from the workbook
            XSSFSheet oenvSheet1 = owbook.getSheet(SheetName);
            // Declaring XSSFRow objects
            XSSFRow row;
            XSSFCell cell;
            
            int rowIndex=0,colIndex=0,rows,cols;
            
            // Getting number of Rows and Columns in the variables 
            rows=oenvSheet1.getPhysicalNumberOfRows();
            cols=oenvSheet1.getRow(rowIndex).getPhysicalNumberOfCells();
            // Re-initializing the local 2D Array for the number of rows and columns 
            ExcelData=new String[rows][cols];
            
            // Loop though the Excel and get each Physical Cell data in the respective Array index
            for (rowIndex=0;rowIndex<rows;rowIndex++){
            	row = oenvSheet1.getRow(rowIndex);
            	
            	for(colIndex=0;colIndex<cols;colIndex++){
            		cell = row.getCell(colIndex);
            			if (cell!=null){
		            		if (cell.getCellType()==1){
		            			ExcelData[rowIndex][colIndex]=cell.getStringCellValue();
		            		}
		            		else if (cell.getCellType()==0){
		            			ExcelData[rowIndex][colIndex]=String.valueOf(cell.getNumericCellValue());
		            		}
		            		else if (cell.getCellType()==3){
		            			ExcelData[rowIndex][colIndex]="null";
		            		}
            			}		            	
		            	else if (cell==null){
		            		ExcelData[rowIndex][colIndex]="null";
		            	}

            		//ExcelData[rowIndex][colIndex]=cell.toString();
            	}
            }
            // Close the Workbook and File Objects
            owbook.close();
            oxlsx.close();
            
		}
		catch (Exception e){
			e.printStackTrace();
		}
        return ExcelData;
	}

}
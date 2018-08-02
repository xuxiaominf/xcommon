package utils.xcommon.util.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

/**
 * excel 操作
 * @author xuxiaoming
 * @version $Id: ReadExcelPlatformManage.java, v 0.1 2016年8月1日 下午4:54:54 xuxiaoming Exp $
 */
public class ReadExcelPlatformManage {
	/** 
     * 读取Office 2003 excel 
     * */ 
	 public static List<List<String>> read2003Excel(File excelFile){
		
		 try{
			List<List<String>> listlist = new ArrayList<List<String>>();
	        Workbook rwb = null;
	        Cell cell = null;
	        // 创建输入流
	        InputStream stream = new FileInputStream(excelFile);
	        // 获取Excel文件对象
	        rwb = Workbook.getWorkbook(stream);
	        // 获取文件的指定工作表默认的第一个
	        Sheet sheet = rwb.getSheet(0);
	        // 行数(表头的目录不需要，从1开始)
	        for (int i = 1; i < sheet.getRows(); i++) {
		        // 创建一个数组用来存储每一列的值
		        List<String> list = new ArrayList<String>();  
		        // 列数
		        for (int j = 0; j < sheet.getColumns(); j++) {
			      // 获取第i行，第j列的值
			      cell = sheet.getCell(j, i);
			      list.add(cell.getContents());
		        }
		        if (!list.equals("")) {
			    listlist.add(list);
		        }
	        }
	  stream.close();
	  excelFile.delete();
      return listlist;
     } catch (Exception e) {
         return null;
     }
   }	
	 
	/** 
     * 读取Office 2007 excel 
     * */  
   public static List<List<String>> read2007Excel(File excelFile)  { 
		
	   try {
		List<List<String>> listlist = new ArrayList<List<String>>();
		FileInputStream input= new FileInputStream(excelFile);
        // 构造 XSSFWorkbook 对象，excelFile 传入文件路径  
        XSSFWorkbook xwb = new XSSFWorkbook(input);  
        // 读取第一章表格内容  
        XSSFSheet sheet = xwb.getSheetAt(0);  
        XSSFRow row = null;  
        XSSFCell cell = null;  
        int counter = 0;  
        for (int i = sheet.getFirstRowNum(); counter < sheet  
                .getPhysicalNumberOfRows(); i++) {  
            row = sheet.getRow(i);  
            if (row == null) {  
                continue;  
            } else {  
                counter++;  
            }  
            List<String> list = new ArrayList<String>();  
            for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {  
                cell = row.getCell(j);  
                if (cell == null) {  
                    continue;  
                }
              String value = cell.getStringCellValue();  
                list.add(value);
            }  
			if (!list.equals("")) {
		       listlist.add(list);
	        }
        }  
        input.close();
        excelFile.delete();
        return listlist;
		} catch (Exception e) {
		    return null;
		}
	   
    } 
}

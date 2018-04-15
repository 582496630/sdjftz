package com.youotech.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ReadExecl {
		public static void main(String[] args) throws IOException {
			/*  String path = "C:\\Users\\Administrator\\Desktop\\PMS2.0台账\\电流互感器.xls";
			  List<Object> data = new ArrayList<>();
			  List<Map<Integer, Object>> list = readExcel(path);
			  for (Map<Integer, Object> map : list) {
				   PmsElectriCityInductor pec = new PmsElectriCityInductor(map);
				   System.out.println(pec);
			}*/
		}
		
		
		public static List<Map<Integer, Object>> readExcel(String filepath) throws IOException{
			 InputStream is = new FileInputStream(filepath);
	 	     Workbook wb =new HSSFWorkbook(is);
	 	     Sheet sheet = wb.getSheetAt(0);  
	 	      // 得到总行数  
	         int rowNum = sheet.getLastRowNum();  
	         Row row = sheet.getRow(0);  
	         int colNum = row.getPhysicalNumberOfCells();
	         List<Map<Integer, Object>> list = new ArrayList<Map<Integer, Object>>();
	         // 正文内容应该从第二行开始,第一行为表头的标题  第二行为列名称
	         for (int i = 2; i <= rowNum; i++) {  
	            row = sheet.getRow(i);  
	            int j = 1;  
	            Map<Integer,Object> cellValue = new HashMap<Integer, Object>();  
	            while (j < colNum) {  
	                Object obj = row.getCell(j)==null ?"":row.getCell(j);  
	                cellValue.put(j, obj);  
	                j++;  
	            }
	            list.add(cellValue);
	         }    
				 return list;
		}
}

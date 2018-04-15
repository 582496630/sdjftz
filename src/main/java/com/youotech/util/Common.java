package com.youotech.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.opencsv.CSVReader;

import sun.misc.*;

/**
 * 
    * @ClassName: Common
    * @Description: 常用工具类
    * @author lfx
    * @date 2017年8月31日
    *
 */
public class Common {
	
	/**
	 * 
	    * @Title: getCurrentMonthFirstDay
	    * @Description: 获取当前月第一天
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
	public static String getCurrentMonthFirstDay(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		String first = format.format(c.getTime());
		return first;
	}
	
	/**
	 * 
	    * @Title: getCurrentMonthLastDay
	    * @Description: 获取当前月最后一天
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
	public static String getCurrentMonthLastDay(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
		String last = format.format(ca.getTime());
		return last;
	}

	/**
	 * 
	    * @Title: getFromBase64
	    * @Description: base64解密
	    * @param @param s
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
    @SuppressWarnings("restriction")
	public static String getFromBase64(String s) {  
        byte[] b = null;  
        String result = null;  
        if (s != null) {  
            BASE64Decoder decoder = new BASE64Decoder();  
            try {  
                b = decoder.decodeBuffer(s);  
                result = new String(b, "utf-8");  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
        return result;  
    }
 
	/**
	 * 
	    * @Title: setToBase64
	    * @Description: base64加密
	    * @param @param s
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
    @SuppressWarnings("restriction")
	public static String setToBase64(String s) {  
    	BASE64Encoder encoder = new BASE64Encoder();  
    	byte[] b=null;
		try {
			b = s.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        String str=encoder.encode(b); 
        return str;  
    }  
	
	/**
	 * 
	* @Title: getNowTime  
	* @Description: 获得当前时间
	* @param @return    设定文件  
	* @return String    返回类型  
	* @throws
	 */
	public static String getNowTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String now = sdf.format(date);
		return now;
	}
	
	/**
	 * 
	    * @Title: formatDate
	    * @Description: 日期时间String类型转化为Date类型
	    * @param @param date
	    * @param @return
	    * @param @throws Exception    参数
	    * @return Date    返回类型
	    * @throws
	 */
	public static Date formatDate(String date){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dateTemp = null;
		if (!isEmpty(date)) {
			try {
				dateTemp = simpleDateFormat.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return dateTemp;
	} 
	
	/**
	 * 
	    * @Title: formatTimestamp
	    * @Description: 格式化时间戳，转化为Date类型
	    * @param @param timestamp
	    * @param @return
	    * @param @throws Exception    参数
	    * @return Date    返回类型
	    * @throws
	 */
	public static Date formatTimestamp(String timestamp) throws Exception{
		Date date = null;
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Long time=new Long(timestamp); 
		String dateTemp = format.format(time);  
	    date=format.parse(dateTemp);
		return date;
	}
	
	/**
	 * 
	    * @Title: isEmpty
	    * @Description: 判断变量是否为空
	    * @param @param s
	    * @param @return    参数
	    * @return boolean    返回类型
	    * @throws
	 */
	public static boolean isEmpty(String s) {
		if (null == s || "".equals(s) || "".equals(s.trim()) || "null".equalsIgnoreCase(s)) {
			return true;
		} else {
			return false;
		}
	}
	 
	/**
	 * 
	    * @Title: removeSameItem
	    * @Description: 用来去掉List中空值和相同项的
	    * @param @param list
	    * @param @return    参数
	    * @return List<String>    返回类型
	    * @throws
	 */
	public static List<String> removeSameItem(List<String> list) {
		List<String> difList = new ArrayList<String>();
		for (String t : list) {
			if (t != null && !difList.contains(t)) {
				difList.add(t);
			}
		}
		return difList;
	}

	/**
	 * 返回用户的IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String toIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 传入原图名称，，获得一个以时间格式的新名称
	 * 
	 * @param fileName
	 *            　原图名称
	 * @return
	 */
	public static String generateFileName(String fileName) {
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String formatDate = format.format(new Date());
		int position = fileName.lastIndexOf(".");
		String extension = fileName.substring(position);
		return formatDate + extension;
	}
    
	/**
	 * 上传文件到给定的格式中
	 * @param path
	 * @param fileName
	 */
	public static void uploadFile(CommonsMultipartFile upfile,String path,String fileName){
		InputStream is = null;
        OutputStream os =null;
        
        //文件不存在时自动创建
        File saveFile=null; 
        if (upfile != null) { 
            saveFile = new File(new File(path), fileName); 
            if (!saveFile.getParentFile().exists()) { 
                saveFile.getParentFile().mkdirs(); 
            } 
        }
        try {
			   //获取输入流  
			   is = upfile.getInputStream();
			   //文件输出流  
	           os =new FileOutputStream(new File(path,fileName));  
	           //循环写入  
	           int length=0;  
	           byte [] buffer=new byte[128];  
	           while((length=is.read(buffer))!=-1)  
	           {  
	               os.write(buffer,0, length);  
	           }  
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			   try {
				 if(is!=null)
				 is.close();
				 if(os!=null)
				 os.close(); 
			} catch (IOException e) {
				e.printStackTrace();
			}  
		}
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
	
	/**
	 * 
	    * @Title: readExcel
	    * @Description: 读取Excel文件
	    * @param @param filepath
	    * @param @return
	    * @param @throws IOException    参数
	    * @return List<Map<Integer,Object>>    返回类型
	    * @throws
	 */
	public static List<Map<Integer, Object>> readExcel(String filepath, String suffixName) throws IOException{
		InputStream is = new FileInputStream(filepath);
	    Workbook wb = null;
	    if ("xls".equalsIgnoreCase(suffixName)) {
	    	wb = new HSSFWorkbook(is);
		}else if("xlsx".equalsIgnoreCase(suffixName)){
			wb = new XSSFWorkbook(is);
		}
	    Sheet sheet = wb.getSheetAt(0);  
	    // 得到总行数  
        int rowNum = sheet.getLastRowNum();  
        Row row = sheet.getRow(0);  
        int colNum = row.getPhysicalNumberOfCells();
        List<Map<Integer, Object>> list = new ArrayList<Map<Integer, Object>>();
        // 正文内容应该从第二行开始,第一行为表头的标题  第二行为列名称(程序已处理，默认从第二行开始)
        String dateTime = null;
        for (int i = 1; i <= rowNum; i++) {  
           row = sheet.getRow(i);  
           int j = 0;  
           Map<Integer,Object> cellValue = new HashMap<Integer, Object>();  
           while (j < colNum) {  
               Object obj = row.getCell(j);
               dateTime = buildDate(obj,suffixName);
               cellValue.put(j, dateTime);  
               j++;  
           }
           list.add(cellValue);
        }    
        return list;
	}
	
	/**
	 * 
	    * @Title: readCsv
	    * @Description: 读取CSV文件,需要保证CSV文件日期格式为yyyy-mm-dd hh:mm:ss
	    * @param @param filepath
	    * @param @return
	    * @param @throws IOException    参数
	    * @return List<Map<Integer,Object>>    返回类型
	    * @throws
	 */
	public static List<Map<Integer, Object>> readCsv(String filepath) throws IOException{
		List<Map<Integer, Object>> list = new ArrayList<Map<Integer, Object>>();
		FileInputStream fis = new FileInputStream(filepath); 
		//读取CSV文件需指定编码格式为GBK
        InputStreamReader isr = new InputStreamReader(fis,"GBK");
        BufferedReader br = new BufferedReader(isr);
        CSVReader csvReader = new CSVReader(br);  
        List<String[]> listTemp = csvReader.readAll();
        Map<Integer,Object> cellValue = new HashMap<Integer, Object>();
        for (int i = 1; i < listTemp.size(); i++) {
        	String[] rowdata = listTemp.get(i);
        	int j = 0;
            for(String cell : rowdata) { 
                if(null != cell && !cell.equals("")){
                	cellValue.put(j, cell);
                	j++;
                } 
            } 
            list.add(cellValue);
		}
        csvReader.close();
		return list;
	}
	
	/**
	 * 
	    * @Title: buildDate
	    * @Description: 处理读取Excel自定义格式日期方法，xls、xlsx
	    * @param @param obj
	    * @param @param suffixName
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
	public static String buildDate(Object obj,String suffixName) {
		String result = new String();
		if ("xls".equalsIgnoreCase(suffixName)) {
			HSSFCell cell = (HSSFCell)obj;
	        switch (cell.getCellType()) {
	        case HSSFCell.CELL_TYPE_NUMERIC:
	            if (cell.getCellStyle().getDataFormat() == 176) {
	                // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
	                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	                double value = cell.getNumericCellValue();
	                Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);
	                result = sdf.format(date);
	            } else {
	                double value = cell.getNumericCellValue();
	                CellStyle style = cell.getCellStyle();
	                DecimalFormat format = new DecimalFormat();
	                String temp = style.getDataFormatString();
	                // 单元格设置成常规
	                if (temp.equals("General")) {
	                    format.applyPattern("#");
	                }
	                result = format.format(value);
	            }
	            break;
	        case HSSFCell.CELL_TYPE_STRING:// String类型
	            result = cell.getStringCellValue();
	            break;
	        default:
	            result = "";
	            break;
	        }
		}else if("xlsx".equalsIgnoreCase(suffixName)){
			XSSFCell cell = (XSSFCell)obj;
	        switch (cell.getCellType()) {
	        case XSSFCell.CELL_TYPE_NUMERIC:
	            if (cell.getCellStyle().getDataFormat() == 176) {
	                // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
	                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	                double value = cell.getNumericCellValue();
	                Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);
	                result = sdf.format(date);
	            } else {
	                double value = cell.getNumericCellValue();
	                CellStyle style = cell.getCellStyle();
	                DecimalFormat format = new DecimalFormat();
	                String temp = style.getDataFormatString();
	                // 单元格设置成常规
	                if (temp.equals("General")) {
	                    format.applyPattern("#");
	                }
	                result = format.format(value);
	            }
	            break;
	        case XSSFCell.CELL_TYPE_STRING:// String类型
	            result = cell.getStringCellValue();
	            break;
	        default:
	            result = "";
	            break;
	        }
		}
		
        return result;
    }
}

package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

/**
 * 将Word文档转换成html字符串的工具类
 * 
 * @author MZULE
 * 
 */
public class Doc2Html {

	public static void main(String[] args) {
			 	File inputFile = new File("F:\\项目文件\\hssjdoc/阿斯达.doc");  
		        File outputFile = new File("F:\\项目文件\\hssjdoc/123.html");  
		        
		        OpenOfficeConnection con = new SocketOpenOfficeConnection(8100);  
		        try {  
		            con.connect();  
		        } catch (ConnectException e) {  
		            System.err.println("文件转换出错，请检查OpenOffice服务是否启动。");  
		            e.printStackTrace();  
		        }  
		        DocumentConverter converter = new OpenOfficeDocumentConverter(con);  
		        converter.convert(inputFile, outputFile);  
		        con.disconnect();  
	}

	 static String soffice_host = "127.0.0.1";  
	    static String soffice_port = "8100";  
	  
	    /** 
	     * 转换文件 
	     * @param fromFileInputStream 
	     * @throws FileNotFoundException  
	     * */  
	    public static String doc2Html(String inputFilePath, String outputFolder) throws FileNotFoundException {  
	        File inputFile = new File(inputFilePath);  
	        if (!inputFile.exists()) {  
	            throw new FileNotFoundException("要转换的文件不存在：" + inputFilePath);  
	        }  
	        File toFileFolder = new File(outputFolder);  
	        if (!toFileFolder.exists()) {  
	            toFileFolder.mkdirs();  
	        }  
	          
	        InputStream fromFileInputStream = new FileInputStream(inputFile);  
	          
//	      Date date = new Date();  
//	      SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");  
//	      String timesuffix = sdf.format(date);  
	          
//	      String htmFileName = inputFile.getName().substring(0, inputFile.getName().lastIndexOf(".")) + ".html";  
	//  
//	      File htmlOutputFile = new File(toFileFolder.toString() + File.separatorChar + htmFileName);  
//	      File docInputFile = new File(toFileFolder.toString() + File.separatorChar + inputFile.getName());  
	          
	        Date date = new Date();  
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");  
	        String timesuffix = sdf.format(date);  
	        String htmFileName = "htmlfile" + timesuffix + ".html";  
	        String docFileName = "docfile" + timesuffix + inputFilePath.substring(inputFilePath.lastIndexOf("."));  
	  
	        File htmlOutputFile = new File(toFileFolder.toString() + File.separatorChar + htmFileName);  
	        File docInputFile = new File(toFileFolder.toString() + File.separatorChar + docFileName);  
	          
	        /** 
	         * 由fromFileInputStream构建输入文件 
	         * */  
	        try {  
	            OutputStream os = new FileOutputStream(docInputFile);  
	            int bytesRead = 0;  
	            byte[] buffer = new byte[1024 * 8];  
	            while ((bytesRead = fromFileInputStream.read(buffer)) != -1) {  
	                os.write(buffer, 0, bytesRead);  
	            }  
	            os.close();  
	              
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }finally{  
	            try {  
	                fromFileInputStream.close();  
	            } catch (IOException e) {  
	                e.printStackTrace();  
	            }  
	        }  
	  
	        OpenOfficeConnection connection = new SocketOpenOfficeConnection(soffice_host, Integer.parseInt(soffice_port));  
	        try {  
	            connection.connect();  
	        } catch (ConnectException e) {  
	            System.err.println("文件转换出错，请检查OpenOffice服务是否启动。");  
	            e.printStackTrace();  
	        }  
	        // convert  
	        DocumentConverter converter = new OpenOfficeDocumentConverter(connection);  
	        converter.convert(docInputFile, htmlOutputFile);  
	        connection.disconnect();  
	  
	        // 转换完之后删除word文件  
	        // docInputFile.delete();  
	        return htmFileName;  
	    }  

}
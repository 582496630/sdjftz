package com.youotech.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * 对文本进行读写
 * @author sjc
 */
public class FileUtil {

	/**
	 * 对txt文本进行读取
	 * @return
	 */
	public static String readFile(String filePath){
		String text = null;
		StringBuffer sb = new StringBuffer();
		File file = new File(filePath);
		FileInputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try{
			is = new FileInputStream(file);
			isr = new InputStreamReader(is,"utf-8");
			br = new BufferedReader(isr);
			while((text=br.readLine())!=null){
				 sb.append(text);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{//关闭所有流
			if(br!=null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(isr!=null){
				try {
					isr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(is!=null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
	}
}

package com.youotech.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.mail.Address;

public class PropertiesUtil {

	public static String fileAddress = getFileAddress();

	public static Properties getProperties() {
		InputStream is = null;
		InputStreamReader isr = null;
		Properties properties = new Properties();
		try {
			is = PropertiesUtil.class.getClassLoader().getResourceAsStream("conf.properties");
			isr = new InputStreamReader(is, "UTF-8");
			properties.load(isr);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (isr != null) {
				try {
					isr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return properties;
	}

	public static String getFileAddress() {
		Properties properties = getProperties();// 文件绝对地址
		if (properties != null) {
			return properties.getProperty("file.address");
		} else {
			return null;
		}
	}
}

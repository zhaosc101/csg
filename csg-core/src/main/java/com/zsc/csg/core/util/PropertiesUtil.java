package com.zsc.csg.core.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropertiesUtil {

	public static String path = "/config/systerm.properties";
	
	public static String getProperties(String propKey) {
		InputStream url = PropertiesUtil.class
				.getResourceAsStream(path);
		Properties prop = new Properties();
		InputStream is = null;
		String filePath = "";
		try {
			is = url;
			prop.load(is);
			filePath = (String) prop.get(propKey);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != is) {
				try {
					is.close();
					is = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return filePath;
	}

	public static String getProperties(String path, String propKey) {
		InputStream url = PropertiesUtil.class
				.getResourceAsStream(path);
		Properties prop = new Properties();
		InputStream is = null;
		String filePath = "";
		try {
			is = url;
			prop.load(is);
			filePath = (String) prop.get(propKey);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != is) {
				try {
					is.close();
					is = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return filePath;
	}

	public static void addProperties(String key, String value) {

		Properties prop = getOldValues();

		prop.put(key, value);

		// 将所有键值对再写入文件
		saveProperties(prop, null);

	}

	public static void addProperties(String path, String key, String value) {

		Properties prop = getOldValues(path);

		prop.put(key, value);

		// 将所有键值对再写入文件
		saveProperties(path, prop, null);

	}

	public static void updateProperties(String path, String key, String value) {

		Properties prop = getOldValues(path);

		prop.put(key, value);

		// 将所有键值对再写入文件
		saveProperties(path, prop, null);
	}

	public static void updateProperties(String key, String value) {

		Properties prop = getOldValues(path);

		prop.put(key, value);

		// 将所有键值对再写入文件
		saveProperties(path, prop, null);

	}

	public static void deleteProperties(String path, String key) {

		Properties prop = getOldValues(path);

		prop.remove(key);

		// 将所有键值对再写入文件
		saveProperties(path, prop, null);
	}

	public static void deleteProperties(String key) {
		
		Properties prop = getOldValues(path);

		prop.remove(key);
		
		// 将所有键值对再写入文件
		saveProperties(path, prop, null);
	}

	private static Properties getOldValues(String path) {

		// 将文件中key，value先拿出来
		InputStream url = PropertiesUtil.class
				.getResourceAsStream(path);
		Properties prop = new Properties();
		
		try {
			prop.load(url);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return prop;
	}

	private static Properties getOldValues() {

		// 将文件中key，value先拿出来
		InputStream url = PropertiesUtil.class
				.getResourceAsStream(path);

		Properties prop = new Properties();
		try {
			prop.load(url);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return prop;
	}

	private static void saveProperties(String path, Properties prop,
			String filename) {
		try {
			OutputStream outputStream = new FileOutputStream(new File(
					PropertiesUtil.class
							.getResource(path).getFile()));
			prop.store(outputStream, filename);
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void saveProperties(Properties prop, String filename) {
		try {
			OutputStream outputStream = new FileOutputStream(new File(
					PropertiesUtil.class
							.getResource(path).getFile()));
			prop.store(outputStream, filename);
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

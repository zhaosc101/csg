package com.zsc.csg.test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;

public class FileIo {

	public static void main(String[] args) throws IOException {
		String file = "E:/io.txt";
		//String s = Buffered(file);
		//System.out.println("------------------");
		//System.out.println(s);
		stringReader(file);
	}
	public static String Buffered(String file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String s;
		StringBuffer sb = new StringBuffer();
		while((s = br.readLine())!=null) {
			sb.append(s+"\n");
			//System.out.println(s);
		}
		br.close();
		return sb.toString();
	}
	public static void stringReader(String file) throws IOException {
		StringReader sr = new StringReader(FileIo.Buffered(file));
		while(sr.read()!= -1){
			System.out.println((char)sr.read());
		}
		sr.close();
	}
	public void bufferedStream(String file) throws IOException {
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
	}
	public void fileIn(File file) {
		
		InputStream steam = null;
		try {
			steam = new FileInputStream(new File("E:/io.txt"));
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void fileOut(File file) {
		
		OutputStream out = null;
		String str = "hello!!";
		try {
			out = new FileOutputStream(file);
			byte[] b = str.getBytes();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}

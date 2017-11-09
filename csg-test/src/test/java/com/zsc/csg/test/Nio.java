package com.zsc.csg.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Nio {
	

	public static void main(String[] args) throws IOException {
		String file = "E:/io.txt";
		getChannel(file);
		
		copyFile(file, "E:/nio.txt");
	}
	public static void getChannel(String file) throws IOException {
		//write
		FileChannel fc = new FileOutputStream(file).getChannel();
		fc.write(ByteBuffer.wrap("abcdef".getBytes()));
		fc.close();
		//
		fc = new FileInputStream(file).getChannel();
		ByteBuffer buff = ByteBuffer.allocate(1024);
		fc.read(buff);
		buff.flip();
		while(buff.hasRemaining()) {
			System.out.println((char)buff.get());
		}
	}
	public static void copyFile(String ofile,String nfile) throws IOException {
		FileChannel f = new FileInputStream(ofile).getChannel();
		FileChannel f2 = new FileOutputStream(nfile).getChannel();
		ByteBuffer bf =ByteBuffer.allocate(1024);
		while(f.read(bf)!=-1){
			bf.flip();
			f2.write(bf);
			bf.clear();
		}
	}
	public static void copyFileForTranster(String ofile,String nfile) throws IOException {
		FileChannel f = new FileInputStream(ofile).getChannel();
		FileChannel f2 = new FileOutputStream(nfile).getChannel();
		f.transferTo(0, 1024, f2);
	}
}

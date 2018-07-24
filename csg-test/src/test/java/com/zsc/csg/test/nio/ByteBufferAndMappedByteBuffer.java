package com.zsc.csg.test.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.AccessController;
import java.security.PrivilegedAction;

public class ByteBufferAndMappedByteBuffer {

	private static final String path ="D:\\jiexi\\cardBill.json.tar(12.15-3.20)\\cardBill.json.tar(12.15-3.20)";
	private static final String Filesrc ="D:\\jiexi\\cardBill.json.tar(12.15-3.20)";
	private static final String credsrc ="D:\\jiexi";
	
	public static void method1() {
		RandomAccessFile aFile = null;
		FileChannel fc = null;
		try {
			aFile = new RandomAccessFile(path, "rw");
			fc = aFile.getChannel();

			long timeBegin = System.currentTimeMillis();
			ByteBuffer buff = ByteBuffer.allocate((int) aFile.length());
			buff.clear();
			fc.read(buff);
			// System.out.println((char)buff.get((int)(aFile.length()/2-1)));
			// System.out.println((char)buff.get((int)(aFile.length()/2)));
			// System.out.println((char)buff.get((int)(aFile.length()/2)+1));
			long timeEnd = System.currentTimeMillis();
			System.out.println("Read time: " + (timeEnd - timeBegin) + "ms");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (aFile != null) {
					aFile.close();
				}
				if (fc != null) {
					fc.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void method2() {
		RandomAccessFile aFile = null;
		FileChannel fc = null;
		try {
			aFile = new RandomAccessFile(path, "rw");
			fc = aFile.getChannel();
			long timeBegin = System.currentTimeMillis();
			MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_ONLY, 0, aFile.length());
			// System.out.println((char)mbb.get((int)(aFile.length()/2-1)));
			// System.out.println((char)mbb.get((int)(aFile.length()/2)));
			// System.out.println((char)mbb.get((int)(aFile.length()/2)+1));
			long timeEnd = System.currentTimeMillis();
			System.out.println("Read time: " + (timeEnd - timeBegin) + "ms");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (aFile != null) {
					aFile.close();
				}
				if (fc != null) {
					fc.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws IOException {
//		method1();
//		method2();
		
		copyFile("test.json", Filesrc, credsrc);
	}
	/**
	 * 文件复制
	 * 
	 * @param filename
	 * @param srcpath
	 * @param destpath
	 * @throws IOException
	 */
	public static void copyFile(String filename, String srcpath, String destpath) throws IOException {
		File source = new File(srcpath + "/" + filename);
		File dest = new File(destpath + "/" + filename);
		FileChannel in = null, out = null;
		try {
			System.err.println("time star"+System.currentTimeMillis());
			in = new FileInputStream(source).getChannel();
			out = new FileOutputStream(dest).getChannel();
			long size = in.size();
			MappedByteBuffer buf = in.map(FileChannel.MapMode.READ_ONLY, 0, size);
			out.write(buf);
			in.close();
			out.close();
			
			clean(buf);//操作
			
			boolean delete = source.delete();// 文件复制完成后，删除源文件
			System.err.println(delete);
			System.err.println("time end"+System.currentTimeMillis());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			in.close();
			out.close();
		}
	}

	/**
	 * 关闭
	 * @param buffer
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static void clean(final Object buffer) throws Exception {
		AccessController.doPrivileged(new PrivilegedAction() {
			public Object run() {
				try {
					Method getCleanerMethod = buffer.getClass().getMethod("cleaner", new Class[0]);
					getCleanerMethod.setAccessible(true);
					sun.misc.Cleaner cleaner = (sun.misc.Cleaner) getCleanerMethod.invoke(buffer, new Object[0]);
					cleaner.clean();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		});

	}
}

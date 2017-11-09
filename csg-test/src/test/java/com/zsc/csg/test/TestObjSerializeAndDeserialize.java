package com.zsc.csg.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.zsc.csg.core.orm.usr.domain.UsrInfo;

public class TestObjSerializeAndDeserialize {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		serialized();
		reback();
	}
	private static void serialized() throws IOException {
		UsrInfo p = new UsrInfo();
		p.setPhoneNo("12931001");
		p.setUsrNm("zhaosc");
		p.setVersion(12);
		ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(new File("E:/id.txt")));
		oo.writeObject(p);
		System.out.println("-------");
		oo.close();
				
	}
	
	private static void reback() throws IOException, ClassNotFoundException{
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("E:/id.txt")));
		UsrInfo p = (UsrInfo) ois.readObject();
		System.out.println(p);
		
 	}
}

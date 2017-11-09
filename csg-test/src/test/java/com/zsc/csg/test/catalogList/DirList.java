package com.zsc.csg.test.catalogList;

import java.io.File;
import java.util.Arrays;

public class DirList {

	public static void main(String[] args) {
		File f= new File(".");
		String[] list;
		if(args.length==0){
			list = f.list();
		}else {
			list = f.list(new DirFilter(args[0]));
		}
		Arrays.sort(list,String.CASE_INSENSITIVE_ORDER);
		for (String dirItem : list) {
			System.out.println(dirItem);
		}
	}
}

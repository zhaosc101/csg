package com.zsc.csg.test.catalogList;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

public class DirFilter implements FilenameFilter{
	
	private Pattern pattern;
	public DirFilter(String regex) {
		pattern = pattern.compile(regex);
	}

	@Override
	public boolean accept(File dir, String name) {
		// TODO Auto-generated method stub
		return pattern.matcher(name).matches();
	}

}

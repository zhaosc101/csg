package com.zsc.csg.test;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.zsc.csg.core.orm.usr.domain.UsrInfo;
import com.zsc.csg.core.util.BeanCopyUtil;

public class Test {

	public static void main(String[] args) {
		UsrInfo u = new UsrInfo();
		u.setUsrId("1111");
		u.setPhoneNo("132022");
		u.setTranDt("111111221");
		UsrInfo u1 = new UsrInfo();
		BeanCopyUtil.copy(u, u1);
		System.out.println(u1.toString());
		////dadsa
		tes();
	}
	public static void tes() {
		Set<String> s1 = new HashSet<String>();
		Collections.addAll(s1, "A B C D".split(" "));
		
		System.out.println(s1);
		System.out.println(s1);
	}
}

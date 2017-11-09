package com.zsc.csg.core.orm.sys.domain;

import com.suixingpay.common.core.orm.domain.Domain;
import com.zsc.csg.core.orm.sys.po.SysUsrPo;
/**
 * Description: 在线业务管理平台用户表 Domain
 * Copyright: ©2017 zsc. All rights reserved.
 * @author zhao_sc@suixingpay.com
 * Created on: 2017-10-26 14:14:10
 */
public class SysUsr extends SysUsrPo implements Domain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void onSave(){
		//this.id=System.currentTimeMillis();
	}
	
	public String validate(){
		StringBuffer sb = new StringBuffer();
			
		if(this.getUuid() == null)
			sb.append("主键不能为空！");
		
		if(this.getUuid().getBytes().length > 32)
			sb.append("主键宽度不能超过32个字符！");
			
		if(this.getName().getBytes().length > 50)
			sb.append("用户名宽度不能超过50个字符！");
			
		if(this.getLoginName().getBytes().length > 100)
			sb.append("用户登陆名宽度不能超过100个字符！");
			
		if(this.getLoginPwd().getBytes().length > 100)
			sb.append("用户登陆密码宽度不能超过100个字符！");
			
		if(this.getCreateUser().getBytes().length > 100)
			sb.append("创建人宽度不能超过100个字符！");
			
		if(this.getUpdateUser().getBytes().length > 100)
			sb.append("修改人宽度不能超过100个字符！");
			
		String msg = sb.toString() ;
		if (msg.length() == 0)
			  return null ;

		return  msg;
	}
	
	 /**
	  * Returns a string representation of the object.
	  */
	 public String toString(){
	 	String sep = "; ";
	 	StringBuffer sb = new StringBuffer();
	 	sb.append("SysUsrPo").append(sep);
		sb.append("uuid").append(" = ").append(this.getUuid()).append(sep);
		sb.append("name").append(" = ").append(this.getName()).append(sep);
		sb.append("loginName").append(" = ").append(this.getLoginName()).append(sep);
		sb.append("loginPwd").append(" = ").append(this.getLoginPwd()).append(sep);
		sb.append("createtime").append(" = ").append(this.getCreatetime()).append(sep);
		sb.append("state").append(" = ").append(this.getState()).append(sep);
		sb.append("createUser").append(" = ").append(this.getCreateUser()).append(sep);
		sb.append("updateUser").append(" = ").append(this.getUpdateUser()).append(sep);
		sb.append("updatetime").append(" = ").append(this.getUpdatetime()).append(sep);
		return sb.toString();
	 
	 }
	 
	
}
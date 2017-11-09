package com.zsc.csg.core.orm.usr.domain;

import com.suixingpay.common.core.orm.domain.Domain;
import com.zsc.csg.core.orm.usr.po.UsrInfoPo;
/**
 * Description: 用户基本信息 Domain
 * Copyright: ©2017 zsc. All rights reserved.
 * @author zhao_sc@suixingpay.com
 * Created on: 2017-10-25 10:37:31
 */
public class UsrInfo extends UsrInfoPo implements Domain {

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
			sb.append("UUID不能为空！");
		
		if(this.getUuid().getBytes().length > 32)
			sb.append("UUID宽度不能超过32个字符！");
			
		if(this.getUsrId().getBytes().length > 15)
			sb.append("用户编号宽度不能超过15个字符！");
			
		if(this.getUsrNm().getBytes().length > 30)
			sb.append("用户姓名宽度不能超过30个字符！");
			
		if(this.getCreTyp().getBytes().length > 2)
			sb.append("证件类型 00身份证；01护照；02营业执照；宽度不能超过2个字符！");
			
		if(this.getCreNo().getBytes().length > 30)
			sb.append("用户证件号宽度不能超过30个字符！");
			
		if(this.getGender().getBytes().length > 2)
			sb.append("性别 00男；01女；02其他宽度不能超过2个字符！");
			
		if(this.getPhoneNo().getBytes().length > 15)
			sb.append("移动电话宽度不能超过15个字符！");
			
		if(this.getUsrSts().getBytes().length > 2)
			sb.append("用户状态 00正常；01逾期；02冻结；03暂停放款宽度不能超过2个字符！");
			
		if(this.getRmk().getBytes().length > 120)
			sb.append("备注宽度不能超过120个字符！");
			
		if(this.getTranDt().getBytes().length > 8)
			sb.append("创建日期宽度不能超过8个字符！");
			
		if(this.getCteOp().getBytes().length > 10)
			sb.append("创建人宽度不能超过10个字符！");
			
		if(this.getUptOp().getBytes().length > 10)
			sb.append("最后修改人宽度不能超过10个字符！");
			
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
	 	sb.append("UsrInfoPo").append(sep);
		sb.append("uuid").append(" = ").append(this.getUuid()).append(sep);
		sb.append("usrId").append(" = ").append(this.getUsrId()).append(sep);
		sb.append("usrNm").append(" = ").append(this.getUsrNm()).append(sep);
		sb.append("creTyp").append(" = ").append(this.getCreTyp()).append(sep);
		sb.append("creNo").append(" = ").append(this.getCreNo()).append(sep);
		sb.append("gender").append(" = ").append(this.getGender()).append(sep);
		sb.append("phoneNo").append(" = ").append(this.getPhoneNo()).append(sep);
		sb.append("usrSts").append(" = ").append(this.getUsrSts()).append(sep);
		sb.append("rmk").append(" = ").append(this.getRmk()).append(sep);
		sb.append("tranDt").append(" = ").append(this.getTranDt()).append(sep);
		sb.append("cteDt").append(" = ").append(this.getCteDt()).append(sep);
		sb.append("cteOp").append(" = ").append(this.getCteOp()).append(sep);
		sb.append("uptDt").append(" = ").append(this.getUptDt()).append(sep);
		sb.append("uptOp").append(" = ").append(this.getUptOp()).append(sep);
		return sb.toString();
	 
	 }
	 
	
}
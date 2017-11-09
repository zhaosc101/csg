package com.zsc.csg.core.orm.usr.po;

import com.zsc.csg.core.orm.AbstractPo;

/**
 * Description: 用户基本信息 Po
 * Copyright: ©2017 zsc. All rights reserved.
 * @author zhao_sc@suixingpay.com
 * Created on: 2017-10-25 10:37:31
 */
public class UsrInfoPo extends AbstractPo{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2146498553596777044L;
	// UUID
	private	String	uuid;
	// 用户编号
	private	String	usrId;
	// 用户姓名
	private	String	usrNm;
	// 证件类型 00身份证；01护照；02营业执照；
	private	String	creTyp;
	// 用户证件号
	private	String	creNo;
	// 性别 00男；01女；02其他
	private	String	gender;
	// 移动电话
	private	String	phoneNo;
	// 用户状态 00正常；01逾期；02冻结；03暂停放款
	private	String	usrSts;
	// 备注
	private	String	rmk;
	// 创建日期
	private	String	tranDt;
	// 创建时间
	private	java.util.Date	cteDt;
	// 创建人
	private	String	cteOp;
	// 最后修改时间
	private	java.util.Date	uptDt;
	// 最后修改人
	private	String	uptOp;

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	/**
	 * UUID
	 * @return
	 */
	public String getUuid() {
		return uuid;
	}

	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}
	/**
	 * 用户编号
	 * @return
	 */
	public String getUsrId() {
		return usrId;
	}

	public void setUsrNm(String usrNm) {
		this.usrNm = usrNm;
	}
	/**
	 * 用户姓名
	 * @return
	 */
	public String getUsrNm() {
		return usrNm;
	}

	public void setCreTyp(String creTyp) {
		this.creTyp = creTyp;
	}
	/**
	 * 证件类型 00身份证；01护照；02营业执照；
	 * @return
	 */
	public String getCreTyp() {
		return creTyp;
	}

	public void setCreNo(String creNo) {
		this.creNo = creNo;
	}
	/**
	 * 用户证件号
	 * @return
	 */
	public String getCreNo() {
		return creNo;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * 性别 00男；01女；02其他
	 * @return
	 */
	public String getGender() {
		return gender;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	/**
	 * 移动电话
	 * @return
	 */
	public String getPhoneNo() {
		return phoneNo;
	}

	public void setUsrSts(String usrSts) {
		this.usrSts = usrSts;
	}
	/**
	 * 用户状态 00正常；01逾期；02冻结；03暂停放款
	 * @return
	 */
	public String getUsrSts() {
		return usrSts;
	}

	public void setRmk(String rmk) {
		this.rmk = rmk;
	}
	/**
	 * 备注
	 * @return
	 */
	public String getRmk() {
		return rmk;
	}

	public void setTranDt(String tranDt) {
		this.tranDt = tranDt;
	}
	/**
	 * 创建日期
	 * @return
	 */
	public String getTranDt() {
		return tranDt;
	}

	public void setCteDt(java.util.Date cteDt) {
		this.cteDt = cteDt;
	}
	/**
	 * 创建时间
	 * @return
	 */
	public java.util.Date getCteDt() {
		return cteDt;
	}

	public void setCteOp(String cteOp) {
		this.cteOp = cteOp;
	}
	/**
	 * 创建人
	 * @return
	 */
	public String getCteOp() {
		return cteOp;
	}

	public void setUptDt(java.util.Date uptDt) {
		this.uptDt = uptDt;
	}
	/**
	 * 最后修改时间
	 * @return
	 */
	public java.util.Date getUptDt() {
		return uptDt;
	}

	public void setUptOp(String uptOp) {
		this.uptOp = uptOp;
	}
	/**
	 * 最后修改人
	 * @return
	 */
	public String getUptOp() {
		return uptOp;
	}
}
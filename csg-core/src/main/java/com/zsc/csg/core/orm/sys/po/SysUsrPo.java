package com.zsc.csg.core.orm.sys.po;

import com.zsc.csg.core.orm.AbstractPo;;
/**
 * Description: 在线业务管理平台用户表 Po
 * Copyright: ©2017 zsc. All rights reserved.
 * @author zhao_sc@suixingpay.com
 * Created on: 2017-10-26 14:14:10
 */
public class SysUsrPo extends AbstractPo{
	// 主键
	private	String	uuid;
	// 用户名
	private	String	name;
	// 用户登陆名
	private	String	loginName;
	// 用户登陆密码
	private	String	loginPwd;
	// 创建时间
	private	java.util.Date	createtime;
	// 1：可用  0 ：停用
	private	Integer	state;
	// 创建人
	private	String	createUser;
	// 修改人
	private	String	updateUser;
	// 修改时间
	private	java.util.Date	updatetime;

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	/**
	 * 主键
	 * @return
	 */
	public String getUuid() {
		return uuid;
	}

	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 用户名
	 * @return
	 */
	public String getName() {
		return name;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	/**
	 * 用户登陆名
	 * @return
	 */
	public String getLoginName() {
		return loginName;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}
	/**
	 * 用户登陆密码
	 * @return
	 */
	public String getLoginPwd() {
		return loginPwd;
	}

	public void setCreatetime(java.util.Date createtime) {
		this.createtime = createtime;
	}
	/**
	 * 创建时间
	 * @return
	 */
	public java.util.Date getCreatetime() {
		return createtime;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	/**
	 * 1：可用  0 ：停用
	 * @return
	 */
	public Integer getState() {
		return state;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	/**
	 * 创建人
	 * @return
	 */
	public String getCreateUser() {
		return createUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	/**
	 * 修改人
	 * @return
	 */
	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdatetime(java.util.Date updatetime) {
		this.updatetime = updatetime;
	}
	/**
	 * 修改时间
	 * @return
	 */
	public java.util.Date getUpdatetime() {
		return updatetime;
	}
}
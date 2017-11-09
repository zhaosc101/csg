package com.zsc.csg.core.orm;

import com.suixingpay.common.core.orm.domain.Domain;

public class AbstractPo implements Domain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 创建时间
	protected java.util.Date createTime;
	// 创建人
	protected String createUser;
	// 更新时间
	protected java.util.Date updateTime;
	// 更新人
	protected String updateUser;
	// 版本号
	protected Integer version;

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public java.util.Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}

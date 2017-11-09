package com.zsc.csg.core.orm.sys.service;

import com.suixingpay.common.core.service.GenericService;
import com.zsc.csg.core.orm.sys.domain.SysUsr;
/**
 * Description: 在线业务管理平台用户表 Service
 * Copyright: ©2017 zsc. All rights reserved.
 * @author zhao_sc@suixingpay.com
 * Created on: 2017-10-26 14:14:10
 */
public interface SysUsrService extends GenericService<SysUsr,String>{
	int updateByPrimaryKeySelective(SysUsr t);
	
    int insertSelective(SysUsr t);
}
package com.zsc.csg.core.orm.sys.mapper;

import com.suixingpay.common.core.orm.dao.GenericDao;
import com.zsc.csg.core.orm.sys.domain.SysUsr;
/**
 * Description: 在线业务管理平台用户表 Mapper
 * Copyright: ©2017 zsc. All rights reserved.
 * @author zhao_sc@suixingpay.com
 * Created on: 2017-10-26 14:14:10
 */

public interface SysUsrMapper extends GenericDao<SysUsr,String> {
	int updateByPrimaryKeySelective(SysUsr t);
    
    int insertSelective(SysUsr t);
}
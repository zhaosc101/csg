package com.zsc.csg.core.orm.usr.service;

import com.zsc.csg.core.orm.usr.domain.UsrInfo;
/**
 * Description: 用户基本信息 Service
 * Copyright: ©2017 zsc. All rights reserved.
 * @author zhao_sc@suixingpay.com
 * Created on: 2017-10-25 10:37:31
 */
public interface UsrInfoService {
	int updateByPrimaryKeySelective(UsrInfo t);
	
    int insertSelective(UsrInfo t);
}
package com.zsc.csg.core.orm.usr.mapper;

import java.util.List;

import com.zsc.csg.core.orm.usr.domain.UsrInfo;
/**
 * Description: 用户基本信息 Mapper
 * Copyright: ©2017 zsc. All rights reserved.
 * @author zhao_sc@suixingpay.com
 * Created on: 2017-10-25 10:37:31
 */
public interface UsrInfoMapper {
	int updateByPrimaryKeySelective(UsrInfo t);
	List<UsrInfo> findAll();
    int insertSelective(UsrInfo t);
}
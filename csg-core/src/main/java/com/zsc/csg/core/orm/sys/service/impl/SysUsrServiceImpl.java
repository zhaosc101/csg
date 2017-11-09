package com.zsc.csg.core.orm.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suixingpay.common.core.orm.dao.GenericDao;
import com.suixingpay.common.core.service.AbstractService;
import com.zsc.csg.core.orm.sys.domain.SysUsr;
import com.zsc.csg.core.orm.sys.mapper.SysUsrMapper;
import com.zsc.csg.core.orm.sys.service.SysUsrService;
@Service
public class SysUsrServiceImpl extends AbstractService<SysUsr, String> implements SysUsrService{

	@Autowired
	private SysUsrMapper sysUsrMapper;
	@Override
	public int updateByPrimaryKeySelective(SysUsr t) {
		return sysUsrMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int insertSelective(SysUsr t) {
		return sysUsrMapper.insertSelective(t);
	}

	@Override
	protected GenericDao<SysUsr, String> getDao() {
		return sysUsrMapper;
	}

}

<#import "function.ftl" as func>
<#assign class=model.variables.class>
package com.${vars.company}.mcs.admin.${vars.project}.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suixingpay.common.core.service.AbstractService;
import com.suixingpay.common.core.orm.dao.GenericDao;
import com.${vars.company}.mcs.core.orm.${vars.project}.domain.${class?cap_first};
import com.${vars.company}.mcs.core.orm.${vars.project}.mapper.${class?cap_first}Mapper;
import com.${vars.company}.mcs.admin.${vars.project}.service.${class?cap_first}Service;
/**
 * Description: ${model.tabComment} Service
 <#if vars.company??>
 * Copyright: ©${date?string("yyyy")} ${vars.company}. All rights reserved.
 </#if>
 <#if vars.developer??>
 * @author ${vars.developer}
 </#if>
 * Created on: ${date?string("yyyy-MM-dd HH:mm:ss")}
 */
@Service
public class ${class}ServiceImpl extends AbstractService<${class}, String> implements ${class}Service{
	@Autowired
	private ${class}Mapper ${class?uncap_first}Mapper;
	
	@Override
	protected GenericDao<${class}, String> getDao() {
		// TODO Auto-generated method stub
		return ${class?uncap_first}Mapper;
	}
	
	@Override
	public int updateByPrimaryKeySelective(${class} t) {
		return ${class?uncap_first}Mapper.updateByPrimaryKeySelective(t);
	}
	
	@Override
	public int insertSelective(${class} t) {
		return ${class?uncap_first}Mapper.insertSelective(t);
	}
}
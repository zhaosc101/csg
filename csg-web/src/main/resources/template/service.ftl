<#import "function.ftl" as func>
<#assign class=model.variables.class>
package com.${vars.company}.mcs.admin.${vars.project}.service;

import com.${vars.company}.mcs.core.orm.${vars.project}.domain.${class?cap_first};
import com.suixingpay.common.core.service.GenericService;
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
public interface ${class}Service extends GenericService<${class?cap_first},String>{
	int updateByPrimaryKeySelective(${class} t);
	
    int insertSelective(${class} t);
}
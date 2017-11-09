<#import "function.ftl" as func>
<#assign class=model.variables.class>
package com.${vars.company}.mcs.core.orm.${vars.project}.mapper;

import com.${vars.company}.mcs.core.orm.${vars.project}.domain.${class?cap_first};
import com.suixingpay.common.core.orm.dao.GenericDao;
/**
 * Description: ${model.tabComment} Mapper
 <#if vars.company??>
 * Copyright: ©${date?string("yyyy")} ${vars.company}. All rights reserved.
 </#if>
 <#if vars.developer??>
 * @author ${vars.developer}
 </#if>
 * Created on: ${date?string("yyyy-MM-dd HH:mm:ss")}
 */
public interface ${class?cap_first}Mapper extends GenericDao<${class?cap_first},String> {
	int updateByPrimaryKeySelective(${class?cap_first} t);
    
    int insertSelective(${class?cap_first} t);
}
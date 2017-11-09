<#import "function.ftl" as func>
<#assign class=model.variables.class>
<#assign colList=model.columnList>
package com.${vars.company}.mcs.core.orm.${vars.project}.domain;

import java.util.*;

import com.${vars.company}.mcs.core.orm.${vars.project}.po.${class}Po;
import com.suixingpay.common.core.orm.domain.Domain;
/**
 * Description: ${model.tabComment} Domain
 <#if vars.company??>
 * Copyright: ©${date?string("yyyy")} ${vars.company}. All rights reserved.
 </#if>
 <#if vars.developer??>
 * @author ${vars.developer}
 </#if>
 * Created on: ${date?string("yyyy-MM-dd HH:mm:ss")}
 */
public class ${class} extends ${class}Po implements Domain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void onSave(){
		//this.id=System.currentTimeMillis();
	}
	
	public String validate(){
		StringBuffer sb = new StringBuffer();
		<#list colList as col>
			<#if (col.isNotNull) >
			
		if(this.get${col.humpColumnName}() == null)
			sb.append("${col.comment}不能为空！");
			</#if>
		</#list>
		
		<#list colList as col>
			<#if (col.colType == "String") >
		if(this.get${col.humpColumnName}().getBytes().length > ${col.length})
			sb.append("${col.comment}宽度不能超过${col.length}个字符！");
			
			</#if>
		</#list>
		String msg = sb.toString() ;
		if (msg.length() == 0)
			  return null ;

		return  msg;
	}
	
	 /**
	  * Returns a string representation of the object.
	  */
	 public String toString(){
	 	String sep = "; ";
	 	StringBuffer sb = new StringBuffer();
	 	sb.append("${class}Po").append(sep);
	 	<#list colList as col>
		sb.append("${col.humpColumnName?uncap_first}").append(" = ").append(this.get${col.humpColumnName}()).append(sep);
		</#list>
		return sb.toString();
	 
	 }
	 
	
}
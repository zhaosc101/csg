<#import "function.ftl" as func>
<#assign class=model.variables.class>
package com.${vars.company}.mcs.admin.${vars.project}.controller;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.${vars.company}.mcs.admin.common.controller.AbstractController;
import com.${vars.company}.mcs.admin.common.utils.UUIDGenerate;
import com.${vars.company}.common.web.annotation.Action;
import com.${vars.company}.common.web.bean.ResultMessage;
import com.${vars.company}.common.web.utils.RequestHelper;
import com.${vars.company}.mcs.core.orm.${vars.project}.domain.${class};
import com.${vars.company}.mcs.admin.${vars.project}.service.impl.${class}ServiceImpl;

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
@Controller
@RequestMapping("/${vars.project}/${class?uncap_first}")
public class ${class}Controller extends AbstractController {

	@Autowired
	private ${class}ServiceImpl ${class?uncap_first}ServiceImpl;


	@RequestMapping("/list")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response, ${class} ${class?uncap_first}) {
		PageHelper.startPage(RequestHelper.getInt("pageNum", PAGENUM), RequestHelper.getInt("pageSize", PAGESIZE));
		List<${class}> ${class?uncap_first}s = ${class?uncap_first}ServiceImpl.find(${class?uncap_first});
		ModelAndView mav = getAutoView().addObject("${class?uncap_first}s", ${class?uncap_first}s).addObject("${class?uncap_first}",${class?uncap_first});
		return mav;
	}
	
	@RequestMapping("/detail")
	@Action(description="查看消息详情")
	ModelAndView detail() throws IOException {
		String uuid=RequestHelper.getString("uuid");
		 ${class} ${class?uncap_first} = ${class?uncap_first}ServiceImpl.findOne(uuid);
		return getAutoView().addObject("${class?uncap_first}", ${class?uncap_first});
	}
	

	@RequestMapping("/save")
	@Action(description="保存消息")
	void save(${class} ${class?uncap_first}, HttpServletResponse response) throws IOException {
		${class?uncap_first}.setUuid(UUIDGenerate.randomUUID());
		${class?uncap_first}ServiceImpl.insertSelective(${class?uncap_first});
		writeResultMessage(response.getWriter(), "添加成功", ResultMessage.Success);
	}
	
	@RequestMapping("/add")
	@Action(description="添加消息")
	ModelAndView add() throws IOException {
		return getAutoView();
	}
	
	@RequestMapping("/edit")
	@Action(description="编辑消息")
	ModelAndView edit() throws IOException {
		String uuid=RequestHelper.getString("uuid");
		${class} ${class?uncap_first} = ${class?uncap_first}ServiceImpl.findOne(uuid);		
		return getAutoView().addObject("${class?uncap_first}", ${class?uncap_first});
	}
	
	@RequestMapping("/update")
	@Action(description="更新消息")
	void update(${class} ${class?uncap_first},HttpServletResponse response) throws IOException {	
		
		${class?uncap_first}ServiceImpl.updateByPrimaryKeySelective(${class?uncap_first});		
		writeResultMessage(response.getWriter(), "更新成功", ResultMessage.Success);
	}
	
	@RequestMapping("/delete")
	@Action(description="删除")
	void delete(HttpServletResponse response) throws IOException {
		String uuid=RequestHelper.getString("uuid");
		${class?uncap_first}ServiceImpl.delete(uuid);
		writeResultMessage(response.getWriter(), "删除成功", ResultMessage.Success);
	}
	
} 

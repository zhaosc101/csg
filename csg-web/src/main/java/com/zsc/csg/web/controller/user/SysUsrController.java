package com.zsc.csg.web.controller.user;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.suixingpay.common.web.annotation.Action;
import com.suixingpay.common.web.bean.ResultMessage;
import com.suixingpay.common.web.utils.RequestHelper;
import com.zsc.csg.core.orm.sys.domain.SysUsr;
import com.zsc.csg.core.orm.sys.service.impl.SysUsrServiceImpl;
import com.zsc.csg.core.util.UUIDUtil;
import com.zsc.csg.web.controller.AbstractController;
import com.zsc.csg.web.security.IgnoreSecurity;

/**
 * Description: 在线业务管理平台用户表 Service
 * Copyright: ©2017 zsc. All rights reserved.
 * @author zhao_sc@suixingpay.com
 * Created on: 2017-11-03 16:55:12
 */
@Controller
@RequestMapping("/sys/sysUsr")
public class SysUsrController extends AbstractController {

	@Autowired
	private SysUsrServiceImpl sysUsrServiceImpl;


	@RequestMapping("/list")
	@IgnoreSecurity
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response, SysUsr sysUsr) {
		PageHelper.startPage(RequestHelper.getInt("pageNum", PAGENUM), RequestHelper.getInt("pageSize", PAGESIZE));
		List<SysUsr> sysUsrs = sysUsrServiceImpl.find(sysUsr);
		ModelAndView mav = getAutoView().addObject("sysUsrs", sysUsrs).addObject("sysUsr",sysUsr);
		return mav;
	}
	
	@RequestMapping("/detail")
	@Action(description="查看消息详情")
	ModelAndView detail() throws IOException {
		String uuid=RequestHelper.getString("uuid");
		 SysUsr sysUsr = sysUsrServiceImpl.findOne(uuid);
		return getAutoView().addObject("sysUsr", sysUsr);
	}
	

	@RequestMapping("/save")
	@Action(description="保存消息")
	void save(SysUsr sysUsr, HttpServletResponse response) throws IOException {
		sysUsr.setUuid(UUIDUtil.randomUUID());
		sysUsrServiceImpl.insertSelective(sysUsr);
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
		SysUsr sysUsr = sysUsrServiceImpl.findOne(uuid);		
		return getAutoView().addObject("sysUsr", sysUsr);
	}
	
	@RequestMapping("/update")
	@Action(description="更新消息")
	void update(SysUsr sysUsr,HttpServletResponse response) throws IOException {	
		
		sysUsrServiceImpl.updateByPrimaryKeySelective(sysUsr);		
		writeResultMessage(response.getWriter(), "更新成功", ResultMessage.Success);
	}
	
	@RequestMapping("/delete")
	@Action(description="删除")
	void delete(HttpServletResponse response) throws IOException {
		String uuid=RequestHelper.getString("uuid");
		sysUsrServiceImpl.delete(uuid);
		writeResultMessage(response.getWriter(), "删除成功", ResultMessage.Success);
	}
	
} 

package com.zsc.csg.web.controller.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zsc.csg.core.orm.usr.domain.UsrInfo;
import com.zsc.csg.core.orm.usr.mapper.UsrInfoMapper;

@Controller
@RequestMapping("/usr")
public class UsrController {

	@Autowired
	private UsrInfoMapper usrInfoService;
	
	@RequestMapping("/list")
	@ResponseBody
	public JSONObject showUsr(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tokenId = request.getHeader("tokenId");
		System.out.println(tokenId);
		List<UsrInfo> list = usrInfoService.findAll();
		JSONObject json = new JSONObject();
		json.put("usrList", list);
		return json;
	}
}

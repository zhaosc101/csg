package com.zsc.csg.web.controller.system;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.zsc.csg.core.orm.sys.domain.SysUsr;
import com.zsc.csg.core.orm.sys.mapper.SysUsrMapper;
import com.zsc.csg.core.redis.RedisClient;
import com.zsc.csg.core.util.StringUtils;
import com.zsc.csg.web.common.WebContext;
import com.zsc.csg.web.security.token.Token;
import com.zsc.csg.web.security.token.TokenManager;

@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private SysUsrMapper sysUsrService;
	
	@Autowired
	private TokenManager tokenManager;
	@Autowired
	private RedisClient redisClient;
	
	private static final String USERINFO = "USERINFO";

    private static long EXPIRE_TIME = 20 * 60 * 1000L;
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public ModelAndView loginCheck(SysUsr sysUsr) {
		//判断
		if(StringUtils.isEmpty(sysUsr.getLoginName())||StringUtils.isEmpty(sysUsr.getLoginPwd())) {
			return new ModelAndView("login");
		}
		List<SysUsr> list = sysUsrService.find(sysUsr);
		if(list.isEmpty()) {
			return new ModelAndView("login");
		}
		Token token = new Token();
		token.setUserName(list.get(0).getName());
		token.setUuid(list.get(0).getUuid());
		tokenManager.putToken(token);
		Map<String,Object> map = new HashMap<>();
		map.put("uuid", list.get(0).getUuid());
		map.put("loginTime", new Date().getTime());
		redisClient.setCacheForHash(token.getTokenId(),USERINFO, map);
        redisClient.setExpireTime(token.getTokenId(), EXPIRE_TIME);
        logger.info("----------------------登录成功------------------------");
		return new ModelAndView("home").addObject("tokenId",token.getTokenId());
	}
	
	@RequestMapping("/index")
	public String index() {
		return "login";
	}
	
	@RequestMapping("/logout")
	@ResponseBody
	public JSONObject logout() {
		redisClient.deleteCacheKeyForHash(WebContext.getTokenId(), "USERINFO");
        Token token = tokenManager.getToken(WebContext.getTokenId());
        tokenManager.removeToken(token.getTokenId());
        logger.info("----------------------登出成功------------------------");
        JSONObject json = new JSONObject();
        json.put("msg", "成功");
        json.put("code", "0000");
        return json;
	}
}

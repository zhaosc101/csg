package com.zsc.csg.web.controller.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zsc.csg.core.mq.MQProducer;
import com.zsc.csg.web.security.IgnoreSecurity;

@RestController
public class MQTestController {

	@Autowired
	private MQProducer mqProducer;
	
	public static final String queue_key = "*.zsc";
	@IgnoreSecurity
    @RequestMapping("/mq")
    public void send() {
    	Map<String,Object> msg = new HashMap<>();
        msg.put("data","hello,rabbmitmq!");
        mqProducer.sendDataToQueue(queue_key,msg);
	}
}

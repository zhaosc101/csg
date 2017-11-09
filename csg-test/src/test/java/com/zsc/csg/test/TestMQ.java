package com.zsc.csg.test;

import java.util.HashMap;
import java.util.Map;

import com.zsc.csg.core.mq.impl.MQProducerImpl;
public class TestMQ {


    public static final String queue_key = "test_queue";
    
    public static void main(String[] args) {
    	MQProducerImpl mqProducer = new MQProducerImpl();
    	Map<String,Object> msg = new HashMap<>();
        msg.put("data","hello,rabbmitmq!");
        mqProducer.sendDataToQueue(queue_key,msg);
	}
}

package com.zsc.csg.core.mq.impl;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zsc.csg.core.mq.MQProducer;
@Service
public class MQProducerImpl implements MQProducer{

	private final static Logger LOGGER = Logger.getLogger(MQProducerImpl.class);
	
	 @Autowired
	 private AmqpTemplate amqpTemplate;
	@Override
	public void sendDataToQueue(String queueKey, Object object) {
		try {
			amqpTemplate.convertAndSend(queueKey, object);
			
		} catch (Exception e) {
			LOGGER.error(e);
		}
	}

}

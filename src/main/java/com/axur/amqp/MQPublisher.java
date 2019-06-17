package com.axur.amqp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.axur.WhitelistApplication;
import com.axur.config.GlobalProperties;
import com.axur.model.RegexMatches;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class MQPublisher {
	
	private Logger logger = LoggerFactory.getLogger(MQPublisher.class);

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	public void publishMatch(RegexMatches match) {
		logger.debug("{} publishMatch().", WhitelistApplication.APP_LOG);
		
	    try {
	         String messageJson = objectMapper.writeValueAsString(match);
	         Message message = MessageBuilder
	                                .withBody(messageJson.getBytes())
	                                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
	                                .build();
	         this.rabbitTemplate.convertAndSend(GlobalProperties.RESPONSE_EXCHANGE, GlobalProperties.RESPONSE_ROUTING_KEY, message);
	         logger.debug("{} publishMatch(). Response Match published on Exchange = {} with Routing Key = {} at", WhitelistApplication.APP_LOG, GlobalProperties.RESPONSE_EXCHANGE, GlobalProperties.RESPONSE_ROUTING_KEY);
	     } catch (JsonProcessingException e) {
	         e.printStackTrace();
	     }
	}
	
}

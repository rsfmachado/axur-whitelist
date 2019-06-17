package com.axur.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GlobalProperties {
	
	public static String INSERTION_QUEUE;
	public static String VALIDATION_QUEUE;
	public static String NUMBER_OF_VALIDATION_CONSUMERS;
	public static String RESPONSE_EXCHANGE;
	public static String RESPONSE_ROUTING_KEY;
	public static String RABBITMQ_HOST;
	public static String RABBITMQ_PORT;
	public static String RABBITMQ_VHOST;
	public static String RABBITMQ_USERNAME;
	public static String RABBITMQ_PASSWORD;
	public static String JDBC_URL;
	
	public static String RESPONSE_QUEUE = "response.queue";
		
	@Value("${INSERTION_QUEUE}")
	public void setInsertionQueue(String insertionQueue) {
		INSERTION_QUEUE = insertionQueue;
	}
	
	@Value("${VALIDATION_QUEUE}")
	public void setValidationQueue(String validationQueue) {
		VALIDATION_QUEUE = validationQueue;
	}
	
    @Value("${NUMBER_OF_VALIDATION_CONSUMERS}")
	public void setNumberOfValidationConsumers(String numberOfValidationConsumers) {
    		NUMBER_OF_VALIDATION_CONSUMERS = numberOfValidationConsumers;
	}
    
    	@Value("${RESPONSE_EXCHANGE}")
    	public void setResponseExchange(String responseExchange) {
    		RESPONSE_EXCHANGE = responseExchange;
    	}
    	
    	@Value("${RESPONSE_ROUTING_KEY}")
    	public void setResponseRoutingKey(String responseRoutingKey) {
    		RESPONSE_ROUTING_KEY = responseRoutingKey;
    	}
    	
    @Value("${RABBITMQ_HOST}")
	public void setRabbitMQHost(String rabbitMQHost) {
		RABBITMQ_HOST = rabbitMQHost;
	}
    
    	@Value("${RABBITMQ_PORT}")
    	public void setRabbitMQPort(String rabbitMQPort) {
    		RABBITMQ_PORT = rabbitMQPort;
    	}
    	
    	@Value("${RABBITMQ_VHOST}")
    	public void setRabbitMQVHost(String rabbitMQVHost) {
    		RABBITMQ_VHOST = rabbitMQVHost;
    	}
    	
    	@Value("${RABBITMQ_USERNAME}")
    	public void setRabbitMQUsername(String rabbitMQUsername) {
    		RABBITMQ_USERNAME = rabbitMQUsername;
    	}
    	
    	@Value("${RABBITMQ_PASSWORD}")
    	public void setRabbitMQPassword(String rabbitMQPassword) {
    		RABBITMQ_PASSWORD = rabbitMQPassword;
    	}
    	
    @Value("${JDBC_URL}")
	public void setJdbcUrl(String jdbcUrl) {
		JDBC_URL = jdbcUrl;
	}
	
}

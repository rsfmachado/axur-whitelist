package com.axur.amqp;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.axur.WhitelistApplication;
import com.axur.model.RegexPattern;
import com.axur.model.ClientURL;
import com.axur.model.RegexMatches;
import com.axur.repository.ClientRegexRepository;

@Component
public class MQListener {
	private static final Logger logger = LoggerFactory.getLogger(MQListener.class);
	
	@Autowired
	private ClientRegexRepository clientRegexRepository;
	
	@Autowired
	private MQPublisher publisher;

	@RabbitListener(queues = "${INSERTION_QUEUE}")
    public void receiveInsertionMessage(RegexPattern message) {
    		logger.debug("{} receiveInsertionMessage().", WhitelistApplication.APP_LOG);
    		logger.debug("{} receiveInsertionMessage(). Insertion Message [Client = {}, Regex = {}]", WhitelistApplication.APP_LOG, message.getClient(), message.getRegex());
    		
    		clientRegexRepository.save(message);
    		logger.debug("{} receiveInsertionMessage(). RabbitMQ Message saved.", WhitelistApplication.APP_LOG);
    }
	
	@RabbitListener(queues = "${VALIDATION_QUEUE}")
	public void receiveValidtionMessage(ClientURL message) {
		logger.debug("{} receiveValidtionMessage().", WhitelistApplication.APP_LOG);
		logger.debug("{} receiveValidationMessage(). Validation Message [Client = {}, URL = {}, CorrelationID = {}]", WhitelistApplication.APP_LOG, message.getClient(), message.getUrl(), message.getCorrelationId());
		
		List<RegexPattern> whiteList = clientRegexRepository.findByClientOrClientIsNull(message.getClient());
		logger.debug("{} receiveValidtionMessage(). Whitelist fetched from Database.", WhitelistApplication.APP_LOG);
		
		RegexMatches matches = new RegexMatches();
		matches.setMatch(false);
		matches.setCorrelationId(message.getCorrelationId());
		
		List<String> regexList = null;
		for (RegexPattern regexPattern : whiteList) {
			if (Pattern.matches(regexPattern.getRegex(), message.getUrl())) {
				logger.debug("{} receiveValidtionMessage(). Client {} Url {} is Validate by Whitelist Regex {}", WhitelistApplication.APP_LOG, message.getClient(), message.getUrl(), regexPattern.getRegex());
				matches.setMatch(true);
				if (regexList == null)
					regexList = new ArrayList<String>();
				regexList.add(regexPattern.getRegex());
			}
		}
		matches.setRegexlist(regexList);
		
		publisher.publishMatch(matches);
	}
}

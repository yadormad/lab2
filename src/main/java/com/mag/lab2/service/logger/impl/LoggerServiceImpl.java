package com.mag.lab2.service.logger.impl;

import com.mag.lab2.model.message.LogMessage;
import com.mag.lab2.service.logger.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.ConnectionFactory;

@Service
public class LoggerServiceImpl implements LoggerService {
    private JmsTemplate jmsTemplate;

    @Autowired
    public LoggerServiceImpl(ConnectionFactory connectionFactory) {
        this.jmsTemplate = new JmsTemplate(connectionFactory);
    }


    @Override
    public void sendLogMessage(LogMessage logMessage) {
        jmsTemplate.convertAndSend("loggerReceiver", logMessage);
    }
}

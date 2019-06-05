package com.mag.lab2.service.logger.impl;

import com.mag.lab2.model.entity.ChangesTableEntity;
import com.mag.lab2.model.entity.LoggerTableEntity;
import com.mag.lab2.model.message.ActionTypes;
import com.mag.lab2.model.message.ChangesMessage;
import com.mag.lab2.model.message.EntityTypes;
import com.mag.lab2.model.message.LogMessage;
import com.mag.lab2.repository.ChangesRepository;
import com.mag.lab2.repository.EmailNotificationsRepository;
import com.mag.lab2.repository.LoggerRepository;
import com.mag.lab2.service.logger.LogReceiverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class LogReceiverServiceImpl implements LogReceiverService {
    private LoggerRepository loggerRepository;
    private ChangesRepository changesRepository;
    private EmailNotificationsRepository emailNotificationsRepository;

    private final JavaMailSender emailSender;

    private void sendSimpleMessage(String to, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Some changes in Petrivich Pit Stop Inc");
        message.setText(text);
        emailSender.send(message);
    }

    @Autowired
    public LogReceiverServiceImpl(
            LoggerRepository loggerRepository,
            ChangesRepository changesRepository,
            JavaMailSender emailSender,
            EmailNotificationsRepository emailNotificationsRepository
    ) {
        this.loggerRepository = loggerRepository;
        this.changesRepository = changesRepository;
        this.emailSender = emailSender;
        this.emailNotificationsRepository = emailNotificationsRepository;
    }

    @Override
    @JmsListener(destination = "loggerReceiver", containerFactory = "myFactory")
    public void receiveLogMessage(LogMessage logMessage) {
        System.out.println(logMessage.toString());
        LoggerTableEntity loggerTableEntity = loggerRepository.saveAndFlush(this.logMessageToEntity(logMessage));
        Set<ChangesTableEntity> changesTableEntities = new HashSet<>();
        logMessage.getChanges().forEach(changesMessage ->
                changesTableEntities.add(this.changeMessageToEntity(changesMessage, loggerTableEntity)));
        changesRepository.saveAll(changesTableEntities);
        changesRepository.flush();
        this.sendChangeEmailNotification(logMessage);
    }

    private void sendChangeEmailNotification(LogMessage logMessage) {
        emailNotificationsRepository.findAll().forEach(emailReceiver -> {
            if (emailReceiver.getEntityType().getTypeEnum() == logMessage.getEntityType())
                this.sendSimpleMessage(emailReceiver.getEmail(), logMessage.toString());
        });
    }

    private LoggerTableEntity logMessageToEntity(LogMessage logMessage) {
        LoggerTableEntity loggerEntity = new LoggerTableEntity();
        loggerEntity.setObjectId(logMessage.getObjectId());
        loggerEntity.setAction(this.getAction(logMessage.getAction()));
        loggerEntity.setTableName(this.getTableName(logMessage.getEntityType()));
        return loggerEntity;
    }

    private ChangesTableEntity changeMessageToEntity(ChangesMessage changesMessage, LoggerTableEntity logEntity) {
        ChangesTableEntity changesTableEntity = new ChangesTableEntity();
        changesTableEntity.setLogEntity(logEntity);
        changesTableEntity.setField(changesMessage.getField());
        changesTableEntity.setOldValue(changesMessage.getOldValue());
        changesTableEntity.setNewValue(changesMessage.getNewValue());
        return changesTableEntity;
    }

    private String getAction(ActionTypes actionType) {
        switch (actionType) {
            case INSERT:
                return "INSERT";
            case UPDATE:
                return "UPDATE";
            case DELETE:
                return "DELETE";
            default:
                return "";
        }
    }

    private String getTableName(EntityTypes entityType) {
        switch (entityType) {
            case ORDER:
                return "ORDER_TABLE";
            case CLIENT:
                return "CLIENT_TABLE";
            case MACHINIST:
                return "MACHINIST_TABLE";
            default:
                return "";
        }
    }
}

package com.mag.lab2.service.logger;

import com.mag.lab2.model.message.LogMessage;

public interface LogReceiverService {
    void receiveLogMessage(LogMessage logMessage);
}

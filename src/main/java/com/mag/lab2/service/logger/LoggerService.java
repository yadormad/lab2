package com.mag.lab2.service.logger;

import com.mag.lab2.model.message.LogMessage;

public interface LoggerService {
    void sendLogMessage(LogMessage logMessage);
}

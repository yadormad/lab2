package com.mag.lab2.service.impl.logger;

import com.mag.lab2.model.dto.Machinist;
import com.mag.lab2.model.message.ActionTypes;
import com.mag.lab2.model.message.EntityTypes;
import com.mag.lab2.model.message.LogMessage;
import com.mag.lab2.service.MachinistService;
import com.mag.lab2.service.logger.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("machinistLogger")
public class MachinistServiceLogger extends Logger implements MachinistService {
    private final MachinistService machinistService;
    private final LoggerService loggerService;

    @Autowired
    public MachinistServiceLogger(MachinistService machinistService, LoggerService loggerService) {
        this.loggerService = loggerService;
        this.machinistService = machinistService;
    }

    @Override
    public Machinist addMachinist(Machinist machinist) {
        Machinist addedMachinist =  machinistService.addMachinist(machinist);
        this.loggerService.sendLogMessage(
                new LogMessage(
                        addedMachinist.getId(),
                        EntityTypes.MACHINIST,
                        ActionTypes.INSERT
                ));
        return addedMachinist;
    }

    @Override
    public void delete(long id) {
        machinistService.delete(id);
        this.loggerService.sendLogMessage(
                new LogMessage(
                        id,
                        EntityTypes.MACHINIST,
                        ActionTypes.DELETE
                ));
    }

    @Override
    public Machinist editMachinist(Machinist machinist) {
        Machinist oldMachinist = this.getMachinistById(machinist.getId());
        Machinist newMachinist = machinistService.editMachinist(machinist);
        LogMessage logMessage = new LogMessage(
                machinist.getId(),
                EntityTypes.MACHINIST,
                ActionTypes.UPDATE
        );
        logMessage.setChanges(this.findDiscrepancy(oldMachinist, newMachinist));
        this.loggerService.sendLogMessage(logMessage);
        return newMachinist;
    }

    @Override
    public List<Machinist> getAll() {
        return machinistService.getAll();
    }

    @Override
    public Machinist getMachinistById(long id) {
        return machinistService.getMachinistById(id);
    }
}

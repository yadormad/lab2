package com.mag.lab2.service.converter.impl;

import com.mag.lab2.model.dto.Machinist;
import com.mag.lab2.model.entity.MachinistTableEntity;
import com.mag.lab2.repository.MachinistRepository;
import com.mag.lab2.service.converter.Converter;

public class MachinistJPAConverterImpl implements Converter<MachinistTableEntity, Machinist> {

    private MachinistRepository machinistRepository;

    public MachinistJPAConverterImpl(MachinistRepository machinistRepository) {
        this.machinistRepository = machinistRepository;
    }

    @Override
    public MachinistTableEntity toEntity(Machinist machinist) {
        MachinistTableEntity machinistTableEntity = (machinist.getId() != null) ? machinistRepository.getOne(machinist.getId()) : new MachinistTableEntity();
        machinistTableEntity.setFirstname(machinist.getFirstName());
        machinistTableEntity.setLastname(machinist.getLastName());
        machinistTableEntity.setFathername(machinist.getFatherName());
        machinistTableEntity.setHourCost(machinist.getValueCost());
        return machinistTableEntity;
    }

    @Override
    public Machinist toDto(MachinistTableEntity machinistTableEntity) {
        return new Machinist(machinistTableEntity.getId(), machinistTableEntity.getFirstname(), machinistTableEntity.getLastname(), machinistTableEntity.getFathername(), machinistTableEntity.getHourCost());
    }
}

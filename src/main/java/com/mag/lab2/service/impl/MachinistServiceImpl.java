package com.mag.lab2.service.impl;

import com.mag.lab2.model.entity.MachinistTableEntity;
import com.mag.lab2.model.dto.Machinist;
import com.mag.lab2.repository.MachinistRepository;
import com.mag.lab2.service.MachinistService;
import com.mag.lab2.service.converter.Converter;
import com.mag.lab2.service.converter.impl.MachinistJPAConverterImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MachinistServiceImpl implements MachinistService {

    private final MachinistRepository machinistRepository;
    private Converter<MachinistTableEntity, Machinist> machinistConverter;

    @Autowired
    public MachinistServiceImpl(MachinistRepository machinistRepository) {
        this.machinistRepository = machinistRepository;
        this.machinistConverter = new MachinistJPAConverterImpl(machinistRepository);
    }
    
    @Override
    public Machinist addMachinist(Machinist machinist) {
        MachinistTableEntity machinistEntity = machinistConverter.toEntity(machinist);
        machinistRepository.saveAndFlush(machinistEntity);
        return machinistConverter.toDto(machinistEntity);
    }

    @Override
    public void delete(long id) {
        machinistRepository.deleteById(id);
    }

    @Override
    public Machinist editMachinist(Machinist machinist) {
        machinistRepository.saveAndFlush(machinistConverter.toEntity(machinist));
        return machinist;
    }

    @Override
    public List<Machinist> getAll() {
        List<Machinist> allMachinists = new ArrayList<>();
        for(MachinistTableEntity machinistEntity: machinistRepository.findAll()) {
            allMachinists.add(machinistConverter.toDto(machinistEntity));
        }
        return allMachinists;
    }
}

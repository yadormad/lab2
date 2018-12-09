package com.mag.lab2.service.impl;

import com.mag.lab2.model.entity.MachinistTableEntity;
import com.mag.lab2.model.dto.Machinist;
import com.mag.lab2.repository.MachinistRepository;
import com.mag.lab2.service.MachinistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MachinistServiceImpl implements MachinistService {

    private final MachinistRepository machinistRepository;

    @Autowired
    public MachinistServiceImpl(MachinistRepository machinistRepository) {
        this.machinistRepository = machinistRepository;
    }
    
    @Override
    public Machinist addMachinist(Machinist machinist) {
        MachinistTableEntity machinistEntity = new MachinistTableEntity();
        machinistEntity.toEntity(machinist);
        machinistRepository.saveAndFlush(machinistEntity);
        return machinistEntity.toModel();
    }

    @Override
    public void delete(long id) {
        machinistRepository.deleteById(id);
    }

    @Override
    public Machinist editMachinist(Machinist machinist) {
        MachinistTableEntity machinistEntity = machinistRepository.getOne(machinist.getId());
        machinistEntity.toEntity(machinist);
        machinistRepository.saveAndFlush(machinistEntity);
        return machinistEntity.toModel();
    }

    @Override
    public List<Machinist> getAll() {
        List<Machinist> allMachinists = new ArrayList<>();
        for(MachinistTableEntity machinistEntity: machinistRepository.findAll()) {
            allMachinists.add(machinistEntity.exportOrders(machinistEntity.toModel()));
        }
        return allMachinists;
    }
}

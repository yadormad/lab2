package com.mag.lab2.service;

import com.mag.lab2.model.dto.Machinist;

import java.util.List;

public interface MachinistService {
    Machinist addMachinist(Machinist machinist);
    void delete(long id);
    Machinist editMachinist(Machinist machinist);
    List<Machinist> getAll();
    Machinist getMachinistById(long id);
}

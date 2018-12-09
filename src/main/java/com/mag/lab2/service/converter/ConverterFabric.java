package com.mag.lab2.service.converter;

public interface ConverterFabric<E, M> {
    E toEntity(M m);
    M toDto(E e);
}

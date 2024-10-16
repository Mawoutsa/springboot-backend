package com.facturation.application.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelMapperService {

    @Autowired
    private static ModelMapper modelMapper;

    public static <D, T> D map(final T entity, Class<D> outClass){
        return modelMapper.map(entity, outClass);
    }

    public <D, T> void  map(final T entity, D dto){
        modelMapper.map(entity, dto);
    }
}

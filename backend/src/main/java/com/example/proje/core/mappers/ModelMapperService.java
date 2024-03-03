package com.example.proje.core.mappers;

import org.modelmapper.ModelMapper;
public interface ModelMapperService {
    ModelMapper forResponse();
    ModelMapper forRequest();
}

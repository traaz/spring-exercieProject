package com.example.proje.core.mappers;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.modelmapper.convention.MatchingStrategies;

@Service
@AllArgsConstructor
public class ModelMapperManager implements ModelMapperService{
    private ModelMapper modelMapper;
    @Override
    public ModelMapper forResponse() {
        this.modelMapper.getConfiguration()
                .setAmbiguityIgnored(true)
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        return this.modelMapper;
    }

    @Override
    public ModelMapper forRequest() {
        this.modelMapper.getConfiguration()
                .setAmbiguityIgnored(true)
                .setMatchingStrategy(MatchingStrategies.STANDARD);
        return this.modelMapper;
    }
}

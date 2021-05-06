package com.ailtonluiz.sgdapi.api.v1.assembler;

import com.ailtonluiz.sgdapi.api.v1.model.input.CityInput;
import com.ailtonluiz.sgdapi.domain.model.City;
import com.ailtonluiz.sgdapi.domain.model.State;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CityInputDisassembler {
    @Autowired
    private ModelMapper modelMapper;

    public City toDomainObject(CityInput cityInput) {
        return modelMapper.map(cityInput, City.class);
    }

    public void copyToDomainObject(CityInput cityInput, City city) {
        // Para evitar org.hibernate.HibernateException: identifier of an instance of
        // com.algaworks.algafood.domain.model.Estado was altered from 1 to 2
        city.setState(new State());

        modelMapper.map(cityInput, city);
    }
//teste GitHub
}

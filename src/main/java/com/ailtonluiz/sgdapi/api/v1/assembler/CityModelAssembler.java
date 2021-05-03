package com.ailtonluiz.sgdapi.api.v1.assembler;

import com.ailtonluiz.sgdapi.api.v1.controller.CitiesController;
import com.ailtonluiz.sgdapi.api.v1.SgdLinks;
import com.ailtonluiz.sgdapi.api.v1.model.CityModel;
import com.ailtonluiz.sgdapi.core.security.SgdSecurity;
import com.ailtonluiz.sgdapi.domain.model.City;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class CityModelAssembler
        extends RepresentationModelAssemblerSupport<City, CityModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SgdLinks sgdLinks;

    @Autowired
    private SgdSecurity sgdSecurity;

    public CityModelAssembler() {
        super(CitiesController.class, CityModel.class);
    }

    @Override
    public CityModel toModel(City city) {
        CityModel cityModel = createModelWithId(city.getId(), city);

        modelMapper.map(city, cityModel);

        if (sgdSecurity.canConsultCities()) {
            cityModel.add(sgdLinks.linkToCities("cities"));
        }

        if (sgdSecurity.canConsultStates()) {
            cityModel.getState().add(sgdLinks.linkToState(cityModel.getState().getId()));
        }

        return cityModel;
    }

    @Override
    public CollectionModel<CityModel> toCollectionModel(Iterable<? extends City> entities) {
        CollectionModel<CityModel> collectionModel = super.toCollectionModel(entities);

        if (sgdSecurity.canConsultCities()) {
            collectionModel.add(sgdLinks.linkToCities());
        }

        return collectionModel;
    }

}

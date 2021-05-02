package com.ailtonluiz.sgdapi.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "cities")
@Setter
@Getter
public class CityModel extends RepresentationModel<CityModel> {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Santos")
    private String name;

    private StateModel state;
}

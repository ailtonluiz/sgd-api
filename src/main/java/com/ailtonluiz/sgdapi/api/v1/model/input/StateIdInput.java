package com.ailtonluiz.sgdapi.api.v1.model.input;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

public class StateIdInput {
    @ApiModelProperty(example = "1", required = true)
    @NotNull
    private Long id;
}

package com.ailtonluiz.sgdapi.api.v1.openapi.controller;

import com.ailtonluiz.sgdapi.api.v1.model.CityModel;
import com.ailtonluiz.sgdapi.api.exceptionhandler.Problem;;
import com.ailtonluiz.sgdapi.api.v1.model.input.CityInput;

import org.springframework.hateoas.CollectionModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Cities")
public interface CityControllerOpenApi {
    @ApiOperation("List the cities")
    CollectionModel<CityModel> list();

    @ApiOperation("Search City for ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID city invalid", response = Problem.class),
            @ApiResponse(code = 404, message = "City not found", response = Problem.class)
    })
    CityModel search(
            @ApiParam(value = "ID de uma city", example = "1", required = true)
                    Long cityId);

    @ApiOperation("Register city")
    @ApiResponses({
            @ApiResponse(code = 201, message = "City cadastrada"),
    })
    CityModel add(
            @ApiParam(name = "body", value = "Representação de uma nova city", required = true)
                    CityInput cityInput);

    @ApiOperation("Atualiza uma city por ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "City atualizada"),
            @ApiResponse(code = 404, message = "City não encontrada", response = Problem.class)
    })
    CityModel update(
            @ApiParam(value = "ID de uma city", example = "1", required = true)
                    Long cityId,

            @ApiParam(name = "body", value = "Representação de uma city com os novos dados", required = true)
                    CityInput cityInput);

    @ApiOperation("Exclui uma city por ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "City excluída"),
            @ApiResponse(code = 404, message = "City não encontrada", response = Problem.class)
    })
    void remove(
            @ApiParam(value = "ID de uma city", example = "1", required = true)
                    Long cityId);
}

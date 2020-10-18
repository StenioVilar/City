package com.maxi.pago.city.controller;

import com.maxi.pago.city.service.CityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "City")
public class CityController {

    @Autowired
    private CityService service;

    @ApiOperation(value = "Get all cities")
    @GetMapping(value = "/cities", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getCities() {
       return new ResponseEntity<>(service.getCities(), HttpStatus.OK);
    }

}
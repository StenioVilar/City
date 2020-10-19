package com.maxi.pago.city.controller;

import com.maxi.pago.city.model.CityDifference;
import com.maxi.pago.city.service.CityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "City")
public class CityController {

    @Autowired
    private CityService service;

    @ApiOperation(value = "Get all cities")
    @GetMapping(value = "/cities", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCities() {
       return new ResponseEntity<>(service.getCities(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get differences between cities")
    @GetMapping(value = "/differences", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity getDifferences(@RequestParam(value="format") @ApiParam(value = "Format type (json/xml)") String format,
                                          @RequestParam(value="measure") @ApiParam(value = "Measure type (km / mi)") String measure) {

        HttpHeaders httpHeaders = new HttpHeaders();

        if (format.equalsIgnoreCase("json")) {
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        } else if(format.equalsIgnoreCase("xml")) {
            httpHeaders.setContentType(MediaType.APPLICATION_XML);
        }

        List<CityDifference> dfferenceList = service.getDifferences(format,measure);

        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(dfferenceList);

    }

}
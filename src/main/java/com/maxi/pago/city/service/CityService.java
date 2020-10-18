package com.maxi.pago.city.service;


import com.maxi.pago.city.dao.CityDAO;
import com.maxi.pago.city.error.ResourceNotFoundException;
import com.maxi.pago.city.error.ServiceUnavailableException;
import com.maxi.pago.city.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityDAO cityDao;

    public List<City> getCities() {
        try {
            List<City> cities = cityDao.findAll();
            if (cities.isEmpty()) {
                throw new ResourceNotFoundException("No cities registered");
            }
            return cities;
        }catch (Exception e){
            throw new ServiceUnavailableException(e.getMessage());
        }
    }

}

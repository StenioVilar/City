package com.maxi.pago.city.service;


import com.maxi.pago.city.dao.CityDAO;
import com.maxi.pago.city.error.NoContentException;
import com.maxi.pago.city.error.ResourceNotFoundException;
import com.maxi.pago.city.error.ServiceUnavailableException;
import com.maxi.pago.city.model.City;
import com.maxi.pago.city.model.CityDifference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityDAO cityDao;

    @Autowired
    private Environment env;

    public List<City> getCities() {
        try {
            List<City> cities = cityDao.findAll();
            validateCities(cities);

            return cities;
        }catch (SQLException e){
            throw new ServiceUnavailableException(e.getMessage());
        }
    }

    public List<CityDifference> getDifferences(String measure) {

        try {
            List<City> cities = cityDao.findAll();
            validateCities(cities);
            List<CityDifference> diffCities = generateDiff(cities, measure);
            return diffCities;

        } catch (SQLException e) {
            throw new ServiceUnavailableException(e.getMessage());
        }

    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*:: Método para gerar as diferenças tanto de distancia quanto de timezone (GMT)
         Para distancia, chamada a método escolhido
         Para diferença de horário, subtraçao normal com tratamento a GMT negativo
     */
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private List<CityDifference> generateDiff(List<City> cities, String measure) {

        List<CityDifference> diffCities = new ArrayList<>();

        for(City city : cities){
            for(City cityAux : cities) {
                if(city.equals(cityAux)){
                    break;
                }else{
                    CityDifference cityDif = new CityDifference();
                    cityDif.setFirstCity(city);
                    cityDif.setFinalCity(cityAux);
                    cityDif.setDistanceDifference(distance(city.getLatitude(),city.getLongitude(),cityAux.getLatitude(),cityAux.getLongitude(), measure));
                    int hourDifference = city.getTimezone() - cityAux.getTimezone();
                    cityDif.setHourDifference(hourDifference < 0 ? hourDifference * -1 : hourDifference);
                    diffCities.add(cityDif);
                }
            }
        }

        return diffCities;

    }

    private void validateCities(List<City> cities) {
        if (cities.isEmpty()) {
            throw new ResourceNotFoundException(env.getProperty("cities.not.found"));
        } else if (cities.size() < 3) {
            throw new NoContentException(env.getProperty("cities.no.content"));
        }
    }


    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

    /*::  Função escolhida através do site https://dzone.com/ que é um site com conteúdo "open source"
          que tem uma area destinada apenas para Java, indo de novidades, até funções práticas como essa
          que obtive, aonde consigo comparar as latidudes e já passando a unidade desejada, sendo:

          K - Kilometers ( alterado para String (km) devido necessidade do desafio )
          M - Miles
          N - Nautical Miles ( não utilizado no desafio - logo comentado)

          https://dzone.com/articles/distance-calculation-using-3 (URL específica da função com mais de 30k views trazendo certa confiabilidade)

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

    private double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit.equalsIgnoreCase("km")) {
            dist = dist * 1.609344;
        }
        // Caso fosse necessário - Nautical Miles
        //else if (unit == 'N') {
        //  dist = dist * 0.8684;
        //}
        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::                                                                :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/


}

package com.maxi.pago.city.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityDifference {

    private City firstCity;
    private City finalCity;
    private double distanceDifference;
    private int hourDifference;

}

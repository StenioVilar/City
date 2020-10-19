package com.maxi.pago.city.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class City {

    private int id;
    private String name;
    private double latitude;
    private double longitude;
    private int timezone;

}

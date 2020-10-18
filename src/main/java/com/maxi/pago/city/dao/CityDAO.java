package com.maxi.pago.city.dao;

import com.maxi.pago.city.model.City;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class CityDAO extends GenericDAO {

    public List<City> findAll() throws SQLException {

        Connection conn = null;
        PreparedStatement stmt  = null;
        List<City> cityList = new ArrayList<>();

        try {
            conn = getConnection();
            stmt = conn.prepareStatement("SELECT * FROM CITY");

            ResultSet queryResult = stmt.executeQuery();

            while (queryResult.next()) {
                City city = new City();
                city.setId(queryResult.getInt("id"));
                city.setName(queryResult.getString("name"));
                city.setLatitude(queryResult.getDouble("latitude"));
                city.setLongitude(queryResult.getDouble("longitude"));
                cityList.add(city);
            }
            queryResult.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            try { if (stmt != null) stmt.close(); } catch (Exception e) {};
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }

        return cityList;

    }

}

package org.example.model;

import lombok.*;
import java.sql.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class City {
    private Integer city_id;
    private String city_name;

    public static City create(ResultSet resultSet) throws SQLException {
        City city = new City();
        city.setCity_id(resultSet.getInt("city_id"));
        city.setCity_name(resultSet.getString("city_name"));
        return city;
    }
}

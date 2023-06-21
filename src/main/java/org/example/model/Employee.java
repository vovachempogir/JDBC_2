package org.example.model;

import lombok.*;
import java.sql.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private Integer id;
    private String first_name;
    private String last_name;
    private String gender;
    private Integer age;
    private Integer city_id;

    public static Employee create(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();
        employee.setId(resultSet.getInt("id"));
        employee.setFirst_name(resultSet.getString("first_name"));
        employee.setLast_name(resultSet.getString("last_name"));
        employee.setGender(resultSet.getString("gender"));
        employee.setAge(resultSet.getInt("age"));
        employee.setCity_id(resultSet.getInt("city_id"));
        return employee;
    }
}
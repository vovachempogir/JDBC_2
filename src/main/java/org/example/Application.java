package org.example;

import org.example.dao.EmployeeDAOImpl;

import java.sql.*;

public class Application {
    public static void main(String[] args) throws SQLException {
        final String url = "jdbc:postgresql://localhost:5432/skypro";
        final String user = "postgres";
        final String password = "Stalker97+";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement
                     ("SELECT * FROM employee" +
                             " INNER JOIN city" +
                             " ON employee.city_id = city.city_id;")
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("id: " + resultSet.getInt("id") +
                        "; Имя: " + resultSet.getString("first_name") +
                        "; Фамилия: " + resultSet.getString("last_name") +
                        "; Пол: " + resultSet.getString("gender") +
                        "; Город: " + resultSet.getString("city_name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Connection connection = DriverManager.getConnection(url, user, password);
        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl(connection);
//        employeeDAO.create(new Employee(null, "Николай", "Попов", "муж", 35, 4));
        System.out.println(employeeDAO.findById(3));
        employeeDAO.findAll().stream().forEach(System.out::println);
        employeeDAO.update(7, "Оксана", "Иванова", "жен", 41, 1);
        System.out.println(employeeDAO.findById(7));
//        employeeDAO.deleteById(8);
    }
}
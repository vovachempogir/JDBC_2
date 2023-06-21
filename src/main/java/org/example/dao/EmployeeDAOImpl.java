package org.example.dao;

import org.example.model.Employee;
import java.sql.*;
import java.util.*;

public class EmployeeDAOImpl implements EmployeeDAO {

    private final Connection connection;

    public EmployeeDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Employee employee) {
        try (PreparedStatement preparedStatement = connection.prepareStatement
                ("INSERT INTO employee(first_name, " +
                        "last_name," +
                        "gender, " +
                        "age," +
                        "city_id ) " +
                        "VALUES (?, ?, ?, ?, ?)")) {
            preparedStatement.setString(1, employee.getFirst_name());
            preparedStatement.setString(2, employee.getLast_name());
            preparedStatement.setString(3, employee.getGender());
            preparedStatement.setInt(4, employee.getAge());
            preparedStatement.setInt(5,employee.getCity_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employee findById(Integer id) {
        Employee employee = new Employee();
        try (PreparedStatement preparedStatement = connection.prepareStatement
                ("SELECT * FROM employee INNER JOIN city ON employee.city_id = city.city_id AND id = (?)")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employee.setId(Integer.parseInt(resultSet.getString("id")));
                employee.setFirst_name(resultSet.getString("first_name"));
                employee.setLast_name(resultSet.getString("last_name"));
                employee.setGender(resultSet.getString("gender"));
                employee.setAge(Integer.parseInt(resultSet.getString("age")));
                employee.setCity_id(resultSet.getInt("city_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employeeList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement
                ("SELECT * FROM employee INNER JOIN city ON employee.city_id = city.city_id")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer id = Integer.parseInt(resultSet.getString("id"));
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                String gender = resultSet.getString("gender");
                Integer age = Integer.parseInt(resultSet.getString("age"));
                Integer city_id = resultSet.getInt("city_id");
                employeeList.add(new Employee(id, first_name, last_name, gender, age, city_id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    @Override
    public void update(Integer id, String first_name, String last_name, String gender, Integer age, Integer city_id) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE employee SET first_name = (?), " +
                        "last_name = (?), " +
                        "gender = (?), " +
                        "age = (?), " +
                        "city_id = (?) " +
                        "WHERE id = (?)")) {
            preparedStatement.setString(1, first_name);
            preparedStatement.setString(2, last_name);
            preparedStatement.setString(3, gender);
            preparedStatement.setInt(4, age);
            preparedStatement.setInt(5, city_id);
            preparedStatement.setInt(6, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Integer id) {
        try(PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM employee WHERE id = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
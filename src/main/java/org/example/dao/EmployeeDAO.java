package org.example.dao;

import org.example.model.Employee;
import java.util.*;

public interface EmployeeDAO {
    void create(Employee employee);

    Employee findById(Integer id);

    List<Employee> findAll();

    void update(Integer id, String first_name, String last_name, String gender, Integer age, Integer city_id);

    void deleteById(Integer id);
}

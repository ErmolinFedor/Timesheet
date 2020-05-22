package com.ermolin.timesheet.dao;

import com.ermolin.timesheet.beans.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDAOJDBC implements EmployeeDAO {
    @Override
    public void insert(Employee employee) {

    }

    @Override
    public void update(Employee employee) {

    }

    @Override
    public void delete(Employee employee) {

    }

    public Employee getEmployeeByID(int id){
        Employee employee = new Employee();
        try (Connection connection = JDBCPostgreeConnection.getConnection()){
            String sql = "select * from Employees where id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                preparedStatement.setInt(1, id);
                try(ResultSet resultSet = preparedStatement.executeQuery();){
                    while (resultSet.next()) {
                        employee.setId(resultSet.getInt("id"));
                        employee.setName(resultSet.getString("name"));
                        employee.setSurname(resultSet.getString("surname"));
                        employee.setPosition(resultSet.getString("position"));
                    }
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return employee;
    }
}

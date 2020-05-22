package com.ermolin.timesheet.dao;

import com.ermolin.timesheet.beans.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAOJDBC implements DepartmentDAO {

    public List<Department> getAllDepartments(){
        List<Department> departments = new ArrayList<>();
        try (Connection connection = JDBCPostgreeConnection.getConnection()){
            String sql = "select * from Departments";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    Department department = new Department();
                    department.setName(resultSet.getString("name"));
                    department.setId(resultSet.getInt("id"));
                    departments.add(department);
                }
            }
            for (Department department: departments) {
                EmployeeDAO employeeDAO = new EmployeeDAOJDBC();
                String sqlDep = "select * from WorkPlace where idDepartment = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sqlDep)){
                    preparedStatement.setInt(1, department.getId());
                    ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()){
                        int id = resultSet.getInt("idEmployee");
                        department.addEmployee(employeeDAO.getEmployeeByID(id));
                    }
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return departments;
    }

    @Override
    public void insert(Department department) {

    }

    @Override
    public void update(Department department) {

    }

    @Override
    public void delete(Department department) {

    }
}

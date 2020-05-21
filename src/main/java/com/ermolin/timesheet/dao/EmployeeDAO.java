package com.ermolin.timesheet.dao;

import com.ermolin.timesheet.beans.Employee;

public interface EmployeeDAO extends DAO<Employee> {
    Employee getEmployeeByID(int id);
}

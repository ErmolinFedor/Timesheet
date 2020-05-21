package com.ermolin.timesheet.dao;

import com.ermolin.timesheet.beans.Department;

import java.util.List;

public interface DepartmentDAO extends DAO<Department> {
    List<Department> getAllDepartments();
}

package com.ermolin.timesheet.dao;

import com.ermolin.timesheet.beans.DayType;
import com.ermolin.timesheet.beans.PersonCalendar;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class PersonalCalendarDAOJDBC implements  PersonalCalendarDAO{

    /*private static DayType factory(String str){
        if (str == null || str.isEmpty()) return null;
        switch (str){
            case "Я" : return DayType.WORK;
            case "Н" : return DayType.ABSENT;
            case "В" : return DayType.WEEKEND;
            case "Рв" : return DayType.WORK_ON_WEEKEND;
            case "Б" : return DayType.SICK;
            case "К" : return DayType.BUSINESS_TRIP;
            case "ОТ"  : return DayType.VACATION;
            case "До"  : return DayType.FURLOUGH;
            case "Хд"  : return DayType.BUSINESS_DAY;
            case "У" : return DayType.STUDY_LEAVE;
            case "Ож"  : return DayType.LEAVE_FOR_CHILDREN;
        }
        return null;
    }*/
    @Override
    @SuppressWarnings("deprecation")
    public PersonCalendar getCalendar(int monthLong, int id, int monthIndex, int idDep){
        PersonCalendar res = new PersonCalendar(monthLong, id);

        try (Connection connection = JDBCPostgreeConnection.getConnection()){
            String sql = "select * from PersonCalendar where idEmployee = ? and idDepartment = ? and " +
                    "\"day\" between date(?) and date(?) + interval '1 month'";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                preparedStatement.setInt(1, id);
                Date date = new Date(new GregorianCalendar(2020, monthIndex , 1).getTimeInMillis());
                preparedStatement.setInt(2, idDep + 1 );
                preparedStatement.setDate(3, date);
                preparedStatement.setDate(4, date);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()){
                    Calendar calendar = new GregorianCalendar();
                    calendar.setTime(resultSet.getDate("day"));
                    DayType dayType = factory(resultSet.getString("workType"));
                    res.addDay(calendar.get(Calendar.DAY_OF_MONTH) -1, dayType);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  res;
    }

    @Override
    public void insert(PersonCalendar personCalendar) {

    }

    @Override
    public void update(PersonCalendar personCalendar) {

    }

    @Override
    public void delete(PersonCalendar personCalendar) {

    }
}

package com.ermolin.timesheet.dao;

import com.ermolin.timesheet.beans.DayType;
import com.ermolin.timesheet.beans.PersonCalendar;

public interface PersonalCalendarDAO extends DAO<PersonCalendar> {
    PersonCalendar getCalendar(int monthLong, int id, int monthIndex, int idDep);

    default DayType factory(String str){
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
    }

}

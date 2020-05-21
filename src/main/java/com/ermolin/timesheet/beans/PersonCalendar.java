package com.ermolin.timesheet.beans;

public class PersonCalendar {
    private int id;
    private int idDep;
    private DayType[] days;
    private int monthLong;

    public PersonCalendar(int monthLong, int id) {
        this.monthLong = monthLong;
        this.id = id;
        days = new DayType[monthLong];
    }

    public PersonCalendar(int monthLong, int id, int idDep){
        this(monthLong, id);
        this.idDep = idDep;
    }

    public void addDay(int index, DayType type){
        days[index] = type;
    }

    public DayType[] getDays() {
        return days;
    }
}

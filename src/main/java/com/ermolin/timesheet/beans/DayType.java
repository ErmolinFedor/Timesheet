package com.ermolin.timesheet.beans;

public enum DayType {

    WORK("Я"),                  //Я – полный рабочий день;
    ABSENT("Н"),                //Н – отсутствие на рабочее место по невыясненным причинам;
    WEEKEND("В"),               //В – выходные и праздничные дни;
    WORK_ON_WEEKEND("Рв"),      //Рв – работа в праздничные и выходные дни; а также работа в праздничные и выходные дни, при
                                //нахождении в командировке;
    SICK("Б"),                  //Б – дни временной нетрудоспособности;
    BUSINESS_TRIP("К"),         //К – командировочные дни; а также, выходные (нерабочие) дни при нахождении в командировке,
                                //когда сотрудник отдыхает, в соответствии с графиком работы ООО «Наука» в командировке;
    VACATION("ОТ"),             //ОТ – ежегодный основной оплаченный отпуск;
    FURLOUGH("До"),             //До – неоплачиваемый отпуск (отпуск за свой счет) ;
    BUSINESS_DAY("Хд"),         //Хд – хозяйственный день;
    STUDY_LEAVE("У"),           //У – отпуск на период обучения.
    LEAVE_FOR_CHILDREN("Ож");   //Ож – Отпуск по уходу за ребенком.

    private String name;

    DayType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

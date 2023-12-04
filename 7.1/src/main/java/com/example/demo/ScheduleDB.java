package com.example.demo;

public class ScheduleDB {

   private Integer id;
   private Integer teacherId;
   private Integer disciplineId;
   private Integer timeId;

    public ScheduleDB() {
    }

    public ScheduleDB(Integer id, Integer teacherId, Integer disciplineId, Integer timeId) {
        this.id = id;
        this.teacherId = teacherId;
        this.disciplineId = disciplineId;
        this.timeId = timeId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getDisciplineId() {
        return disciplineId;
    }

    public void setDisciplineId(Integer disciplineId) {
        this.disciplineId = disciplineId;
    }

    public Integer getTimeId() {
        return timeId;
    }

    public void setTimeId(Integer timeId) {
        this.timeId = timeId;
    }
}



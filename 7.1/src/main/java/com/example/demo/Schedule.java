package com.example.demo;

public class Schedule {
    private Integer id;
    private Teacher teacher;
    private Discipline discipline;
    private TimeEn time;

    public Schedule() {
    }

    public Schedule(Integer id, Teacher teacher, Discipline discipline, TimeEn time) {
        this.id = id;
        this.teacher = teacher;
        this.discipline = discipline;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public TimeEn getTime() {
        return time;
    }

    public void setTime(TimeEn time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", teacher=" + teacher +
                ", discipline=" + discipline +
                ", time=" + time +
                '}';
    }
}

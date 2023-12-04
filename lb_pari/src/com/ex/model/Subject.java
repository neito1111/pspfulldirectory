package com.ex.model;

import javax.persistence.*;
import java.util.List;

@Entity

public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String dayOfWeek;

    @ElementCollection
    private List<String> classrooms;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Teacher_id")
    private Teacher Teacher;







    public void setTeacher(com.ex.model.Teacher teacher) {
        Teacher = teacher;
    }

    public com.ex.model.Teacher getTeacher() {
        return Teacher;
    }

    public Subject(String name, String dayOfWeek, List<String> classrooms, Teacher teacher) {
        this.name = name;
        this.dayOfWeek = dayOfWeek;
        this.classrooms = classrooms;
        this.Teacher=teacher;
    }

    public Subject() {

    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public List<String> getClassrooms() {
        return classrooms;
    }

    public void setClassrooms(List<String> classrooms) {
        this.classrooms = classrooms;
    }
}
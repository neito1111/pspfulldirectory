package com.ex.model;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Subject> subjects;

    @ElementCollection
    private List<Integer> weeklyClasses;

    @ElementCollection
    private List<Integer> studentsPerClass;

    public Teacher() {
    }

    public Teacher(String name, List<Subject> subjects, List<Integer> weeklyClasses, List<Integer> studentsPerClass) {
        this.name = name;
        this.subjects = subjects;
        this.weeklyClasses = weeklyClasses;
        this.studentsPerClass = studentsPerClass;
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

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public List<Integer> getWeeklyClasses() {
        return weeklyClasses;
    }

    public void setWeeklyClasses(List<Integer> weeklyClasses) {
        this.weeklyClasses = weeklyClasses;
    }

    public List<Integer> getStudentsPerClass() {
        return studentsPerClass;
    }

    public void setStudentsPerClass(List<Integer> studentsPerClass) {
        this.studentsPerClass = studentsPerClass;
    }
}
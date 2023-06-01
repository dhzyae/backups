package com.mouse.spring.pojo;

import lombok.Data;

import java.util.Arrays;
import java.util.Map;

@Data
public class Student {

    private Integer sid;

    private String sname;

    private Integer age;

    private String gender;

    private Double score;

    private String[] hobby;

    private Clazz clazz;

    private Map<String, Teacher> teacherMap;

    public Student() {
    }

    public Student(Integer sid, String sname, Integer age, String gender) {
        this.sid = sid;
        this.sname = sname;
        this.age = age;
        this.gender = gender;
    }

    public Student(Integer sid, String sname, Double score, String gender) {
        this.sid = sid;
        this.sname = sname;
        this.score = score;
        this.gender = gender;
    }
}

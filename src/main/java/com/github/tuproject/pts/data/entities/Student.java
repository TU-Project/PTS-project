package com.github.tuproject.pts.data.entities;

import com.poiji.annotation.ExcelCellName;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Student {

    @ExcelCellName("ID")
    private int id;

    @ExcelCellName("Result")
    private double result;

    private int uploadedFiles;

    List<StudentActivities> studentActivities;

    public List<StudentActivities> getStudentActivities() { return studentActivities; }

    public void setStudentActivities(List<StudentActivities> studentActivities) { this.studentActivities = studentActivities; }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public int getUploadedFiles() {
        return uploadedFiles;
    }

    public void setUploadedFiles(int uploadedFiles) {
        this.uploadedFiles = uploadedFiles;
    }

    public void addStudentActivity(StudentActivities activity){
        if (studentActivities==null){
            studentActivities = new ArrayList<StudentActivities>();
        }

        studentActivities.add(activity);
    }

    private String eventContext;
    private String component;
    private String eventName;
    private String description;

    public String getEventContext() {
        return eventContext;
    }

    public void setEventContext(String eventContext) {
        this.eventContext = eventContext;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}



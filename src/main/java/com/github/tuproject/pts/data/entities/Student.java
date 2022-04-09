package com.github.tuproject.pts.data.entities;

import com.poiji.annotation.ExcelCellName;

import java.util.Comparator;

public class Student {

    @ExcelCellName("ID")
    private int id;

    @ExcelCellName("Result")
    private double result;

    private int uploadedFiles;

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
}



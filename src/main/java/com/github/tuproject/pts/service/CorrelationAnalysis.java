package com.github.tuproject.pts.service;

import com.github.tuproject.pts.data.entities.Student;

import java.io.FileNotFoundException;
import java.util.List;

public interface CorrelationAnalysis {
    public double GetCorrelationAnalysis(String pathToStudentResultsFile, String pathToStudentActivitiesFile);
    public double GetCorrelationAnalysis(String pathToStudentResultsFile1,String pathToStudentResultsFile2, String pathToStudentActivitiesFile);
    public List<Student> GetStudentsUsedInAnalysis();

}

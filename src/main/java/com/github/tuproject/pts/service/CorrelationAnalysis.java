package com.github.tuproject.pts.service;

import com.github.tuproject.pts.data.entities.Student;

import java.io.FileNotFoundException;
import java.util.List;

public interface CorrelationAnalysis {
    public double GetCorrelationCoefficient(String pathToStudentResultsFile, String pathToStudentActivitiesFile);
    public double GetCorrelationCoefficient(String pathToStudentResultsFile1, String pathToStudentResultsFile2, String pathToStudentActivitiesFile);
    public List<Student> GetStudentsUsedInAnalysis();

}

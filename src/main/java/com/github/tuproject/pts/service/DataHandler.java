package com.github.tuproject.pts.service;

import com.github.tuproject.pts.data.entities.ResultFrequency;
import com.github.tuproject.pts.data.entities.Student;
import com.github.tuproject.pts.data.entities.StudentActivities;
import org.apache.commons.lang3.Range;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public interface DataHandler {
    List<Student> GetStudents(String path) throws FileNotFoundException;

    List<StudentActivities> GetStudentActivities(String path) throws FileNotFoundException;

    List<Student> SetUploadedFiles(List<Student> students, List<StudentActivities> activities);

    double GetPearsonsCorrelation(List<Student> students);

    Range<Double> GetRange(List<Student> students);

    double GetVariance(List<Student> students);

    double GetStandardDeviation(List<Student> students);

    ArrayList<ResultFrequency> getFrequencyDistribution(List<Student> students);
}

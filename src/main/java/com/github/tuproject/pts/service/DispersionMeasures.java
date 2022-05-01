package com.github.tuproject.pts.service;

import com.github.tuproject.pts.data.entities.Student;
import org.apache.commons.lang3.Range;

import java.util.List;

public interface DispersionMeasures {
    Range<Double> Range(String pathToStudentResultsFile1, String pathToStudentResultsFile2,
                        String pathToStudentActivitiesFile, boolean isSingleYear);
    double Variance(String pathToStudentResultsFile1, String pathToStudentResultsFile2,
                    String pathToStudentActivitiesFile, boolean isSingleYear);
    double StandardDeviation(String pathToStudentResultsFile1, String pathToStudentResultsFile2,
                             String pathToStudentActivitiesFile, boolean isSingleYear);
    List<Student> GetStudentsUsedInAnalysis();
}

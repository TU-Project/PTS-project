package com.github.tuproject.pts.service;

import com.github.tuproject.pts.data.entities.ResultFrequency;
import com.github.tuproject.pts.data.entities.Student;

import java.util.ArrayList;
import java.util.List;

public interface FrequencyDistribution {

    ArrayList<ResultFrequency> getFrequencyDistribution(String pathToStudentResultsFile1, String pathToStudentResultsFile2,
                                                        String pathToStudentActivitiesFile, boolean isSingleYear);

    List<Student> GetStudentsUsedInAnalysis();
}

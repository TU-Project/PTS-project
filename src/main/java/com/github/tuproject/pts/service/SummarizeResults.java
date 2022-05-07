package com.github.tuproject.pts.service;

import com.github.tuproject.pts.data.entities.Student;
import com.github.tuproject.pts.data.entities.Student;

import java.util.ArrayList;
import java.util.List;

public interface SummarizeResults {

    List<Student> getSummarizedResults(String pathToStudentResultsFile1, String pathToStudentResultsFile2,
                                       String pathToStudentActivitiesFile);

}

package com.github.tuproject.pts.service.serviceImpl;

import com.github.tuproject.pts.data.entities.ResultFrequency;
import com.github.tuproject.pts.data.entities.Student;
import com.github.tuproject.pts.service.DataHandler;
import com.github.tuproject.pts.service.SummarizeResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SummarizeResultsImpl implements SummarizeResults {
    @Autowired
    DataHandler dataHandler;

    private List<Student> students;

    public List<Student> getSummarizedResults(String pathToStudentResultsFile1, String pathToStudentResultsFile2, String pathToStudentActivitiesFile){
        students = dataHandler.GetStudents(pathToStudentResultsFile1);
        students.addAll(dataHandler.GetStudents(pathToStudentResultsFile2));
        students = dataHandler.getSummarizedResults(dataHandler.GetStudentActivities(pathToStudentActivitiesFile), students);

        return students;
    }
}


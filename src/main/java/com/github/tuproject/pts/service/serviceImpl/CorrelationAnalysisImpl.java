package com.github.tuproject.pts.service.serviceImpl;

import com.github.tuproject.pts.data.entities.Student;
import com.github.tuproject.pts.service.CorrelationAnalysis;
import com.github.tuproject.pts.service.DataHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;

@Service
public class CorrelationAnalysisImpl implements CorrelationAnalysis {

    @Autowired
    DataHandler dataHandler;

    private List<Student> students;

    @Override
    public double GetCorrelationCoefficient(String pathToStudentResultsFile, String pathToStudentActivitiesFile) {
        try {
            students = dataHandler.GetStudents(pathToStudentResultsFile);
            students = dataHandler.SetUploadedFiles(students, dataHandler.GetStudentActivities(pathToStudentActivitiesFile));
        }
        catch (FileNotFoundException e){
            return 0.0;
        }

        return dataHandler.GetPearsonsCorrelation(students);
    }

    @Override
    public double GetCorrelationCoefficient(String pathToStudentResultsFile1, String pathToStudentResultsFile2, String pathToStudentActivitiesFile) {
        try {
            students = dataHandler.GetStudents(pathToStudentResultsFile1);
            students.addAll(dataHandler.GetStudents(pathToStudentResultsFile2));
            students = dataHandler.SetUploadedFiles(students, dataHandler.GetStudentActivities(pathToStudentActivitiesFile));
        }
        catch (FileNotFoundException e){
            return 0.0;
        }

        return dataHandler.GetPearsonsCorrelation(students);
    }

    @Override
    public List<Student> GetStudentsUsedInAnalysis() {
        return students;
    }

}

package com.github.tuproject.pts.service.serviceImpl;

import com.github.tuproject.pts.data.entities.Student;
import com.github.tuproject.pts.service.DataHandler;
import com.github.tuproject.pts.service.DispersionMeasures;
import org.apache.commons.lang3.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;

@Service
public class DispersionMeasuresImpl implements DispersionMeasures {
    @Autowired
    DataHandler dataHandler;

    private List<Student> students;

    @Override
    public Range<Double> Range(String pathToStudentResultsFile1, String pathToStudentResultsFile2,
                               String pathToStudentActivitiesFile, boolean isSingleYear) {
        loadStudents(pathToStudentResultsFile1, pathToStudentResultsFile2, pathToStudentActivitiesFile, isSingleYear);
        return dataHandler.GetRange(students);
    }

    @Override
    public double Variance(String pathToStudentResultsFile1, String pathToStudentResultsFile2,
                           String pathToStudentActivitiesFile, boolean isSingleYear) {
        loadStudents(pathToStudentResultsFile1, pathToStudentResultsFile2, pathToStudentActivitiesFile, isSingleYear);
        return dataHandler.GetVariance(students);
    }

    @Override
    public double StandardDeviation(String pathToStudentResultsFile1, String pathToStudentResultsFile2,
                                    String pathToStudentActivitiesFile, boolean isSingleYear) {
        loadStudents(pathToStudentResultsFile1, pathToStudentResultsFile2, pathToStudentActivitiesFile, isSingleYear);
        return dataHandler.GetStandardDeviation(students);
    }

    @Override
    public List<Student> GetStudentsUsedInAnalysis() {
        return students;
    }

    private void loadStudents(String pathToStudentResultsFile1, String pathToStudentResultsFile2,
                              String pathToStudentActivitiesFile, boolean isSingleYear) {
        try {
            students = dataHandler.GetStudents(pathToStudentResultsFile1);
            if (!isSingleYear) {
                students.addAll(dataHandler.GetStudents(pathToStudentResultsFile2));
            }
            students = dataHandler.SetUploadedFiles(students, dataHandler.GetStudentActivities(pathToStudentActivitiesFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

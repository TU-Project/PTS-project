package com.github.tuproject.pts.service.serviceImpl;

import com.github.tuproject.pts.data.entities.ResultFrequency;
import com.github.tuproject.pts.data.entities.Student;
import com.github.tuproject.pts.service.DataHandler;
import com.github.tuproject.pts.service.FrequencyDistribution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FrequencyDistributionImpl implements FrequencyDistribution {

    @Autowired
    DataHandler dataHandler;

    private List<Student> students;

    public ArrayList<ResultFrequency> getFrequencyDistribution(String pathToStudentResultsFile1, String pathToStudentResultsFile2,
                                                               String pathToStudentActivitiesFile, boolean isSingleYear) {
        loadStudents(pathToStudentResultsFile1, pathToStudentResultsFile2, pathToStudentActivitiesFile, isSingleYear);
        return dataHandler.getFrequencyDistribution(students);
    }

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

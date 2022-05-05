package com.github.tuproject.pts.service.serviceImpl;

import com.github.tuproject.pts.data.entities.StudentActivities;
import com.github.tuproject.pts.service.CentralTrend;
import com.github.tuproject.pts.service.DataHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;

@Service
public class CentralTrendImpl implements CentralTrend {

    @Autowired
    DataHandler dataHandler;

    public double GetMedian (String pathToStudentActivitiesFile) {
        List<StudentActivities> studentActivities = dataHandler.GetStudentActivities(pathToStudentActivitiesFile);
        return dataHandler.GetMedian(studentActivities);
    }

    public double GetMean(String pathToStudentActivitiesFile) {
        List<StudentActivities> studentActivities = dataHandler.GetStudentActivities(pathToStudentActivitiesFile);
        return dataHandler.GetMean(studentActivities);
    }

    public double[] GetMode(String pathToStudentActivitiesFile) {
        List<StudentActivities> studentActivities = dataHandler.GetStudentActivities(pathToStudentActivitiesFile);
        return dataHandler.GetMode(studentActivities);
    }

}

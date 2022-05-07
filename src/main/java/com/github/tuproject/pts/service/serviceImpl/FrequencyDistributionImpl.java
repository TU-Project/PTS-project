package com.github.tuproject.pts.service.serviceImpl;

import com.github.tuproject.pts.data.entities.ResultFrequency;
import com.github.tuproject.pts.data.entities.StudentActivities;
import com.github.tuproject.pts.service.DataHandler;
import com.github.tuproject.pts.service.FrequencyDistribution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FrequencyDistributionImpl implements FrequencyDistribution {

    @Autowired
    DataHandler dataHandler;


    public ArrayList<ResultFrequency> getFrequencyDistribution(String pathToStudentActivitiesFile){
        List<StudentActivities> studentActivities = dataHandler.GetStudentActivities(pathToStudentActivitiesFile);
        return dataHandler.getFrequencyDistribution(studentActivities);
    }
}

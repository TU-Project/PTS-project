package com.github.tuproject.pts.service;

import com.github.tuproject.pts.data.entities.ResultFrequency;
import com.github.tuproject.pts.data.entities.StudentActivities;

import java.util.ArrayList;
import java.util.List;

public interface FrequencyDistribution {

    ArrayList<ResultFrequency> getFrequencyDistribution(String studentActivitiesFilePath);

}

package com.github.tuproject.pts.service;

public interface CentralTrend {
    double GetMedian (String pathToStudentActivitiesFile);
    double GetMean(String pathToStudentActivitiesFile);
    double[] GetMode(String pathToStudentActivitiesFile);
}

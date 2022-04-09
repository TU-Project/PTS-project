package com.github.tuproject.pts.service;

import com.github.tuproject.pts.data.entities.Student;
import com.github.tuproject.pts.data.entities.StudentActivities;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.List;

public interface DataHandler {
    public List<Student> GetStudents(String path) throws FileNotFoundException;
    public List<StudentActivities> GetStudentActivities(String path) throws FileNotFoundException;
    public List<Student> SetUploadedFiles (List<Student> students, List<StudentActivities> activities);
    public double GetPearsonsCorrelation (List<Student> students);
}

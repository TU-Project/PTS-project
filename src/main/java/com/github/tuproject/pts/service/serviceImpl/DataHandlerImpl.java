package com.github.tuproject.pts.service.serviceImpl;

import com.github.tuproject.pts.data.entities.SortByResult;
import com.github.tuproject.pts.data.entities.Student;
import com.github.tuproject.pts.data.entities.StudentActivities;
import com.github.tuproject.pts.service.DataHandler;
import com.poiji.bind.Poiji;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DataHandlerImpl implements DataHandler {

    private final static String uploadedFileEventName = "A file has been uploaded.";
    private final static String uploadedFileDescription = "The user with id '%d' has uploaded a file to the submission";

    public List<Student> GetStudents(String path) throws  FileNotFoundException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file =new File(classLoader.getResource(path).getFile());

        return Poiji.fromExcel(file, Student.class);
    }

    @Override
    public List<StudentActivities> GetStudentActivities(String path) throws FileNotFoundException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file =new File(classLoader.getResource(path).getFile());

        return Poiji.fromExcel(file, StudentActivities.class);
    }

    public List<Student> SetUploadedFiles (List<Student> students, List<StudentActivities> activities){

        for (Student student : students){
            int studentId = student.getId();
            int uploadedFiles = 0;
            for (StudentActivities activity : activities){
                if (activity.getEventName().contains(uploadedFileEventName) && activity.getDescription().contains(String.format(uploadedFileDescription, studentId))){
                    uploadedFiles++;
                }
            }
            student.setUploadedFiles(uploadedFiles);
        }

        Collections.sort(students, new SortByResult());
        return students;
    }

    public double GetPearsonsCorrelation (List<Student> students){
        PearsonsCorrelation pearsonCorrelation = new PearsonsCorrelation();

        List<Double> results = new ArrayList<Double>();
        List<Double> uploadedFiles = new ArrayList<Double>();

        for (Student student : students){
            results.add(student.getResult());
            uploadedFiles.add(Double.valueOf(student.getUploadedFiles()));
        }

        double[] resultsArray = ArrayUtils.toPrimitive(results.toArray(new Double[0]));
        double[] uploadedFilesArray = ArrayUtils.toPrimitive(uploadedFiles.toArray(new Double[0]));

        return pearsonCorrelation.correlation(resultsArray, uploadedFilesArray);
    }
}

package com.github.tuproject.pts.service.serviceImpl;

import com.github.tuproject.pts.data.entities.ResultFrequency;
import com.github.tuproject.pts.data.entities.SortByResult;
import com.github.tuproject.pts.data.entities.Student;
import com.github.tuproject.pts.data.entities.StudentActivities;
import com.github.tuproject.pts.service.DataHandler;
import com.poiji.bind.Poiji;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.Range;
import org.apache.commons.math3.stat.Frequency;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;
import org.apache.commons.math3.stat.descriptive.rank.Median;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.StatUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DataHandlerImpl implements DataHandler {

    private final static String uploadedFileEventName = "A file has been uploaded.";
    private final static String uploadedFileDescription = "The user with id '%d' has uploaded a file to the submission";
    private final static String uploadedExercises = "Assignment: Качване на курсови задачи и проекти";
    private final static String fileSubmissionsContent = "File submissions";

    public List<Student> GetStudents(String path) {
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource(path).getFile());

        return Poiji.fromExcel(file, Student.class);
    }

    @Override
    public List<StudentActivities> GetStudentActivities(String path) {
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource(path).getFile());

        return Poiji.fromExcel(file, StudentActivities.class);
    }

    public List<Student> SetUploadedFiles(List<Student> students, List<StudentActivities> activities) {

        for (Student student : students) {
            int studentId = student.getId();
            int uploadedFiles = 0;
            for (StudentActivities activity : activities) {
                if (activity.getEventName().contains(uploadedFileEventName) && activity.getDescription().contains(String.format(uploadedFileDescription, studentId))) {
                    uploadedFiles++;
                }
            }
            student.setUploadedFiles(uploadedFiles);
        }

        Collections.sort(students, new SortByResult());
        return students;
    }

    public double GetPearsonsCorrelation(List<Student> students) {
        PearsonsCorrelation pearsonCorrelation = new PearsonsCorrelation();

        List<Double> results = new ArrayList<Double>();
        List<Double> uploadedFiles = new ArrayList<Double>();

        for (Student student : students) {
            results.add(student.getResult());
            uploadedFiles.add(Double.valueOf(student.getUploadedFiles()));
        }

        double[] resultsArray = ArrayUtils.toPrimitive(results.toArray(new Double[0]));
        double[] uploadedFilesArray = ArrayUtils.toPrimitive(uploadedFiles.toArray(new Double[0]));

        return pearsonCorrelation.correlation(resultsArray, uploadedFilesArray);
    }

    @Override
    public Range<Double> GetRange(List<Student> students) {
        List<Double> results = new ArrayList<>();
        for (Student student : students) {
            results.add(student.getResult());
        }

        double min = Collections.min(results);
        double max = Collections.max(results);

        return Range.between(min, max);
    }

    @Override
    public double GetVariance(List<Student> students) {
        SummaryStatistics statistics = new SummaryStatistics();
        for (Student student : students) {
            statistics.addValue(student.getResult());
        }

        return statistics.getVariance();
    }

    @Override
    public double GetStandardDeviation(List<Student> students) {
        SummaryStatistics statistics = new SummaryStatistics();
        for (Student student : students) {
            statistics.addValue(student.getResult());
        }

        return statistics.getStandardDeviation();
    }

    public ArrayList<ResultFrequency> getFrequencyDistribution(List<Student> students) {

        Frequency frequencies = new Frequency();
        students.forEach(student -> frequencies.addValue(student.getResult()));
        var list = new ArrayList<ResultFrequency>();
        frequencies.valuesIterator().forEachRemaining(x -> list.add(new ResultFrequency((Double) x, frequencies.getCount(x), frequencies.getPct(x))));

        return list;
    }

    public double GetMedian(List<StudentActivities> studentActivities){
        return new Median().evaluate(getUploadedExerciseActivities(studentActivities));
    }

    public double GetMean(List<StudentActivities> studentActivities){
        return new Mean().evaluate(getUploadedExerciseActivities(studentActivities));
    }

    public double[] GetMode(List<StudentActivities> studentActivities){
        return StatUtils.mode(getUploadedExerciseActivities(studentActivities));
    }



    private double[] getUploadedExerciseActivities (List<StudentActivities> studentActivities) {
        List<StudentActivities> uploadExerciseActivity = new ArrayList<StudentActivities>();

        for (int i =0 ; i < studentActivities.size(); i ++ ){
            if (!studentActivities.get(i).getEventContext().equals(uploadedExercises) && studentActivities.get(i).getComponent().contains(fileSubmissionsContent)){
                uploadExerciseActivity.add(studentActivities.get(i));
            }
        }

        double[] studentActivitiesArray = new double[uploadExerciseActivity.size()];

        for (int i =0 ; i < studentActivitiesArray.length; i ++ ){
            studentActivitiesArray[i] = Double.parseDouble(uploadExerciseActivity.get(i).getEventContext().split(" ")[4]);
        }

        return studentActivitiesArray;
    }
}
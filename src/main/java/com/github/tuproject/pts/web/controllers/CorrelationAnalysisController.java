package com.github.tuproject.pts.web.controllers;

import com.github.tuproject.pts.data.entities.Student;
import com.github.tuproject.pts.data.entities.StudentActivities;
import com.github.tuproject.pts.service.DataHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/correlationAnalysis")
public class CorrelationAnalysisController extends BaseController{

    private String year1StudentsFilePath = "Course_A_StudentsResults_Year_1.xlsx";
    private String year2StudentsFilePath = "Course_A_StudentsResults_Year_2.xlsx";
    private String studentActivitiesFilePath = "Logs_Course_A_StudentsActivities.xlsx";

    @Autowired
    DataHandler dataHandler;

    @GetMapping
    public ModelAndView mainPage(Model model) throws  FileNotFoundException {
        List<Student> studentsYear1 = dataHandler.GetStudents(year1StudentsFilePath);
        List<Student> studentsYear2 = dataHandler.GetStudents(year2StudentsFilePath);
        List<Student> studentsBothYears = new ArrayList<>();
        studentsBothYears.addAll(studentsYear1);
        studentsBothYears.addAll(studentsYear2);

        List <StudentActivities> studentActivities = dataHandler.GetStudentActivities(studentActivitiesFilePath);

        model.addAttribute("studentListYear1" , dataHandler.SetUploadedFiles(studentsYear1, studentActivities));
        model.addAttribute("correlationCoefficientYear1", dataHandler.GetPearsonsCorrelation(studentsYear1));

        model.addAttribute("studentListYear2" , dataHandler.SetUploadedFiles(studentsYear2, studentActivities));
        model.addAttribute("correlationCoefficientYear2", dataHandler.GetPearsonsCorrelation(studentsYear2));

        model.addAttribute("studentListBothYears" , dataHandler.SetUploadedFiles(studentsBothYears, studentActivities));
        model.addAttribute("correlationCoefficientBothYears", dataHandler.GetPearsonsCorrelation(studentsBothYears));

        return super.view("correlationAnalysis");
    }

}

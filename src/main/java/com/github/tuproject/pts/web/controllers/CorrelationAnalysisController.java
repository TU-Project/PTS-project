package com.github.tuproject.pts.web.controllers;

import com.github.tuproject.pts.data.entities.Student;
import com.github.tuproject.pts.service.CorrelationAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/correlationAnalysis")
public class CorrelationAnalysisController extends BaseController{

    private String year1StudentsFilePath = "Course_A_StudentsResults_Year_1.xlsx";
    private String year2StudentsFilePath = "Course_A_StudentsResults_Year_2.xlsx";
    private String studentActivitiesFilePath = "Logs_Course_A_StudentsActivities.xlsx";

    private static List<Student> cachedStudentListYear1;
    private static List<Student> cachedStudentListYear2;
    private static List<Student> cachedStudentListYearBoth;

    private static Double cachedCorrelationCoefficientYear1;
    private static Double cachedCorrelationCoefficientYear2;
    private static Double cachedCorrelationCoefficientYearBoth;

    private static boolean cached = false;


    @Autowired
    CorrelationAnalysis correlationAnalysis;

    @GetMapping
    public ModelAndView mainPage(Model model) {

        cacheResults();

        model.addAttribute("correlationCoefficientYear1", cachedCorrelationCoefficientYear1);
        model.addAttribute("studentListYear1" , cachedStudentListYear1);

        model.addAttribute("correlationCoefficientYear2", cachedCorrelationCoefficientYear2);
        model.addAttribute("studentListYear2" , cachedStudentListYear2);

        model.addAttribute("correlationCoefficientBothYears", cachedCorrelationCoefficientYearBoth);
        model.addAttribute("studentListBothYears" , cachedStudentListYearBoth);

        return super.view("correlationAnalysis");
    }

    private void cacheResults(){

        if (cached){
            return;
        }

        cachedCorrelationCoefficientYear1 = correlationAnalysis.GetCorrelationCoefficient(year1StudentsFilePath,studentActivitiesFilePath);
        cachedStudentListYear1 = correlationAnalysis.GetStudentsUsedInAnalysis();

        cachedCorrelationCoefficientYear2 = correlationAnalysis.GetCorrelationCoefficient(year2StudentsFilePath,studentActivitiesFilePath);
        cachedStudentListYear2 = correlationAnalysis.GetStudentsUsedInAnalysis();

        cachedCorrelationCoefficientYearBoth = correlationAnalysis.GetCorrelationCoefficient(year1StudentsFilePath,year2StudentsFilePath,studentActivitiesFilePath);
        cachedStudentListYearBoth = correlationAnalysis.GetStudentsUsedInAnalysis();

        cached=true;

        return;
    }

}

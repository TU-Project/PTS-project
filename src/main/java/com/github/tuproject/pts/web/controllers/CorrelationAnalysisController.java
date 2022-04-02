package com.github.tuproject.pts.web.controllers;

import com.github.tuproject.pts.data.entities.Student;
import com.github.tuproject.pts.data.entities.StudentActivities;
import com.github.tuproject.pts.service.CorrelationAnalysis;
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
    CorrelationAnalysis correlationAnalysis;

    @GetMapping
    public ModelAndView mainPage(Model model) {

        model.addAttribute("correlationCoefficientYear1", correlationAnalysis.GetCorrelationAnalysis(year1StudentsFilePath,studentActivitiesFilePath));
        model.addAttribute("studentListYear1" , correlationAnalysis.GetStudentsUsedInAnalysis());

        model.addAttribute("correlationCoefficientYear2", correlationAnalysis.GetCorrelationAnalysis(year2StudentsFilePath,studentActivitiesFilePath));
        model.addAttribute("studentListYear2" , correlationAnalysis.GetStudentsUsedInAnalysis());

        model.addAttribute("correlationCoefficientBothYears", correlationAnalysis.GetCorrelationAnalysis(year1StudentsFilePath,year2StudentsFilePath,studentActivitiesFilePath));
        model.addAttribute("studentListBothYears" , correlationAnalysis.GetStudentsUsedInAnalysis());

        return super.view("correlationAnalysis");
    }

}

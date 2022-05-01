package com.github.tuproject.pts.web.controllers;

import com.github.tuproject.pts.data.entities.Student;
import com.github.tuproject.pts.service.DispersionMeasures;
import org.apache.commons.lang3.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/dispersionMeasures")
public class DispersionMeasuresController extends BaseController{
    private static List<Student> cachedStudentListYear1;
    private static List<Student> cachedStudentListYear2;
    private static List<Student> cachedStudentListYearBoth;
    boolean cached = false;

    private static Range<Double> rangeYear1;
    private static Range<Double> rangeYear2;
    private static Range<Double> rangeYearBoth;

    private static Double varianceYear1;
    private static Double varianceYear2;
    private static Double varianceYearBoth;

    private static Double standardDeviationYear1;
    private static Double standardDeviationYear2;
    private static Double standardDeviationYearBoth;

    @Autowired
    DispersionMeasures dispersionMeasures;

    @GetMapping
    public ModelAndView mainPage(Model model){
        cacheResults();

        model.addAttribute("rangeYear1", rangeYear1);
        model.addAttribute("rangeYear2", rangeYear2);
        model.addAttribute("rangeYearBoth", rangeYearBoth);

        model.addAttribute("varianceYear1", varianceYear1);
        model.addAttribute("varianceYear2", varianceYear2);
        model.addAttribute("varianceYearBoth", varianceYearBoth);

        model.addAttribute("standardDeviationYear1", standardDeviationYear1);
        model.addAttribute("standardDeviationYear2", standardDeviationYear2);
        model.addAttribute("standardDeviationYearBoth", standardDeviationYearBoth);

        return super.view("dispersionMeasures");
    }

    private void cacheResults(){
        if (cached){
            return;
        }
        rangeYear1 = dispersionMeasures.Range(year1StudentsFilePath, null, studentActivitiesFilePath, true);
        varianceYear1 = dispersionMeasures.Variance(year1StudentsFilePath, null, studentActivitiesFilePath, true);
        standardDeviationYear1 = dispersionMeasures.StandardDeviation(year1StudentsFilePath, null, studentActivitiesFilePath, true);
        cachedStudentListYear1 = dispersionMeasures.GetStudentsUsedInAnalysis();

        rangeYear2 = dispersionMeasures.Range(year2StudentsFilePath, null, studentActivitiesFilePath, true);
        varianceYear2 = dispersionMeasures.Variance(year2StudentsFilePath, null, studentActivitiesFilePath, true);
        standardDeviationYear2 = dispersionMeasures.StandardDeviation(year2StudentsFilePath, null, studentActivitiesFilePath, true);
        cachedStudentListYear2 = dispersionMeasures.GetStudentsUsedInAnalysis();

        rangeYearBoth = dispersionMeasures.Range(year1StudentsFilePath, year2StudentsFilePath, studentActivitiesFilePath, false);
        varianceYearBoth = dispersionMeasures.Variance(year1StudentsFilePath, year2StudentsFilePath, studentActivitiesFilePath, false);
        standardDeviationYearBoth = dispersionMeasures.StandardDeviation(year1StudentsFilePath, year2StudentsFilePath, studentActivitiesFilePath, false);
        cachedStudentListYearBoth = dispersionMeasures.GetStudentsUsedInAnalysis();

        cached=true;
    }

}

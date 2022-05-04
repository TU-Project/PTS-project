package com.github.tuproject.pts.web.controllers;

import com.github.tuproject.pts.data.entities.ResultFrequency;
import com.github.tuproject.pts.data.entities.Student;
import com.github.tuproject.pts.service.FrequencyDistribution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/frequencyDistribution")
public class FrequencyDistributionController extends BaseController {

    private static List<Student> cachedStudentListYear1;
    private static List<Student> cachedStudentListYear2;
    private static List<Student> cachedStudentListYearBoth;
    private static ArrayList<ResultFrequency> frequencyDistributionY1;
    private static ArrayList<ResultFrequency> frequencyDistributionY2;
    private static ArrayList<ResultFrequency> frequencyDistributionBoth;
    boolean cached = false;

    @Autowired
    FrequencyDistribution frequencyDistribution;

    @GetMapping
    public ModelAndView mainPage(Model model) {
        cacheResults();

        model.addAttribute("frequencyDistributionY1", frequencyDistributionY1);
        model.addAttribute("frequencyDistributionY2", frequencyDistributionY2);
        model.addAttribute("frequencyDistributionBoth", frequencyDistributionBoth);
        model.addAttribute("studentListYear1", cachedStudentListYear1);
        model.addAttribute("studentListYear2", cachedStudentListYear2);
        model.addAttribute("studentListBothYears", cachedStudentListYearBoth);

        return super.view("frequencyDistribution");
    }

    private void cacheResults() {
        if (cached) {
            return;
        }
        frequencyDistributionY1 = frequencyDistribution.getFrequencyDistribution(year1StudentsFilePath, null, studentActivitiesFilePath, true);
        cachedStudentListYear1 = frequencyDistribution.GetStudentsUsedInAnalysis();

        frequencyDistributionY2 = frequencyDistribution.getFrequencyDistribution(year2StudentsFilePath, null, studentActivitiesFilePath, true);
        cachedStudentListYear2 = frequencyDistribution.GetStudentsUsedInAnalysis();

        frequencyDistributionBoth = frequencyDistribution.getFrequencyDistribution(year1StudentsFilePath, year2StudentsFilePath, studentActivitiesFilePath, false);
        cachedStudentListYearBoth = frequencyDistribution.GetStudentsUsedInAnalysis();

        cached = true;
    }
}

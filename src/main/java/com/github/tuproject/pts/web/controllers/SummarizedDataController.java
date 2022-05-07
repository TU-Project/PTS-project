package com.github.tuproject.pts.web.controllers;

import com.github.tuproject.pts.data.entities.Student;
import com.github.tuproject.pts.service.DispersionMeasures;
import com.github.tuproject.pts.service.SummarizeResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/summarizedData")
public class SummarizedDataController extends BaseController {

    private static List<Student> cachedStudentListYear1;

    private static boolean cached = false;

    @Autowired
    SummarizeResults summarizeResults;

    @GetMapping
    public ModelAndView mainPage(Model model){
        cacheResults();

        model.addAttribute("studentListFromYear1" , cachedStudentListYear1);

        return super.view("summarizedData");
    }

    private void cacheResults() {
        if (cached){
            return;
        }

        cachedStudentListYear1 = summarizeResults.getSummarizedResults(year1StudentsFilePath,year2StudentsFilePath,studentActivitiesFilePath);

        cached=true;

        return;
    }

}

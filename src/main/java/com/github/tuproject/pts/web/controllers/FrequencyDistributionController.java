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


    private static ArrayList<ResultFrequency> frequencyDistributionTable;

    boolean cached = false;

    @Autowired
    FrequencyDistribution frequencyDistribution;

    @GetMapping
    public ModelAndView mainPage(Model model) {
        cacheResults();

        model.addAttribute("frequencyDistribution", frequencyDistributionTable);

        return super.view("frequencyDistribution");
    }

    private void cacheResults() {
        if (cached) {
            return;
        }
        frequencyDistributionTable = frequencyDistribution.getFrequencyDistribution(studentActivitiesFilePath);

        cached = true;

        return;
    }
}

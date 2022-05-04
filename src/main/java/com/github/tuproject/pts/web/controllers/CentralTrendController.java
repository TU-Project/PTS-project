package com.github.tuproject.pts.web.controllers;

import com.github.tuproject.pts.data.entities.Student;
import com.github.tuproject.pts.service.CentralTrend;
import com.github.tuproject.pts.service.CorrelationAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/centralTrend")
public class CentralTrendController extends BaseController{

    private static double cachedMedian;
    private static double cachedMean;
    private static double[] cachedMode;

    private static boolean cached = false;

    @Autowired
    CentralTrend centralTrend;

    @GetMapping
    public ModelAndView mainPage(Model model){
        cacheResults();
        model.addAttribute("median", cachedMedian);
        model.addAttribute("mean", cachedMean);
        model.addAttribute("mode", cachedMode);
        return super.view("centralTrend");
    }

    private void cacheResults(){

        if (cached){
            return;
        }

        cachedMedian = centralTrend.GetMedian(studentActivitiesFilePath);
        cachedMean = centralTrend.GetMean(studentActivitiesFilePath);
        cachedMode = centralTrend.GetMode(studentActivitiesFilePath);

        cached=true;

        return;
    }

}

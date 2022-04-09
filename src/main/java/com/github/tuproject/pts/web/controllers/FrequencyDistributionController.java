package com.github.tuproject.pts.web.controllers;

import com.github.tuproject.pts.service.DispersionMeasures;
import com.github.tuproject.pts.service.FrequencyDistribution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/frequencyDistribution")
public class FrequencyDistributionController extends BaseController{

    @Autowired
    FrequencyDistribution frequencyDistribution;

    @GetMapping
    public ModelAndView mainPage(Model model){
        return super.view("frequencyDistribution");
    }


}

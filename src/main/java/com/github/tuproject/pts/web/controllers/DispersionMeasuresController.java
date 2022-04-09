package com.github.tuproject.pts.web.controllers;

import com.github.tuproject.pts.service.CentralTrend;
import com.github.tuproject.pts.service.DispersionMeasures;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/dispersionMeasures")
public class DispersionMeasuresController extends BaseController{

    @Autowired
    DispersionMeasures dispersionMeasures;

    @GetMapping
    public ModelAndView mainPage(Model model){
        return super.view("dispersionMeasures");
    }

}

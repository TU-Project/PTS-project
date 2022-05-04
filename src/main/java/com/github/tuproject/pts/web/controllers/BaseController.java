package com.github.tuproject.pts.web.controllers;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.ModelAndView;

public abstract class BaseController {
    protected String year1StudentsFilePath = new ClassPathResource("Course_A_StudentsResults_Year_1.xlsx").getPath();
    protected String year2StudentsFilePath = new ClassPathResource("Course_A_StudentsResults_Year_2.xlsx").getPath();
    protected String studentActivitiesFilePath = new ClassPathResource("Logs_Course_A_StudentsActivities.xlsx").getPath();

    public ModelAndView view(String viewName, ModelAndView modelAndView){
        modelAndView.setViewName(viewName);

        return modelAndView;
    }
    public ModelAndView view(String viewName){ return this.view(viewName, new ModelAndView()); }

    public ModelAndView redirect(String url){ return this.view("redirect:" + url); }

}
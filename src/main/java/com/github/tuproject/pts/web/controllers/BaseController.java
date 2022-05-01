package com.github.tuproject.pts.web.controllers;

import org.springframework.web.servlet.ModelAndView;

public abstract class BaseController {
    protected String year1StudentsFilePath = "Course_A_StudentsResults_Year_1.xlsx";
    protected String year2StudentsFilePath = "Course_A_StudentsResults_Year_2.xlsx";
    protected String studentActivitiesFilePath = "Logs_Course_A_StudentsActivities.xlsx";

    public ModelAndView view(String viewName, ModelAndView modelAndView){
        modelAndView.setViewName(viewName);

        return modelAndView;
    }
    public ModelAndView view(String viewName){ return this.view(viewName, new ModelAndView()); }

    public ModelAndView redirect(String url){ return this.view("redirect:" + url); }

}
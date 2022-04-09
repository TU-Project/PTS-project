package com.github.tuproject.pts.data.entities;

import com.poiji.annotation.ExcelCellName;

public class StudentActivities {

    @ExcelCellName("Event context")
    private String eventContext;
    @ExcelCellName("Component")
    private String component;
    @ExcelCellName("Event name")
    private String eventName;
    @ExcelCellName("Description")
    private String description;

    public String getEventContext() {
        return eventContext;
    }

    public void setEventContext(String eventContext) {
        this.eventContext = eventContext;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}

package com.github.tuproject.pts.data.entities;

public class ResultFrequency {

    private Double key;
    private long absoluteFrequency;
    private double relativeFrequency;

    public ResultFrequency(Double  key, long absoluteFrequency, double relativeFrequency) {
        this.key = key;
        this.absoluteFrequency = absoluteFrequency;
        this.relativeFrequency = relativeFrequency;
    }

    public Double getKey() {
        return key;
    }

    public void setKey(Double key) {
        this.key = key;
    }

    public long getAbsoluteFrequency() {
        return absoluteFrequency;
    }

    public void setAbsoluteFrequency(long absoluteFrequency) {
        this.absoluteFrequency = absoluteFrequency;
    }

    public double getRelativeFrequency() {
        return relativeFrequency;
    }

    public void setRelativeFrequency(double relativeFrequency) {
        this.relativeFrequency = relativeFrequency;
    }
}



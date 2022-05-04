package com.github.tuproject.pts.data.entities;

public class ResultFrequency {

    private double key;
    private long absoluteFrequency;
    private double relativeFrequency;

    public ResultFrequency(double key, long absoluteFrequency, double relativeFrequency) {
        this.key = key;
        this.absoluteFrequency = absoluteFrequency;
        this.relativeFrequency = relativeFrequency;
    }

    public double getKey() {
        return key;
    }

    public void setKey(double key) {
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



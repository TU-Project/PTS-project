package com.github.tuproject.pts.data.entities;

import java.util.Comparator;

public class SortByResult implements Comparator<Student>
{
    // Used for sorting in ascending order of the results
    public int compare(Student a, Student b)
    {
        return Double.compare(a.getResult() , b.getResult());
    }
}

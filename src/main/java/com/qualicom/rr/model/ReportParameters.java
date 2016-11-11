package com.qualicom.rr.model;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by x110277 on 11/09/2016.
 */
public class ReportParameters extends TreeMap<String,String> {

    public ReportParameters() {
        super();
    }

    public ReportParameters(Map<String,String> map) {
        super(map);
    }

    public ReportParameters(String paramListString, String valueListString) {
        super();
        String[] paramArray = paramListString.trim().split(",");
        String[] valueArray = valueListString.trim().split(",");
        for (int i = 0; i < Math.min(paramArray.length, valueArray.length); i++) {
            put(paramArray[i],valueArray[i]);
        }
    }
}
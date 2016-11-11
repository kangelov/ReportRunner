package com.qualicom.rr.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by x110277 on 11/09/2016.
 */
@XmlRootElement
public class ReportColumns extends ArrayList<String> {

    public ReportColumns() {

    }

    public ReportColumns(ArrayList<String> list) {
        super(list);
    }

    public ReportColumns(String columnListString) {
        super();
        String[] columns = columnListString.trim().split(",");
        addAll(Arrays.asList(columns));
    }

    @XmlElement(name = "header")
    public List<String> getReportColumns() {
        return this;
    }

}

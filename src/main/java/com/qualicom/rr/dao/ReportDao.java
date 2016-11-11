package com.qualicom.rr.dao;

import com.qualicom.rr.model.Report;
import com.qualicom.rr.model.ReportColumns;
import com.qualicom.rr.model.ReportRow;
import com.qualicom.rr.model.ReportParameters;

import java.util.List;

/**
 * Created by x110277 on 11/09/2016.
 */
public interface ReportDao {

    Report createReport();

    void setReportParameters(ReportParameters parameters);
}

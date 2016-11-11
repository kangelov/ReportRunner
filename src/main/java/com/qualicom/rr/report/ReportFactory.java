package com.qualicom.rr.report;

import com.qualicom.rr.dao.ReportDao;
import com.qualicom.rr.model.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

/**
 * All the report-specific logic goes here.
 * Created by x110277 on 11/10/2016.
 */
@Component("reportFactory")
public class ReportFactory implements ApplicationContextAware  {

    @Autowired
    private ApplicationContext appCtx;
    public void setApplicationContext(ApplicationContext appCtx) {
        this.appCtx = appCtx;
    }

    public Date getReportDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        return calendar.getTime();
    }

    public Report createSuccessPaymentReport() {
        //Reports are recreated from scratch for every request, so they cannot be autowired into long-lived objects.
        ReportDao successReport = (ReportDao)appCtx.getBean("successReportDao");
        return successReport.createReport();
    }

    public Report createFailurePaymentReport() {
        //Reports are recreated from scratch for every request, so they cannot be autowired into long-lived objects.
        ReportDao failureReport = (ReportDao)appCtx.getBean("failureReportDao");
        return failureReport.createReport();
    }

    public Report createDetailedFailurePaymentReport() {
        //Reports are recreated from scratch for every request, so they cannot be autowired into long-lived objects.
        ReportDao detailFailureReport = (ReportDao)appCtx.getBean("detailedFailureReportDao");
        return detailFailureReport.createReport();
    }

}

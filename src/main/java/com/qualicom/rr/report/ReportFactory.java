package com.qualicom.rr.report;

import com.qualicom.rr.dao.ReportDao;
import com.qualicom.rr.model.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Generates all reports configured in the system by instantiating them specially every time they are needed.
 *
 * Created by x110277 on 11/10/2016.
 */
@Component("reportFactory")
public class ReportFactory implements ApplicationContextAware  {

    @Autowired
    private ApplicationContext appCtx;

    private List<String> reportBeanList;

    public void setApplicationContext(ApplicationContext appCtx) {
        this.appCtx = appCtx;
    }

    public Date getReportDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        return calendar.getTime();
    }

    public List<Report> generateReports() {
        ArrayList<Report> reportList = new ArrayList<Report>();
        for(String reportBean : reportBeanList) {
            reportList.add(createPaymentReport(reportBean));
        }
        return reportList;
    }

    private Report createPaymentReport(String beanName) {
        //Reports are recreated from scratch for every request, so they cannot be autowired into long-lived objects.
        ReportDao report = (ReportDao)appCtx.getBean(beanName);
        return report.createReport();
    }

    public List<String> getReportBeanList() {
        return reportBeanList;
    }

    public void setReportBeanList(List<String> reportBeanList) {
        this.reportBeanList = reportBeanList;
    }
}

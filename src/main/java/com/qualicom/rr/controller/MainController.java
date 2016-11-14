package com.qualicom.rr.controller;

import com.qualicom.rr.mail.Mailer;
import com.qualicom.rr.marshall.MarshallAdapter;
import com.qualicom.rr.model.MarshalledReports;
import com.qualicom.rr.model.Report;
import com.qualicom.rr.report.ReportFactory;
import com.qualicom.rr.report.ReportMarshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by x110277 on 11/09/2016.
 */
@Controller("mainController")
public class MainController {

    @Autowired
    private ReportFactory reportFactory;

    @Autowired
    private ReportMarshaller reportMarshaller;

    @Autowired
    private Mailer mailer;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String beginHere() {
        return "redirect:main";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public ModelAndView main() throws Exception {

        runReports();

        ModelAndView model = new ModelAndView();
        model.addObject("title","Qualicom Report Runner");
        model.addObject("message","Nothing here yet.");
        model.setViewName("main");
        return model;
    }

    public void runReports() throws Exception {

        List<Report> reportList = reportFactory.generateReports();

        List<MarshalledReports> marshalledReports = reportMarshaller.marshallReports(reportList);

        mailer.sendMail(marshalledReports);
    }
}

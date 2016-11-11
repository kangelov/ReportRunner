package com.qualicom.rr.controller;

import com.qualicom.rr.report.ReportFactory;
import com.qualicom.rr.report.ReportMarshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 * Created by x110277 on 11/09/2016.
 */
@Controller
public class MainController {

    @Autowired
    private ReportFactory reportFactory;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String beginHere() {
        return "redirect:main";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public ModelAndView main() throws Exception {
        System.out.println("SUCCESS: " + ReportMarshaller.convertToXmlString(reportFactory.createSuccessPaymentReport()));
        System.out.println("FAILURE: " + ReportMarshaller.convertToXmlString(reportFactory.createFailurePaymentReport()));
        System.out.println("DETAIL FAILURE: " + ReportMarshaller.convertToXmlString(reportFactory.createDetailedFailurePaymentReport()));

        ModelAndView model = new ModelAndView();
        model.addObject("title","Qualicom Report Runner");
        model.addObject("message","Nothing here yet.");
        model.setViewName("main");
        return model;
    }
}

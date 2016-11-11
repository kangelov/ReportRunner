package com.qualicom.rr.report;

import com.qualicom.rr.model.Report;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * A generic converter to convert your report to XML.
 *
 * Created by x110277 on 11/10/2016.
 */
public class ReportMarshaller {

    private ReportMarshaller() {

    }

    public static InputStream convertToXml(Report report) throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            JAXBContext jc = JAXBContext.newInstance(Report.class);
            Marshaller m = jc.createMarshaller();
            m.marshal(report, output);
        } catch (JAXBException e) {
            throw new Exception("failed to convert report to xml stream.",e);
        } finally {
            output.close();
        }
        return new ByteArrayInputStream(output.toByteArray());
    }

    public static String convertToXmlString(Report report) throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            JAXBContext jc = JAXBContext.newInstance(Report.class);
            Marshaller m = jc.createMarshaller();
            m.marshal(report, output);
        } catch (JAXBException e) {
            throw new Exception("failed to convert report to xml stream.",e);
        } finally {
            output.close();
        }
        return output.toString("UTF-8");
    }
}

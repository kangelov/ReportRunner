package com.qualicom.rr.marshall.impl;

import com.qualicom.rr.marshall.MarshallAdapter;
import com.qualicom.rr.model.Report;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;

/**
 * Created by x110277 on 11/14/2016.
 */
@Component("xmlMarshallAdapter")
public class XMLMarshallAdapter implements MarshallAdapter {

    @Override
    public byte[] generateReports(Report... reports) throws Exception {
        if (reports == null || reports.length != 1)
            throw new Exception("Exactly one report at a time supported for XML marshalling. If you need to marshall more than one, please call this method repeatedly.");
        Report report = reports[0];

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
        return output.toByteArray();
    }

    @Override
    public boolean canHandleMultipleReports() {
        return false;
    }

    @Override
    public String getMimeType() {
        return "application/xml";
    }

    @Override
    public String getFileExtension() {
        return "xml";
    }
}

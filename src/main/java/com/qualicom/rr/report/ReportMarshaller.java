package com.qualicom.rr.report;

import com.qualicom.rr.model.Report;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.net.URI;

/**
 * A generic converter to convert your report to XML and PDF.
 *
 * Created by x110277 on 11/10/2016.
 */
public class ReportMarshaller {

    private static final String APACHE_FOP_CONFIG_FILE="fopconfig.xml";
    private static final String APACHE_FOP_REPORT_XSLT_FILE="report.xslt";

    private static FopFactory fopFactory = null;
    static {
        InputStream confStream = ReportMarshaller.class.getClassLoader().getResourceAsStream(APACHE_FOP_CONFIG_FILE);
        URI uri = URI.create(".");
        try {
            fopFactory = FopFactory.newInstance(uri,confStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


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

    public static byte[] createPDFReport(Report report) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {

            FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
            foUserAgent.setTitle(report.getTitle());

            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, outputStream);


            TransformerFactory factory = TransformerFactory.newInstance();

            Source xslt = new StreamSource(ReportMarshaller.class.getClassLoader().getResourceAsStream(APACHE_FOP_REPORT_XSLT_FILE));
            Transformer transformer = factory.newTransformer(xslt);

            Source src = new StreamSource(convertToXml(report));

            Result res = new SAXResult(fop.getDefaultHandler());
            transformer.transform(src, res);

        } catch (Exception e) {
            throw e;
        } finally {
            outputStream.close();
        }

        return outputStream.toByteArray();
    }
}

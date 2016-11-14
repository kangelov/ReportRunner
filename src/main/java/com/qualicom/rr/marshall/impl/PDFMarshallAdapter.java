package com.qualicom.rr.marshall.impl;

import com.qualicom.rr.marshall.MarshallAdapter;
import com.qualicom.rr.model.Report;
import com.qualicom.rr.report.ReportMarshaller;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URI;

/**
 * Created by x110277 on 11/14/2016.
 */
@Component("pdfMarshallAdapter")
public class PDFMarshallAdapter implements MarshallAdapter{

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

    @Autowired
    @Qualifier("xmlMarshallAdapter")
    private MarshallAdapter xmlMarshallAdapter;

    @Override
    public byte[] generateReports(Report... reports) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        if (reports == null || reports.length != 1)
            throw new Exception("Exactly one report at a time supported for PDF marshalling. If you need to marshall more than one, please call this method repeatedly.");
        Report report = reports[0];
        try {

            FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
            foUserAgent.setTitle(report.getTitle());

            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, outputStream);


            TransformerFactory factory = TransformerFactory.newInstance();

            Source xslt = new StreamSource(ReportMarshaller.class.getClassLoader().getResourceAsStream(APACHE_FOP_REPORT_XSLT_FILE));
            Transformer transformer = factory.newTransformer(xslt);

            Source src = new StreamSource(new ByteArrayInputStream(xmlMarshallAdapter.generateReports(report)));

            Result res = new SAXResult(fop.getDefaultHandler());
            transformer.transform(src, res);

        } catch (Exception e) {
            throw e;
        } finally {
            outputStream.close();
        }

        return outputStream.toByteArray();
    }

    @Override
    public boolean canHandleMultipleReports() {
        return false;
    }

    @Override
    public String getMimeType() {
        return "application/pdf";
    }

    @Override
    public String getFileExtension() {
        return "pdf";
    }
}

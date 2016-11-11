package com.qualicom.rr.mail;

import com.qualicom.rr.model.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.activation.MimeType;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import java.io.*;
import java.util.Map;

/**
 * Created by x110277 on 11/11/2016.
 */
public class Mailer {

    private Message message;

    private String mailFrom;

    private String mailTo;

    private String mailSubject;

    private String mailText;

    public void sendMail(final Map<Report,byte[]> pdfAttachments) throws Exception {
        message.setFrom(new InternetAddress(mailFrom));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(mailTo));
        message.setSubject(mailSubject);

        //Add the e-mail text
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText(mailText);

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        //Now send attachments
        for(final Report report : pdfAttachments.keySet()) {
            messageBodyPart = new MimeBodyPart();
            DataSource source = new DataSource() {

                @Override
                public InputStream getInputStream() throws IOException {
                    return new ByteArrayInputStream(pdfAttachments.get(report));
                }

                @Override
                public OutputStream getOutputStream() throws IOException {
                    return new ByteArrayOutputStream();
                }

                @Override
                public String getContentType() {
                    return "application/pdf";
                }

                @Override
                public String getName() {
                    return report.getName();
                }
            };
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(source.getName() + ".pdf");
            multipart.addBodyPart(messageBodyPart);
        }


        message.setContent(multipart);

        Transport.send(message);
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getMailFrom() {
        return mailFrom;
    }

    public void setMailFrom(String mailFrom) {
        this.mailFrom = mailFrom;
    }

    public String getMailTo() {
        return mailTo;
    }

    public void setMailTo(String mailTo) {
        this.mailTo = mailTo;
    }

    public String getMailSubject() {
        return mailSubject;
    }

    public void setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject;
    }

    public String getMailText() {
        return mailText;
    }

    public void setMailText(String mailText) {
        this.mailText = mailText;
    }
}

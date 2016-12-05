package com.qualicom.rr.mail;

import com.qualicom.rr.model.MarshalledReports;;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import java.io.*;
import java.util.List;

/**
 * Created by x110277 on 11/11/2016.
 */
public class Mailer {

    private Message message;

    private String mailFrom;

    private String mailTo;

    private String mailSubject;

    private String mailText;

    public void sendMail(final String subjectAddon, final List<MarshalledReports> attachments) throws Exception {
        message.setFrom(new InternetAddress(mailFrom));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(mailTo));
        message.setSubject(mailSubject + subjectAddon);

        //Add the e-mail text
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText(mailText);

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        //Now send attachments
        for(final MarshalledReports marshalledReport : attachments) {
            messageBodyPart = new MimeBodyPart();
            DataSource source = new DataSource() {

                @Override
                public InputStream getInputStream() throws IOException {
                    return new ByteArrayInputStream(marshalledReport.getMarshalledReports());
                }

                @Override
                public OutputStream getOutputStream() throws IOException {
                    return new ByteArrayOutputStream();
                }

                @Override
                public String getContentType() {
                    return marshalledReport.getMimeType();
                }

                @Override
                public String getName() {
                    return marshalledReport.getName();
                }
            };
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(marshalledReport.getName() + "." + marshalledReport.getFileExtension());
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

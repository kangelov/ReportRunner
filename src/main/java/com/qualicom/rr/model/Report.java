package com.qualicom.rr.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.List;

/**
 * A POJO representation of a report for JAX-B to convert to XML. This is an example what the XML looks like:
 *  <pre>

 <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
 <report>
    <headers>
        <header>transDt</header>
        <header>transTypeCd</header>
        <header>transReasonDesc</header>
        <header>numTrans</header>
    </headers>
    <data>
        <row>
            <value>2016-11-09 00:00:00.0</value>
            <value>CreditCard</value>
            <value>PURCHASE</value>
            <value>2</value>
        </row>
    </data>
 </report>

 *  </pre>
 * Created by x110277 on 11/10/2016.
 */
@XmlRootElement
@XmlSeeAlso({ReportRow.class})
public class Report {

    private ReportColumns header;

    private List<ReportRow> data;

    public Report() {

    }

    public Report(ReportColumns header, List<ReportRow> data) {
        setHeader(header);
        setData(data);
    }

    @XmlElementWrapper(name = "data")
    @XmlElement(name = "row")
    public List<ReportRow> getData() {
        return data;
    }

    public void setData(List<ReportRow> data) {
        this.data = data;
    }

    @XmlElementWrapper(name = "headers")
    @XmlElement(name="header")
    public ReportColumns getHeader() {
        return header;
    }

    public void setHeader(ReportColumns header) {
        this.header = header;
    }
}

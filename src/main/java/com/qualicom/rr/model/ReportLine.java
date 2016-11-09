package com.qualicom.rr.model;

import java.util.Date;

/**
 * Created by x110277 on 11/09/2016.
 */
public class ReportLine {

    private Date transDt;

    private String transTypeCd;

    private String transReasonDesc;

    private String responseCd;

    private int transStatusId;

    private int numTrans;

    public Date getTransDt() {
        return transDt;
    }

    public void setTransDt(Date transDt) {
        this.transDt = transDt;
    }

    public String getTransTypeCd() {
        return transTypeCd;
    }

    public void setTransTypeCd(String transTypeCd) {
        this.transTypeCd = transTypeCd;
    }

    public String getTransReasonDesc() {
        return transReasonDesc;
    }

    public void setTransReasonDesc(String transReasonDesc) {
        this.transReasonDesc = transReasonDesc;
    }

    public String getResponseCd() {
        return responseCd;
    }

    public void setResponseCd(String responseCd) {
        this.responseCd = responseCd;
    }

    public int getTransStatusId() {
        return transStatusId;
    }

    public void setTransStatusId(int transStatusId) {
        this.transStatusId = transStatusId;
    }

    public int getNumTrans() {
        return numTrans;
    }

    public void setNumTrans(int numTrans) {
        this.numTrans = numTrans;
    }
}

package com.qualicom.rr.marshall;

import com.qualicom.rr.model.Report;

/**
 * Created by x110277 on 11/14/2016.
 */
public interface MarshallAdapter {

    byte[] generateReports(Report... reports) throws Exception;

    boolean canHandleMultipleReports();

    String getMimeType();

    String getFileExtension();
}

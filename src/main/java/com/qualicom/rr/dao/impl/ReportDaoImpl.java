package com.qualicom.rr.dao.impl;

import com.qualicom.rr.dao.ReportDao;
import com.qualicom.rr.model.ReportLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by x110277 on 11/09/2016.
 */
public class ReportDaoImpl extends NamedParameterJdbcDaoSupport implements ReportDao {

    private String successQuery;
    private String successQueryColumns;

    private String failureQuery;
    private String failureQueryColumns;

    private String detailedFailureQuery;
    private String detailedFailureQueryColumns;

    public String getSuccessQuery() {
        return successQuery;
    }

    public void setSuccessQuery(String successQuery) {
        this.successQuery = successQuery;
    }

    public String getFailureQuery() {
        return failureQuery;
    }

    public void setFailureQuery(String failureQuery) {
        this.failureQuery = failureQuery;
    }

    public String getDetailedFailureQuery() {
        return detailedFailureQuery;
    }

    public void setDetailedFailureQuery(String detailedFailureQuery) {
        this.detailedFailureQuery = detailedFailureQuery;
    }

    public String getSuccessQueryColumns() {
        return successQueryColumns;
    }

    public void setSuccessQueryColumns(String successQueryColumns) {
        this.successQueryColumns = successQueryColumns;
    }

    public String getFailureQueryColumns() {
        return failureQueryColumns;
    }

    public void setFailureQueryColumns(String failureQueryColumns) {
        this.failureQueryColumns = failureQueryColumns;
    }

    public String getDetailedFailureQueryColumns() {
        return detailedFailureQueryColumns;
    }

    public void setDetailedFailureQueryColumns(String detailedFailureQueryColumns) {
        this.detailedFailureQueryColumns = detailedFailureQueryColumns;
    }

    private List<ReportLine> createReport(final String query, final String[] columns, final String mmddyyyy) {
        final List<ReportLine> report = getJdbcTemplate().execute(query, new PreparedStatementCallback<List<ReportLine>>() {

            @Override
            public List<ReportLine> doInPreparedStatement(PreparedStatement pstmt) throws SQLException {

                pstmt.setString(1, mmddyyyy);

                ResultSet results = pstmt.executeQuery();
                ArrayList<ReportLine> report = new ArrayList<ReportLine>();
                while (results != null && results.next()) {
                    //Do stuff here to populate the report from the query given, keying each value by its column name
                }
                return report;
            };
        });
        return report;
    }


}

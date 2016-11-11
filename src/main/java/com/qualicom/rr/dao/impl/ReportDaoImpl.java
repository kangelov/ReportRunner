package com.qualicom.rr.dao.impl;

import com.qualicom.rr.dao.ReportDao;
import com.qualicom.rr.model.Report;
import com.qualicom.rr.model.ReportColumns;
import com.qualicom.rr.model.ReportRow;
import com.qualicom.rr.model.ReportParameters;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by x110277 on 11/09/2016.
 */
public class ReportDaoImpl extends NamedParameterJdbcDaoSupport implements ReportDao {

    public ReportColumns reportColumns;

    public ReportParameters reportParameters;

    public String query;

    @Override
    public Report createReport() {
        return new Report(reportColumns,generateData(query,reportColumns,reportParameters));
    }

    List<ReportRow> generateData(final String query, final ReportColumns columns, final ReportParameters parameters) {
        //Populate the parameter list in order.
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        for (Map.Entry<String,String> entry : parameters.entrySet()) {
            namedParameters.addValue(entry.getKey(), entry.getValue());
        }

        //Run the report and extract the resultset
        final List<ReportRow> data = getNamedParameterJdbcTemplate().query(query, namedParameters, new ResultSetExtractor<List<ReportRow>>() {

            @Override
            public List<ReportRow> extractData(ResultSet results) throws SQLException {
                //Now read the result
                ArrayList<ReportRow> data = new ArrayList<ReportRow>();
                while (results != null && results.next()) {
                    ReportRow line = new ReportRow();
                    for (String col : columns) {
                        String value = results.getString(col);
                        line.add(value);
                    }
                    data.add(line);
                }
                return data;
            }
        });
        return data;
    }

    public ReportColumns getReportColumns() {
        return reportColumns;
    }

    public void setReportColumns(ReportColumns reportColumns) {
        this.reportColumns = reportColumns;
    }

    public ReportParameters getReportParameters() {
        return reportParameters;
    }

    @Override
    public void setReportParameters(ReportParameters reportParameters) {
        this.reportParameters = reportParameters;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}

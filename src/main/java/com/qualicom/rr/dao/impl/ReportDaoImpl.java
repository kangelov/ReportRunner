package com.qualicom.rr.dao.impl;

import com.qualicom.rr.dao.ReportDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

/**
 * Created by x110277 on 11/09/2016.
 */
public class ReportDaoImpl extends NamedParameterJdbcDaoSupport implements ReportDao {


}

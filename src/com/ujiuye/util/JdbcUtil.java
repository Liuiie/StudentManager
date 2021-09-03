package com.ujiuye.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;

public class JdbcUtil {

    private JdbcUtil(){}

    private static ComboPooledDataSource cpds = new ComboPooledDataSource();
    private static QueryRunner qr = new QueryRunner(cpds);

    public static QueryRunner getQueryRunner(){
        return qr;
    }

}

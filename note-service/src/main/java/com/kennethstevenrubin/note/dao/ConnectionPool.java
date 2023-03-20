package com.kennethstevenrubin.note.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.core.env.Environment;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {

    private HikariConfig config = new HikariConfig();
    private HikariDataSource ds = null;

    public ConnectionPool(final Environment env, final String dataSource) {

        this.config.setJdbcUrl(env.getProperty("datasource." + dataSource + ".url"));
        this.config.setUsername(env.getProperty("datasource.username"));
        this.config.setPassword(env.getProperty("datasource.password"));
        this.config.addDataSourceProperty("cachePrepStmts", "true");
        this.config.addDataSourceProperty("prepStmtCacheSize", "250");
        this.config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        this.ds = new HikariDataSource(this.config);
    }

    public Connection getConnection() throws SQLException {

        return this.ds.getConnection();
    }
}
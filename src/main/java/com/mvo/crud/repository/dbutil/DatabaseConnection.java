package com.mvo.crud.repository.dbutil;

import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;

public class DatabaseConnection {
    private  static Connection connection;
    private FileInputStream fis;
    private Properties property = new Properties();

    private DatabaseConnection() {
    }
}

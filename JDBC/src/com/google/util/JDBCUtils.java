package com.google.util;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
    private static String url;
    private static String user;
    private static String password;
    private static String driver;

    //通过静态代码块做预处理,读取资源文件
    static {
        try {
            InputStream inputStream = ClassLoader.getSystemResourceAsStream("resources/db.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            driver = properties.getProperty("driver");



        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(url, user, password);
    }



    public static void close(Connection connection, PreparedStatement preparedStatement) throws SQLException {
        if(connection!=null){
            connection.close();
        }
        if(preparedStatement!=null){
            preparedStatement.close();
        }

    }

}

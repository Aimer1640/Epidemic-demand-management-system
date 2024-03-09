package util;

import java.sql.*;

//数据库连接文件
public class dbutil {
    private static final String USER = "root";
    private static final String UPWD = "root";
    private static final String URL = "jdbc:mysql://localhost:3308/work?useUnicode=true&characterEncoding=UTF-8";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    //数据库连接对象
    public static Connection getCon(){
        try {
            return DriverManager.getConnection(URL, USER, UPWD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    //关闭连接
    public static void close(Connection connection, Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //关闭打开资源
    public static void close(Connection connection, Statement statement, ResultSet rs) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

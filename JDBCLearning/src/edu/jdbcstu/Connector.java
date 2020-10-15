package edu.jdbcstu;

import java.sql.*;

public class Connector {
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost/glimmer?&serverTimezone=GMT";
    private static final String user = "root";
    private static final String password = "my-llh0901-sql";


    public static Connection getConnection() {
        Connection conn = null;
        try {
            //注册JDBC驱动器
            Class.forName(driver);
            System.out.println("MySQL JDBC Driver Registered!");
            //创建连接对象,建立连接
            conn = DriverManager.getConnection(url, user, password);

            assert conn != null;
            System.out.println("Take control of your database now!");

            //取消自动提交
            conn.setAutoCommit(false);
            return conn;

        } catch (ClassNotFoundException ex) {
            System.out.println("Error: unable to load driver class!");
            System.exit(1);
        } catch (SQLException ex) {
            System.out.println("Connection Failed! Check output console");
            ex.printStackTrace();

        }
        return null;
}
    public static void closeConnection(Connection conn) {
        //关闭访问与连接
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

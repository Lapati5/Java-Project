package edu.jdbcstu;

import java.sql.*;

/**
 * JDBC样例代码
 */
public class JDBCDemo {
    static final String driver = "com.mysql.cj.jdbc.Driver";
    //mysql识别时区出现错误，这是因为访问的url中没有指定时区为UTC
    static final String url = "jdbc:mysql://localhost/glimmer?&serverTimezone=GMT";
    static final String user = "root";
    static final String password = "my-llh0901-sql";


    public static void main(String[] args) throws SQLException {
        //使用JDBC驱动程序连接MySQL数据库的示例代码片段
        Connection conn = null;
        Statement stmt = null;
        try {
            //注册JDBC驱动器
            Class.forName(driver);
            System.out.println("MySQL JDBC Driver Registered!");
            //创建连接对象,建立连接
            conn = DriverManager.getConnection(url, user, password);

            assert conn != null;
            System.out.println("Take control of your database now!");

            //对数据库进行访问
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                                                    ResultSet.CONCUR_UPDATABLE);

            //取消自动提交
            conn.setAutoCommit(false);

            String sql;
            //对数据库中表实施插入操作
            System.out.println("Inserting the records into the studentinfo...");
            sql = "INSERT INTO studentinfo " +
                        "VALUES (10004, 'george clooney', 'Class04')";
            stmt.addBatch(sql);

            //对数据库中表实施删除操作
            System.out.println("Deleting the records from the studentinfo...");
            sql = "DELETE FROM studentinfo " +
                    "WHERE stu_id = 10002";
            stmt.addBatch(sql);        //将sql语句添加到批处理中

            //对数据库中表实施更新操作
            System.out.println("Updating the records in the studentinfo...");
            sql = "UPDATE studentinfo " +
                    "SET stu_class = 'Class04'" +
                    "WHERE stu_id = 10004";
            stmt.addBatch(sql);

            stmt.executeBatch();    //执行批处理中所有sql语句

            conn.commit();        //提交改动

            //对数据库中表实施查询操作
            sql = "SELECT stu_id, stu_name, stu_class " +
                    "FROM studentinfo";
            ResultSet rs = stmt.executeQuery(sql);
            rs.beforeFirst();
            while (rs.next()) {
                int id = rs.getInt("stu_id");
                String name = rs.getString("stu_name");
                String classname = rs.getString("stu_class");

                System.out.print("ID: " + id);
                System.out.print(", Name: " + name);
                System.out.println(", Class: " + classname);
            }

            rs.close();
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: unable to load driver class!");
            System.exit(1);
        } catch (SQLException ex) {
            if (conn != null)
                conn.rollback();
            System.out.println("Connection Failed! Check output console");
            ex.printStackTrace();
        } finally {
            //关闭访问与连接
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("Operation is over");
    }
}

package edu.jdbcstu;

import java.sql.*;
import java.util.Scanner;

public class StuTest {
    private Scanner in = new Scanner(System.in);
    Connection conn = null;
    private PreparedStatement pstmt = null;       //对数据库进行访问的接口
    private String sql = null;                    //数据库sql语句

    public static void main(String[] args) {
        StuTest t1 = new StuTest();
        try {
            t1.MainMenu();                      //运行主界面
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //主界面函数，显示可用操作
    public void MainMenu() throws SQLException {
        conn = Connector.getConnection();              //获得与JDBC驱动程序的连接
        while (true) {
            System.out.println("1.查询学生信息\t 2.修改学生信息\t 3.显示所有学生信息\t 4.退出系统");
            System.out.print("请选择所需操作的序号: ");
            int op = in.nextInt();
            switch (op) {
                case 1 -> Check();      //查询操作
                case 2 -> Change();     //修改操作
                case 3 -> Show();       //显示操作
                case 4 -> {
                    Connector.closeConnection(conn);        //关闭连接
                    System.exit(0);              //退出程序
                }
            }
        }
    }

    //打印rs指向的结果集的行
    public void printRs(ResultSet rs) throws SQLException {
        int id = rs.getInt("stu_id");
        String name = rs.getString("stu_name");
        String classname = rs.getString("stu_class");
        System.out.print("ID: " + id);
        System.out.print(", Name: " + name);
        System.out.println(", Class: " + classname);

    }

    //通过学生id查询数据库内学生情况
    public void Check() throws SQLException {
        System.out.println("====学生信息查询====");
        System.out.print("请输入要查询的id: ");
        int choose_id = in.nextInt();
        sql = "SELECT stu_id, stu_name, stu_class FROM studentinfo "
                + "WHERE stu_id = ?";
        pstmt = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        pstmt.setInt(1, choose_id);
        ResultSet rs = pstmt.executeQuery();
        if (!rs.next()) {
            System.out.println("====未找到id为" + choose_id + "的学生====");
            rs.close();
            return;
        }
        printRs(rs);
        rs.close();
    }

    //提供可用的修改操作
    public void Change() throws SQLException {
        System.out.println("1.新增一条学生信息\t 2.更新一条学生信息\t 3.删除一条学生信息\t 4.返回上一级");
        System.out.print("请选择所需操作的序号: ");
        int op = in.nextInt();
        switch (op) {
            case 1 -> {
                Student stu = addStudent();         //新增一名学生
                Insert(stu);
            }
            case 2 -> Update();                     //更新一名学生的信息
            case 3 -> Delete();                     //删除一名学生
        }
        pstmt.close();                              //操作结束后关闭接口
        conn.commit();                    //提交修改至数据库
    }

    //打印数据库中所有学生的信息
    public void Show() throws SQLException {
        System.out.println("====所有学生信息====");
        sql = "SELECT stu_id, stu_name, stu_class FROM studentinfo";
        pstmt = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = pstmt.executeQuery();
        rs.beforeFirst();
        while (rs.next()) {
            printRs(rs);
        }
        rs.close();
    }

    //增加新的学生
    public Student addStudent() {
        System.out.println("====新增一条学生信息====");
        System.out.print("请输入新学生的id: ");
        int id = in.nextInt();
        in.nextLine();
        System.out.print("请输入新学生姓名: ");
        String name = in.nextLine();
        System.out.print("请输入新学生班级名称: ");
        String classname = in.nextLine();
        return new Student(id, name, classname);
    }

    //将学生信息插入到数据库中
    public void Insert(Student stu) throws SQLException {
        int id = stu.getId();
        String name = stu.getName();
        String classname = stu.getClassname();
        sql = "INSERT INTO studentinfo " +
                "VALUES (?, ?, ?)";
        pstmt = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        pstmt.setInt(1, id);
        pstmt.setString(2, name);
        pstmt.setString(3, classname);
        pstmt.executeUpdate();
        System.out.println("=====新增成功====");
    }

    //在数据库中更新学生信息
    public void Update() throws SQLException {
        System.out.println("====更新一条学生信息====");
        System.out.print("请输入需要更新的学生的id: ");
        int choose_id = in.nextInt();
        System.out.print("请输入学生新的id: ");
        int id = in.nextInt();
        in.nextLine();
        System.out.print("请输入学生新的姓名: ");
        String name = in.nextLine();
        System.out.print("请输入学生新的班级名称: ");
        String classname = in.nextLine();
        sql = "UPDATE studentinfo " +
                "SET stu_id = ?, stu_name = ?, stu_class = ? " +
                "WHERE stu_id = ?";
        pstmt = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        pstmt.setInt(1, id);
        pstmt.setString(2, name);
        pstmt.setString(3, classname);
        pstmt.setInt(4, choose_id);
        pstmt.executeUpdate();
        System.out.println("====更新完成====");
    }

    //在数据库中删除学生信息
    public void Delete() throws SQLException{
        System.out.println("====删除一条学生信息====");
        System.out.print("请输入需要删除信息学生的id: ");
        int choose_id = in.nextInt();
        sql = "DELETE FROM studentinfo " +
                "WHERE stu_id = ?";
        pstmt = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        pstmt.setInt(1, choose_id);
        pstmt.executeUpdate();
        System.out.println("====删除完成====");
    }
}

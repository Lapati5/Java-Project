package edu.jdbcstu;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Xml2DB {
    private static List<Student> stuList = new ArrayList<>();
    private static Connection conn = null;
    private static PreparedStatement pstmt = null;
    private static String sql = null;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        try {
            conn = Connector.getConnection();
            System.out.println("选择操作：1.增加信息\t2.删除信息\t3.更改信息\t4.查询信息");
            int op = in.nextInt();
            switch (op) {
                case 1 -> Insert();
                case 2 -> Delete();
                case 3 -> Update();
                case 4 -> Select();
            }
            conn.commit();
            Connector.closeConnection(conn);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 解析XML
     * SAXReader可以通过多种方式读取xml数据，并返回Document格式的对象。
     * @return document对象
     */
    public static Document getDocument() {
        Document doc = null;
        try {
            //创建解析器
            SAXReader reader = new SAXReader();
            doc = reader.read(new File("src/edu/jdbcstu/studentinfo.xml"));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return doc;
    }

    /**
     * 获取XML文档中的学生信息
     * @return 学生类的集合
     */
    public static List<Student> getInfo() {
        Document doc = getDocument();
        Element root = doc.getRootElement();

        List<Element> student = root.elements("student");
        for (Element e : student) {
            int id = Integer.parseInt(e.elementText("id").trim());
            String name = e.elementText("name").trim();
            String classname = e.elementText("classname").trim();
            stuList.add(new Student(id, name, classname));
        }
        return stuList;
    }

    //查询操作
    public static void Select() throws SQLException{
        sql = "SELECT stu_id, stu_name, stu_class FROM studentinfo "
                + "WHERE stu_id = ? AND stu_name = ? AND stu_class = ?";
        pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        stuList = getInfo();
        for (Student stu : stuList) {
            pstmt.setInt(1, stu.getId());
            pstmt.setString(2, stu.getName());
            pstmt.setString(3, stu.getClassname());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("stu_id");
                String name = rs.getString("stu_name");
                String classname = rs.getString("stu_class");
                System.out.print("ID: " + id);
                System.out.print(", Name: " + name);
                System.out.println(", Class: " + classname);
            } else {
                System.out.println("未找到" + stu);
            }
        }
    }

    //增加操作
    public static void Insert() throws SQLException {
        sql = "INSERT INTO studentinfo " +
                "VALUES ( ?, ?, ? )";
        pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        stuList = getInfo();
        for (Student stu : stuList) {
            pstmt.setInt(1, stu.getId());
            pstmt.setString(2, stu.getName());
            pstmt.setString(3, stu.getClassname());
            pstmt.addBatch();
        }
        pstmt.executeBatch();
        System.out.println("====添加成功====");
    }

    //删除操作
    public static void Delete() throws SQLException {
        sql = "DELETE FROM studentinfo " +
                "WHERE stu_id = ?";
        pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        stuList = getInfo();
        for (Student stu : stuList) {
            pstmt.setInt(1, stu.getId());
            pstmt.addBatch();
        }
        pstmt.executeBatch();
        System.out.println("====删除成功====");
    }

    //更改操作
    public static void Update() throws SQLException {
        sql = "UPDATE studentinfo " +
                "SET stu_id = ?, stu_name = ?, stu_class = ? " +
                "WHERE stu_id = ?";
        pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        stuList = getInfo();
        for (Student stu : stuList) {
            pstmt.setInt(1, stu.getId());
            pstmt.setString(2, stu.getName());
            pstmt.setString(3, stu.getClassname());
            pstmt.setInt(4, stu.getId());
            pstmt.addBatch();
        }
        pstmt.executeBatch();
        System.out.println("====更改成功====");
    }

}


//package edu.jdbcstu;
//
//
//import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.List;
//import java.util.Scanner;
//
//public class Test {
//    private List<Student> stuList = new ArrayList<>();
//    Scanner in = new Scanner(System.in);
//
//    public void MainMenu() {
//        while (true) {
//            System.out.println("1.查询学生信息\t 2.修改学生信息\t 3.显示所有学生信息\t 4.退出系统");
//            System.out.print("请选择所需操作的序号: ");
//            int op = in.nextInt();
//            switch (op) {
//                case 1 -> {
//                    System.out.print("请输入所要查询学生的id: ");
//                    Student stu = Check();
//                    if (stu != null)
//                        System.out.println(stu);
//                }
//                case 2 -> Change();
//                case 3 -> Show();
//                case 4 -> System.exit(0);
//            }
//        }
//    }
//
//    public Student Check() {
//        int choose_id = in.nextInt();
//        for (Student stu : stuList) {
//            if (stu.getId() == choose_id) {
//                return stu;
//            }
//        }
//        System.out.println("====未找到id为" + choose_id + "的学生====");
//        return null;
//    }
//
//    public void Change() {
//        System.out.println("1.新增一条学生信息\t 2.更新一条学生信息\t 3.删除一条学生信息\t 4.返回上一级");
//        System.out.print("请选择所需操作的序号: ");
//        int op = in.nextInt();
//        switch (op) {
//            case 1 -> Insert();
//            case 2 -> Update();
//            case 3 -> Delete();
//        }
//    }
//
//    public void Show() {
//        stuList.sort((o1, o2) -> o1.getId() - o2.getId());
//        for (Student stu : stuList)
//            System.out.println(stu);
//    }
//
//    public void Insert() {
//        System.out.print("请输入学生的id: ");
//        int id = in.nextInt();
//        in.nextLine();
//        System.out.print("请输入学生姓名: ");
//        String name = in.nextLine();
//        System.out.print("请输入学生班级名称: ");
//        String classname = in.nextLine();
//        Student stu = new Student(id, name, classname);
//        stuList.add(stu);
//        System.out.println("=====新增学生信息成功====");
//    }
//
//    public void Update() {
//        System.out.print("请输入需要更新的学生的id: ");
//        Student stu = Check();
//        if (stu == null)    return;
//        System.out.println(stu);
//        System.out.println("1.ID\t 2.姓名\t 3.班级名称\t 4.全部更新");
//        System.out.print("请输入要更改的条目: ");
//        int op = in.nextInt();
//        if (op == 1 || op == 4) {
//            System.out.print("请输入学生新的id: ");
//            int id = in.nextInt();
//            in.nextLine();
//            stu.setId(id);
//        }
//        if (op == 2 || op == 4) {
//            System.out.print("请输入学生新的姓名: ");
//            String name = in.nextLine();
//            stu.setName(name);
//        }
//        if (op == 3 || op == 4) {
//            System.out.print("请输入学生新的班级名称: ");
//            String classname = in.nextLine();
//            stu.setClassname(classname);
//        }
//        System.out.println("====更新完成====");
//    }
//
//    public void Delete() {
//        System.out.print("请输入需要删除信息学生的id: ");
//        Student stu = Check();
//        if (stu == null)    return;
//        stuList.remove(stu);
//        System.out.println("====删除完成====");
//    }
//
//    public static void main(String[] args) {
//
//        Test t = new Test();
//        t.stuList.add(new Student(10002, "李四", "Class B"));
//        t.stuList.add(new Student(10003, "张三", "Class A"));
//        t.stuList.add(new Student(10001, "王二", "Class C"));
//        t.MainMenu();
//    }
//}
//

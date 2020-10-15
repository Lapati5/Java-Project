package edu.jdbcstu;

public class Student {
    private int id;
    private String name;
    private String classname;

    public Student(int id, String name, String classname) {
        this.id = id;
        this.name = name;
        this.classname = classname;
    }

    @Override
    public String toString() {
        return  "ID: " + id +
                "\t姓名: " + name +
                "\t班级: " + classname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }
}

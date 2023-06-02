package com.freecourses;

public class Users {
    String name,email,pass,uname,college,course;

    public Users() {
    }

    public Users(String name, String email, String pass,String uname, String college, String course) {
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.uname = uname;
        this.college=college;
        this.course=course;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}

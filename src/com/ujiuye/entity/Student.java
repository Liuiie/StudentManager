package com.ujiuye.entity;

import java.util.Objects;

public class Student {

    private int sid;
    private String sname;
    private int sage;
    private String sgender;
    private String semail;
    private String photo;

    public Student() {
    }

    public Student(String sname, int sage, String sgender, String semail) {
        this.sname = sname;
        this.sage = sage;
        this.sgender = sgender;
        this.semail = semail;
    }

    public Student(int sid, String sname, int sage, String sgender, String semail) {
        this.sid = sid;
        this.sname = sname;
        this.sage = sage;
        this.sgender = sgender;
        this.semail = semail;
    }

    public Student(String sname, int sage, String sgender, String semail, String photo) {
        this.sid = sid;
        this.sname = sname;
        this.sage = sage;
        this.sgender = sgender;
        this.semail = semail;
        this.photo = photo;
    }

    public Student(int sid, String sname, int sage, String sgender, String semail, String photo) {
        this.sid = sid;
        this.sname = sname;
        this.sage = sage;
        this.sgender = sgender;
        this.semail = semail;
        this.photo = photo;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public int getSage() {
        return sage;
    }

    public void setSage(int sage) {
        this.sage = sage;
    }

    public String getSgender() {
        return sgender;
    }

    public void setSgender(String sgender) {
        this.sgender = sgender;
    }

    public String getSemail() {
        return semail;
    }

    public void setSemail(String semail) {
        this.semail = semail;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return sid == student.sid &&
                sage == student.sage &&
                Objects.equals(sname, student.sname) &&
                Objects.equals(sgender, student.sgender) &&
                Objects.equals(semail, student.semail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid, sname, sage, sgender, semail);
    }

    @Override
    public String toString() {
        return "Student{" +
                "sid=" + sid +
                ", sname='" + sname + '\'' +
                ", sage=" + sage +
                ", sgender='" + sgender + '\'' +
                ", semail='" + semail + '\'' +
                '}';
    }
}

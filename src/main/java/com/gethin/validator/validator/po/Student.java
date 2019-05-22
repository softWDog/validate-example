package com.gethin.validator.validator.po;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.util.List;

public class Student {

    @NotNull(message = "id不能为空")
    private long id;
    @NotNull(message = "中文名称不能为空")
    @NotEmpty(message = "中文名称不能是空字符串")
    private String cnname;
    @Email(message = "email格式不对")
    private String email;
    @NotNull(message = "学生卡不能为空")
    private StudentSelfcard studentSelfcard;
    @NotNull(message = "课程列表不能为空")
    private List<StudentLecture> studentLectureList;

    private String note;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCnname() {
        return cnname;
    }

    public void setCnname(String cnname) {
        this.cnname = cnname;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public StudentSelfcard getStudentSelfcard() {
        return studentSelfcard;
    }

    public void setStudentSelfcard(StudentSelfcard studentSelfcard) {
        this.studentSelfcard = studentSelfcard;
    }

    public List<StudentLecture> getStudentLectureList() {
        return studentLectureList;
    }

    public void setStudentLectureList(List<StudentLecture> studentLectureList) {
        this.studentLectureList = studentLectureList;
    }
}

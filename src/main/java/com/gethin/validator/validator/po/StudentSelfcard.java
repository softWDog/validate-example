package com.gethin.validator.validator.po;


import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

public class StudentSelfcard {
    @NotNull(message = "id不能为空")
    private int id;
    @NotNull(message = "学生id不能为空")
    private long studentId;
    @NotNull(message = "地址不能为空")
    private String address;
    @NotNull(message = "开始时间不能为空")
    private Date issueDate;
    @Future(message = "结束日期必须晚于当前时间")
    private Date endDate;
    private String note;
    @Pattern(regexp = "@NotNull(message = \"开始时间不能为空\")")
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

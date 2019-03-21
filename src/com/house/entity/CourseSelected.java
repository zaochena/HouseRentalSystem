package com.house.entity;

import java.util.Date;

public class CourseSelected {
    private int cid;
    private String uname;
    private String course_code;

    private int mark;
    private String content;
    private Date contentdate;
    private int status;

    public CourseSelected() {
    }

    public CourseSelected(int cid, String uname, String course_code, int mark, String content, Date contentdate, int status) {
        this.cid = cid;
        this.uname = uname;
        this.course_code = course_code;
        this.mark = mark;
        this.content = content;
        this.contentdate = contentdate;
        this.status = status;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getContentdate() {
        return contentdate;
    }

    public void setContentdate(Date contentdate) {
        this.contentdate = contentdate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

package cn.hba.entity;

import java.io.Serializable;
import java.util.Date;

public class BookInfo implements Serializable {
    private Integer book_id;

    private String book_code;

    private String book_name;

    private Integer book_type;

    private String book_atuthor;

    private String publish_press;

    private Date publish_date;

    private Integer is_borrow;

    private String createdBy;

    private Date creation_time;

    private Date last_updatetime;

    public static String getKeyName(){
        return "BookInfo";
    }


    public String getBook() {
        return "BookInfo{" +
                "book_id=" + book_id +
                ", book_code='" + book_code + '\'' +
                ", book_name='" + book_name + '\'' +
                ", book_type=" + book_type +
                ", book_atuthor='" + book_atuthor + '\'' +
                ", publish_press='" + publish_press + '\'' +
                ", publish_date=" + publish_date +
                ", is_borrow=" + is_borrow +
                ", createdBy='" + createdBy + '\'' +
                ", creation_time=" + creation_time +
                ", last_updatetime=" + last_updatetime +
                '}';
    }

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public String getBook_code() {
        return book_code;
    }

    public void setBook_code(String book_code) {
        this.book_code = book_code == null ? null : book_code.trim();
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name == null ? null : book_name.trim();
    }

    public Integer getBook_type() {
        return book_type;
    }

    public void setBook_type(Integer book_type) {
        this.book_type = book_type;
    }

    public String getBook_atuthor() {
        return book_atuthor;
    }

    public void setBook_atuthor(String book_atuthor) {
        this.book_atuthor = book_atuthor == null ? null : book_atuthor.trim();
    }

    public String getPublish_press() {
        return publish_press;
    }

    public void setPublish_press(String publish_press) {
        this.publish_press = publish_press == null ? null : publish_press.trim();
    }

    public Date getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(Date publish_date) {
        this.publish_date = publish_date;
    }

    public Integer getIs_borrow() {
        return is_borrow;
    }

    public void setIs_borrow(Integer is_borrow) {
        this.is_borrow = is_borrow;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy == null ? null : createdBy.trim();
    }

    public Date getCreation_time() {
        return creation_time;
    }

    public void setCreation_time(Date creation_time) {
        this.creation_time = creation_time;
    }

    public Date getLast_updatetime() {
        return last_updatetime;
    }

    public void setLast_updatetime(Date last_updatetime) {
        this.last_updatetime = last_updatetime;
    }
}
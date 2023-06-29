package com.feiyi.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private String phonenumber;
    private String sex;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date created_date;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date final_date;
    private Integer roleId;
    private int status;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthday;
    private List<Role> roles;
    private String pic;
    private String rolename;
    private Integer age;
    private String status_after;
    /* public Integer getAge() {
        Date date = new Date();//获取今日日期
        int age = date.getYear()-birthday.getYear();
        return age;
    }*/
   /* public Integer getBirthday() {

        return birthday.getYear() - (new Date()).getYear();
    }*/
}

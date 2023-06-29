package com.feiyi.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Exhibition {
    private int id;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date created_date;
    private String title;
    private String description;
    private int createdId;
    private int accommodate;
    private int status;
    private List<User> users;
    private String username;
    private String url;
}

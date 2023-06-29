package com.feiyi.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Works {
    private int id;
    private String title;
    private String description;
    private String url;
    private int createdby_id;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date created_date;
    private int status;
    //用户
    private List<User> users;
    private String username;
    private int categoryId;//1.图片 2.视频 3.音频
}

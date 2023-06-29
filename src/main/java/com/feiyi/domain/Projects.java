package com.feiyi.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Projects {
    private int id;
    private String code;
    private String name;
    private String category_id;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date public_date;
    private String report_area;
    private String protect_area;
    private List<Category> categoryList;
}

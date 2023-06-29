package com.feiyi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountByCategory {
    private String type;
    private Double countCategory;
    private String year;
    private Double countNation;
    private String nation;
}

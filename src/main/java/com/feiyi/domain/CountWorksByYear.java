package com.feiyi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountWorksByYear {
    private String year;
    private int countWorks;
}

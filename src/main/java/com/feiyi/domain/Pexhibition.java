package com.feiyi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pexhibition {
    private Integer id;
    private Integer userId;
    private Integer exhibitionId;//用户预约的展馆
    private Exhibition exhibitions;//预约的所有展馆
}

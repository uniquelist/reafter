package com.feiyi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pcollect {
    private Integer id;
    private Integer userId;
    private Integer collectedId;//用户收藏的作品
    private Works works;//收藏的所有作品

}

package com.cc.userdemo.domain;

import lombok.Data;

@Data
public class QueryCondition {
    private int QueryType;
    private int LogicFlag;
    private String QueryData;

    // Getter and Setter methods
    // ...
}

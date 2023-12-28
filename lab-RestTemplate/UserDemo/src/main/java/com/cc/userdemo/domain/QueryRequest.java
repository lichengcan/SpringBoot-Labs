package com.cc.userdemo.domain;

import lombok.Data;

import java.util.List;

@Data
public class QueryRequest {
    private int ItemNum;
    private List<QueryCondition> Condition;
    private int QueryCount;
    private int PageFirstRowNumber;
    private int PageRowNum;

}

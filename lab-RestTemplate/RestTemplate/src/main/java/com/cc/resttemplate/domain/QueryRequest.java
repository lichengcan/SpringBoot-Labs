package com.cc.resttemplate.domain;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
public class QueryRequest {
    private int ItemNum;
    private List<QueryCondition> Condition;
    private int QueryCount;
    private int PageFirstRowNumber;
    private int PageRowNum;

    @Override
    public String toString() {
        return "QueryRequest{" +
                "itemNum:" + ItemNum +
                ", condition:" + Condition +
                ", queryCount:" + QueryCount +
                ", pageFirstRowNumber:" + PageFirstRowNumber +
                ", pageRowNum:" + PageRowNum +
                '}';
    }

}

package com.cc.resttemplate.domain;

import lombok.Data;

@Data
public class QueryCondition {
    private int QueryType;
    private int LogicFlag;
    private String QueryData;

    @Override
    public String toString() {
        return "QueryCondition{" +
                "QueryType:" + QueryType +
                ", LogicFlag:" + LogicFlag +
                ", QueryData:'" + QueryData + '\'' +
                '}';
    }
}

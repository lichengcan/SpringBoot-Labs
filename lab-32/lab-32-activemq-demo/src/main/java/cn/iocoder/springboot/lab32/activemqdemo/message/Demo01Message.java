package cn.iocoder.springboot.lab32.activemqdemo.message;

import lombok.Data;

import java.io.Serializable;

@Data
public class Demo01Message implements Serializable {

    public static final String QUEUE = "lichengcan";

    /**
     * 编号
     */
    private Integer id;

}

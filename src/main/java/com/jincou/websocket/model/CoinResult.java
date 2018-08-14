package com.jincou.websocket.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CoinResult {

    //状态码，0代表成功
    @JsonProperty("Code")
    private int Code;
    //具体数据（注意这里json用{表示，所有代表对象
    @JsonProperty("Obj")
    private Obj obj;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)

    public static class Obj {

        //虚拟币代码
        @JsonProperty("S")
        private String securityCode;

        //虚拟币名称
        @JsonProperty("N")
        private String name;

        //现在价格
        @JsonProperty("P")
        private double now;

        //最高价格
        @JsonProperty("H")
        private double high;

        //最低价格
        @JsonProperty("L")
        private double low;

        //买一价
        @JsonProperty("B1")
        private double bid1;

        //买一量
        @JsonProperty("B1V")
        private int bid1Volume;

        //卖一价
        @JsonProperty("S1")
        private double ask1;

        //卖一量
        @JsonProperty("S1V")
        private double ask1Volume;

        //已成交价，这个接口没有提供，只要记住{}代表是对象，【】代表是结合那就需要集合接受：如下
        //private List<Transaction> transactions;
    }
}

package com.jincou.websocket.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 股票成交消息
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction {

    /**
     * 成交时间
     */
    private String time;

    /**
     * 成交价格
     */
    private int price;

    /**
     * 成交量（手）
     */
    private int volume;
}

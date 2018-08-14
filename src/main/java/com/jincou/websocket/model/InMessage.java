package com.jincou.websocket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * 接受客户端数据实体
 */
public class InMessage {

    private String from;

    private String to;

    private String content;

    private Date time;

    public String getFrom() {
        return from;
    }

   //手写一个只传content的构造函数
    public InMessage(String content) {
        this.content = content;
    }
}

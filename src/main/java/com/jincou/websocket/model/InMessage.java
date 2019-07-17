package com.jincou.websocket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * 接受客户端数据实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InMessage {

    private String from;

    private String to;

    private String content;

    private Date time;

    public String getFrom() {
        return from;
    }

    public InMessage(String content) {
        this.content = content;
    }
}

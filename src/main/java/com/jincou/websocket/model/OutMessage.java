package com.jincou.websocket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 服务端向客户端传入实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutMessage {

    private String from;

    private String content;

    private Date time = new Date();


    public OutMessage(String content) {
        this.content = content;
    }
}


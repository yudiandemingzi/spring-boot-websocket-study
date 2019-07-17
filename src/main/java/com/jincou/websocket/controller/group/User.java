package com.jincou.websocket.controller.group;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 功能描述：用户模型
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String username;
    private String pwd;

}

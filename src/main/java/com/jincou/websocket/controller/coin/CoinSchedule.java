package com.jincou.websocket.controller.coin;

import com.jincou.websocket.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * //要启动定时任务记得在启动类上添加下面两个注解
 *
 * @ComponentScan(basePackages="com.jincou.websocket")
 * @EnableScheduling 功能描述：股票推送,这里只需通过定时任务向客服端发送消息
 */
@Component
public class CoinSchedule {

    @Autowired
    private WebSocketService ws;

    /**
     * 定时任务
     */
    @Scheduled(fixedRate = 100000000)
    public void coinInfo() {
        ws.sendCoinInfo();
    }
}

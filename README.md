该项目主要实现的功能有：

```
1、公告功能系统功能

2、单人聊天功能

3、股票或者比特币时时推送功能

4、群聊功能，时时在线用户信息
```

针对每一个功能下面都有博客详细的说明

1、[WebSocket(1)---WebSocket介绍](https://www.cnblogs.com/qdhxhz/p/8467715.html)

2、[WebSocket(2)---实现游戏公告功能](https://www.cnblogs.com/qdhxhz/p/9438954.html)

3、[WebSocket(3)---实现一对一聊天功能](https://www.cnblogs.com/qdhxhz/p/9452237.html)

4、[WebSocket(4)---实现定时推送比特币交易信息](https://www.cnblogs.com/qdhxhz/p/9452404.html)

5、[WebSocket(5)---多人聊天系统](https://www.cnblogs.com/qdhxhz/p/9471659.html)

#### 技术架构

项目总体技术选型

```
SpringBoot2.1.0 + Maven3.5.4 + websocket + lombok(插件)+ html + js
```

前端的代码在 resources —> static 目录下

`注意` 

这里 获取比特币推送信息的服务 我这边并没有对接交易所的api获取而是在阿里云上购买的服务，现在账号密码已经过期了
你们想要测试，可以自己去阿里云购买服务获取。购买地址我的博客里有。几块钱就可以买个1000次推送。

package com.jincou.websocket.intecepter;

import com.jincou.websocket.controller.group.UserChatController;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;


/**
 * 功能描述：频道拦截器 ，类似管道，可以获取消息的一些meta数据
 */
public class SocketChannelIntecepter extends ChannelInterceptorAdapter {

    /**
     * 在完成发送之后进行调用，不管是否有异常发生，一般用于资源清理
     */
    @Override
    public void afterSendCompletion(Message<?> message, MessageChannel channel,
                                    boolean sent, Exception ex) {
        System.out.println("SocketChannelIntecepter->afterSendCompletion");
        super.afterSendCompletion(message, channel, sent, ex);
    }


    /**
     * 在消息被实际发送到频道之前调用
     */
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        System.out.println("SocketChannelIntecepter->preSend");

        return super.preSend(message, channel);
    }

    /**
     * 发送消息调用后立即调用
     */
    @Override
    public void postSend(Message<?> message, MessageChannel channel,
                         boolean sent) {
        System.out.println("SocketChannelIntecepter->postSend");

        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(message);//消息头访问器

        if (headerAccessor.getCommand() == null) return;// 避免非stomp消息类型，例如心跳检测

        String sessionId = headerAccessor.getSessionAttributes().get("sessionId").toString();
        System.out.println("SocketChannelIntecepter -> sessionId = " + sessionId);

        switch (headerAccessor.getCommand()) {
            case CONNECT:
                connect(sessionId);
                break;
            case DISCONNECT:
                disconnect(sessionId);
                break;
            case SUBSCRIBE:
                break;

            case UNSUBSCRIBE:
                break;
            default:
                break;
        }
    }

    /**
     * 连接成功
     */
    private void connect(String sessionId) {
        System.out.println("connect sessionId=" + sessionId);
    }

    /**
     * 断开连接
     */
    private void disconnect(String sessionId) {
        System.out.println("disconnect sessionId=" + sessionId);
        //用户下线操作
        UserChatController.onlineUser.remove(sessionId);
    }

}

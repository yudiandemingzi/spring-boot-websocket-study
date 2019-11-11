package com.oujiong.websocket.intecepter;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

/**
 * WebSocket握手请求的拦截器. 检查握手请求和响应, 对WebSocketHandler传递属性
 * 可以通过这个类的方法获取resuest,和response
 */
public class HttpHandShakeIntecepter implements HandshakeInterceptor{

	/**
	 *
	 * 在握手之前执行该方法, 继续握手返回true, 中断握手返回false. 通过attributes参数设置WebSocketSession的属性
	 * 这个项目只在WebSocketSession这里存入sessionID
	 */
	@Override
	public boolean beforeHandshake(ServerHttpRequest request,
			ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {

		System.out.println("【握手拦截器】beforeHandshake");

		if(request instanceof ServletServerHttpRequest) {
			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest)request;
			HttpSession session =  servletRequest.getServletRequest().getSession();
			String sessionId = session.getId();
			System.out.println("【握手拦截器】beforeHandshake sessionId="+sessionId);
			//这里将sessionId放入SessionAttributes中，
			attributes.put("sessionId", sessionId);
		}

		return true;
	}


	/**
	 * 在握手之后执行该方法. 无论是否握手成功都指明了响应状态码和相应头(这个项目没有用到）
	 */
	@Override
	public void afterHandshake(ServerHttpRequest request,
			ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception exception) {
		System.out.println("【握手拦截器】afterHandshake");

		if(request instanceof ServletServerHttpRequest) {
			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest)request;
			HttpSession session =  servletRequest.getServletRequest().getSession();
			String sessionId = session.getId();
			System.out.println("【握手拦截器】afterHandshake sessionId="+sessionId);
		}
	}
}

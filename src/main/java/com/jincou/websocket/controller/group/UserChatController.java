package com.jincou.websocket.controller.group;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.jincou.websocket.model.InMessage;
import com.jincou.websocket.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;






@Controller
public class UserChatController {

	@Autowired
	private WebSocketService ws;

	/**
	 * 登陆时，模拟数据库的用户信息
	 */
	//模拟数据库用户的数据
	public static Map<String, String> userMap = new HashMap<String, String>();
	static{
		userMap.put("zhangsan", "123");
		userMap.put("lisi", "456");
		userMap.put("wangwu", "789");
		userMap.put("zhaoliu", "000");
		userMap.put("xiaoxiao", "666");
	}

	/**
	 * 模拟用户在线进行页面跳转的时候，判断是否在线
	 * (这个实际开发中肯定存在redis或者session中,这样数据才能共享）
	 * 这里只是简单的做个模拟，所以暂且用普通map吧
	 */
	public static Map<String, User> onlineUser = new HashMap<>();
	static{
		//key值一般是每个用户的sessionID（这里表示admin用户一开始就在线）
		onlineUser.put("123",new User("admin","888"));
	}
	
	
	/**
	 * 功能描述：用户登录接口
	 */
	@RequestMapping(value="login", method=RequestMethod.POST)
	public String userLogin( @RequestParam(value="username", required=true)String username, 
			@RequestParam(value="pwd",required=true) String pwd, HttpSession session) {

		//判断是否正确
		String password = userMap.get(username);
		if (pwd.equals(password)) {
			User user = new User(username, pwd);
			String sessionId = session.getId();

			//用户登陆成功就把该用户放到在线用户中...
			onlineUser.put(sessionId, user);
			//跳到群聊页面
			return "redirect:/group/chat.html";
		} else {
			return "redirect:/group/error.html";
		}
		
	}
	
	
	/**
	 * 功能描述：用于定时给客户端推送在线用户
	 */
	@Scheduled(fixedRate = 2000)
	public void onlineUser() {
		ws.sendOnlineUser(onlineUser);
	}
	
	/**
	 * 功能描述 群聊天接口
	 * message 消息体
	 * headerAccessor 消息头访问器，通过这个获取sessionId
	 */
	@MessageMapping("/group/chat")
	public void topicChat(InMessage message, SimpMessageHeaderAccessor headerAccessor){
		//这个sessionId是在HttpHandShakeIntecepter拦截器中放入的
		String sessionId = headerAccessor.getSessionAttributes().get("sessionId").toString();
		//通过sessionID获得在线用户信息
		User user = onlineUser.get(sessionId);
		message.setFrom(user.getUsername());
		ws.sendTopicChat(message);
		
	}	
}

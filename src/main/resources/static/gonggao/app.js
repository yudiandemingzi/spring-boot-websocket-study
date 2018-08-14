var stompClient = null;

//这个方法仅仅是用来改变样式，不是核心
function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#notice").html("");
}

//1、建立连接（先连接服务端配置文件中的基站，建立连接，然后订阅服务器目录消息
function connect() {
	//1、连接SockJS的endpoint是“endpoint-websocket”，与后台代码中注册的endpoint要一样。
    var socket = new SockJS('/endpoint-websocket');

    //2、用stom进行包装，规范协议
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {

    //3、建立通讯
        setConnected(true);
        console.log('Connected: ' + frame);

    //4、通过stompClient.subscribe（）订阅服务器的目标是'/topic/game_chat'发送过来的地址，与@SendTo中的地址对应。
        stompClient.subscribe('/topic/game_chat', function (result) {
        	console.info(result)
        	showContent(JSON.parse(result.body));
        });
    });
}

//2、关闭连接
function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

//3、游戏管理员发送公告信息（这个也是游戏用户所没有功能，其它都一样）
function sendName() {
	//1、通过stompClient.send 向/app/gonggao/chat 目标 发送消息,这个是在控制器的@messageMapping 中定义的。(/app为前缀，配置里配置）
    stompClient.send("/app/gonggao/chat", {}, JSON.stringify({'content': $("#content").val()}));
}

//4、订阅的消息显示在客户端指定位置
function showContent(body) {
    $("#notice").append("<tr><td>" + body.content + "</td> <td>"+new Date(body.time).toLocaleString()+"</td></tr>");
}


$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});


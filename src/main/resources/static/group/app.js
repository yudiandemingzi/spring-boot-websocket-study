var stompClient = null;

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

function connect() {
	var socket = new SockJS('/endpoint-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        
        //订阅群聊消息
        stompClient.subscribe('/topic/chat', function (result) {
        	showContent(JSON.parse(result.body));
        });
        
        //订阅在线用户消息
        stompClient.subscribe('/topic/onlineuser', function (result) {
        	showOnlieUser(JSON.parse(result.body));
        });
        
        
    });
}


//断开连接
function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

//发送聊天记录
function sendContent() {
    stompClient.send("/app/group/chat", {}, JSON.stringify({'content': $("#content").val()}));
    
}

//显示聊天记录
function showContent(body) {
    $("#record").append("<tr><td>" + body.content + "</td> <td>"+new Date(body.time).toLocaleTimeString()+"</td></tr>");
}

//显示实时在线用户
function showOnlieUser(body) {
    $("#online").html("<tr><td>" + body.content + "</td> <td>"+new Date(body.time).toLocaleTimeString()+"</td></tr>");
}


$(function () {
    
	connect();//自动上线
	
	$("form").on('submit', function (e) {
        e.preventDefault();
    });
     
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() {
    	sendContent(); 
    });
});


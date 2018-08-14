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
	var from = $("#from").val();
	var socket = new SockJS('/endpoint-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        //通过+from就可以指定我订阅的是我自己用户的信息
        stompClient.subscribe('/chat/single/'+from, function (result) {
        	showContent(JSON.parse(result.body));
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
	//这里出了发送content信息外，还发送发送信息的用户信息，和接受信息的用户信息
    stompClient.send("/app/ptp/single/chat", {}, JSON.stringify({'content': $("#content").val(), 'to':$("#to").val(), 'from':$("#from").val()}));
}

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


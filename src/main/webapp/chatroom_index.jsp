<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
  String path = request.getContextPath();
			String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript"
	src="http://cdn.sockjs.org/sockjs-0.3.min.js"></script>
<script>
	var websocket;
	if ('WebSocket' in window) {
		websocket = new WebSocket("ws://localhost:8080/Origami/webSocketServer");
	} else if ('MozWebSocket' in window) {
		websocket = new MozWebSocket(
				"ws://localhost:8080/Origami/webSocketServer");
	} else {
		websocket = new SockJS(
				"http://localhost:8080/Origami/sockjs/webSocketServer");
	}
	websocket.onopen = function(evnt) {
	};
	websocket.onmessage = function(evnt) {
		$("#msgcount").html("(<font color='red'>" + evnt.data + "</font>)")
	};
	websocket.onerror = function(evnt) {
	};
	websocket.onclose = function(evnt) {
	}
</script>
</head>

<body>
	This is chatroom index page.
	<br>
</body>
</html>

<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<link rel="shortcut icon" href="${__STATIC__}/favicon.ico" />
	<title>Test page</title>
	<style>
		* {margin: 0; padding: 0;}
	</style>
</head>
<body>

test.jsp

	AppName: ${appName}<br>
	根目录: ${__SERVER__} <br>
	当前URL: ${__URL__}<br>
	完整URL(带参数): ${__FULL_URL__}<br>
	静态资源目录: ${__STATIC__}<br>
	
	<div id="name">name: </div>
	
	<script src="${__STATIC__}/assets/js/app.js"></script>
	<script>
		var app = new APP("李立凯");
		var appInfo = app.getAppInfo();
		console.log(appInfo);
		document.querySelector('#name').innerHTML += JSON.stringify(appInfo);
	</script>
</body>
</html>
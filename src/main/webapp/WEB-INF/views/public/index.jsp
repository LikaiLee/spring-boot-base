<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="shortcut icon" href="${__STATIC__}/favicon.ico" />
<title>Index page</title>
<style>
* {
	margin: 0;
	padding: 0;
}
</style>
</head>
<body>
	AppName: ${appName}
	<br> 根目录: ${__SERVER__}
	<br> 当前URL: ${__URL__}
	<br> 完整URL(带参数): ${__FULL_URL__}
	<br> 静态资源目录: ${__STATIC__}
	<br>

	<div id="name">name:</div>
	<button id="btn">发送请求</button>
	<div class="list">......</div>

	<script src="${__STATIC__}/assets/js/app.js"></script>
	<script>
		var app = new APP("李立凯");
		var appInfo = app.getAppInfo();
		console.log(appInfo);
		document.querySelector('#name').innerHTML += JSON.stringify(appInfo);
	
		var btn = document.querySelector('#btn');
		btn.onclick = function() {
			app.ajax({
				method : 'post',
				url : 'user/list',
				// url : '${__SERVER__}/user/list',
				data : {
					page : 1,
					size : 2
				},
				success : function(res) {
					var data = JSON.parse(res)
					console.log(data)
					document.querySelector('.list').innerHTML = res;
				},
				error: function(statusCode) {
					alert("请求失败，错误码为：" + statusCode);
				}
			})
		}
	</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>dashBoard</title>
</head>
<body onLoad="getAjax('https://api.ipify.org', 'format=json', 'setPublicIp')">

<form action = "action"></form>

<div>


<input name = "certification" type = "hidden" value = "${certification}" />

${message}
</div>

<input type="button" value="로그아웃" onClick="logOut()" />

</body>
<script type = "text/javascript" src= "resources/js/index.js"></script>
</html>
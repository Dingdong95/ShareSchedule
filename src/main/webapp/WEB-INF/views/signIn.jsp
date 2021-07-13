<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>SignIn</title>
    <!-- 실제로 link에 되어있는 css나 js같은경우 ip/css/style.css 이렇게 나가는데 spring 
    servlet context를 보면 resources mapping에서 경로를 지정해주고 있음 
    해당 폴더경로에 css와 js를 저장해야 인식함. -->
    <link type="text/css" rel="stylesheet" href="resources/css/style.css" />
  
  </head>
  <!-- <body onLoad="callMessage('${message }')">  -->
  <body onLoad="getAjax('https://api.ipify.org', 'format=json', 'setPublicIp')">
    <div class="logInBox">
      <div class="section1">
        <div id="login">Sign in</div>
        <div>
          <span class="grey">회원이 아니신가요?</span>
          <span class="grey"><a id="toSignUp" href="signUp">가입하기</a></span>
        </div>
      </div>
      <div class="section2">
        <input name="uCode" type="text" placeholder="아이디" />
        <input name="uPassword" type="password" placeholder="비밀번호" />
        <div id="passwordCheck">
          <span>비밀번호찾기</span>
        </div>
        <button id="logInBtn" onClick="sendAccessInfo()">Log In</button>
      </div>
    </div>
    <div>
    	<input type="date" name = "date"/>
    </div>
  </body>
  <script type = "text/javascript" src= "resources/js/signIn.js"></script>
</html>

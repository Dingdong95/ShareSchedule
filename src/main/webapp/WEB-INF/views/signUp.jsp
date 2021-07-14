<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>SignUp</title>
    <link type="text/css" rel="stylesheet" href="resources/css/style.css" />
    <script>
  const message = "${message}"
  if(message != ""){
	  alert(message);
  }
  </script>
  </head>
  <!-- <body onLoad="callMessage('${message }')"> -->
  <body>
  
    <div class="logInBox">
      <div class="section1">
        <div id="login">Sign Up</div>
      </div>
      <div class="section2">
        <div class="idInput">
          <input name = "uCode" type="text" placeholder="아이디" />
          <button id="dupCheckBtn" onClick ="dupCheck()">중복확인</button>
        </div>

        <input name = "uPassword" type="password" placeholder="비밀번호" />
        <input type="password" placeholder="비밀번호확인" />
        <input name = "uName" type="text" placeholder="이름" />
        <input name = "uMail" type="text" placeholder="이메일" />
        <div id="passwordCheck">
          <span>비밀번호찾기</span>
        </div>

        <button id="signUpBtn" onClick = "handleSignUp()">회원가입</button>
      </div>
    </div>
  </body>
  <script type = "text/javascript" src= "resources/js/signUp.js"></script>
</html>

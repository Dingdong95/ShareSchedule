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
  </head>
  <!-- <body onLoad="callMessage('${message }')"> -->
  <body>
  
    <div class="logInBox">
      <div class="section1">
        <div id="login">Sign Up</div>
      </div>
      <div class="section2">
        <div class="idInput">
          <input id="id" name = "uCode" type="text" placeholder="아이디" onKeyUp = "korCheck(this, event)" />
          <div>
          <div id ="dupCheckBox"></div>
          <input type= "button" id="dupCheckBtn" onClick ="dupCheck(this)" value="중복확인" />
        	</div>
        </div>
        <input id="password" name = "uPassword" type="password" placeholder="비밀번호" value="#*@!" />
        <input id="passwordCheck" type="password" placeholder="비밀번호확인" value="$@#&" />
        <div id = "pwCheckBox"></div>
        <input id = "name" name = "uName" type="text" placeholder="이름" />
        <input id="email" name = "uMail" type="text" placeholder="이메일" />
        <div id="passwordCheck">
          <span>비밀번호찾기</span>
        </div>

        <button id="signUpBtn" onClick = "handleSignUp()">회원가입</button>
      </div>
    </div>
  </body>
  <script type = "text/javascript" src= "resources/js/signUp.js"></script>
  <script type = "text/javascript" src= "resources/js/index.js"></script>
</html>

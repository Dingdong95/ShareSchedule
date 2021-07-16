//객체 
const dupCheckBtn = document.getElementById("dupCheckBtn");
const signUpBtn = document.getElementById("signUpBtn");
const password = document.getElementById("password");
const passwordCheck = document.getElementById("passwordCheck");
const pwCheckBox = document.getElementById("pwCheckBox");
const dupCheckMsg = document.getElementById("dupCheckBox");
const id = document.getElementById("id");
const name = document.getElementById("name");

/***************************************************************************/
//eventlistener

password.addEventListener("change", pwCheck);
passwordCheck.addEventListener("change",pwCheck);
id.addEventListener("change",pwCheck);
name.addEventListener("change",pwCheck);



/****************************************************************************/
//초기값 세팅
signUpBtn.setAttribute("disabled","true");


/****************************************************************** */

function pwCheck() {
	let uCode = document.getElementsByName("uCode")[0];
	let uName = document.getElementsByName("uName")[0];
	
	console.log("pwcheck들어옴");
	
	//비밀번호 길이 검사
	if(charCount(password.value),8,20){
		//비밀번호 유효성 조합 검사 
		if(isValidateCheck(2, password.value)){
			//비밀번호 일치 검사 
			if(password.value != passwordCheck.value){
				pwCheckBox.innerHTML = "비밀번호가 일치하지않습니다.";
				//pwCheckBox.classList.replace("green","red");
				signUpBtn.setAttribute("disabled","true");
			}else{
				pwCheckBox.innerHTML = "비밀번호 일치";
					if(uCode.value != "" && uName.value != ""){
						signUpBtn.removeAttribute("disabled");
						}
				}
      	}else{
			alert("영문 소문자, 대문자, 숫자,특수문자중 3개이상 조합을 사용해야합니다.");
         	password.value = "";
         	password.focus();
         	return;
			}
	}else{
		alert("비밀번호는 8자에서 20자 사이의 영문 소문자, 대문자, 숫자, 특수문자중 3개이상의 조합을 사용해야합니다.");
		}	
}

function handleSignUp() {
	const uCode = document.getElementsByName("uCode")[0];
	const uPassword = document.getElementsByName("uPassword")[0];
	const uName = document.getElementsByName("uName")[0];
	const uMail = document.getElementsByName("uMail")[0];
	
	let form = makeForm("signUp","post");
	
	form.appendChild(uCode);
	form.appendChild(uPassword);
	form.appendChild(uName);
	form.appendChild(uMail);
	
	document.body.appendChild(form);
	
	form.submit();
		
}

function postAjax(jobCode, clientData, fn){

   /* Step 1*/
   let ajax = new XMLHttpRequest();
      
   /* Step2 */
   ajax.onreadystatechange = function(){
      if(ajax.readyState == 4 && ajax.status == 200){
         /* Step 5 */
         window[fn](JSON.parse(ajax.responseText));
      }
   };
   /* Step 3 */
   ajax.open("POST", jobCode);
   /* Step 4 */
   //form 안만들면 이렇게 
	ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
   ajax.send(clientData);
}



/* 아이디 중복 검사 */
function dupCheck(obj){
   let uCode = document.getElementsByName("uCode")[0];
	   

   if(obj.value != "재입력"){
		//아이디 길이검사 
		if(charCount(uCode.length,8,12)){
	  		// 아이디 조합 유효성 검사
      		if(!isValidateCheck(1, uCode.value)){
         		uCode.value = "";
         		uCode.focus();
         		return;
      			}
				/*ajax */      
				postAjax("dupCheck", "uCode="+uCode.value, "handleDupCheck");
		}else{
			alert("아이디의 문자수는 8~12여야합니다");
			uCode.focus();
			return;
			}
	}else{
		uCode.value = "";
      	uCode.readOnly = false;
      	uCode.focus();
     	obj.value = "중복검사";   
	  	dupCheckMsg.innerText=  ""; 
		}
}


//중복체크 후 jsp handling
function handleDupCheck(jsonData){

	
const btn = document.getElementById("dupCheckBtn");
let uCode = document.getElementsByName("uCode")[0];


// true = 사용 가능 
if(jsonData == true){
		uCode.setAttribute("readonly","true");
	btn.setAttribute("value","재입력");
	dupCheckMsg.innerText=  "사용가능";
}else{
	uCode.value = "";
	dupCheckMsg.innerText=  "사용불가능";
	uCode.focus();
}
}


/* 유효성 검사 */
  function isValidateCheck(type, word){
       let result;
       const codeComp = /^[a-z|A-Z]{1}[a-z|0-9]{7,11}$/g;
       const pwdComp1  = /[a-z]/g;
       const pwdComp2  = /[A-Z]/g;
       const pwdComp3 = /[0-9]/g;
       const pwdComp4  = /[!@#$%^&*]/g;
       
       
       if(type == 1){
          result = codeComp.test(word); 
       }else if(type == 2){
          let count = 0;
          
         count += pwdComp1.test(word)? 1:0;
         count += pwdComp2.test(word)? 1:0;
         count += pwdComp3.test(word)? 1:0;
         count += pwdComp4.test(word)? 1:0;
         
          result = (count >= 3)? true:false;
       }
       
       
       return result;
    }


/*한국어 체크 */
function korCheck(obj, event){
	const pattern = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
	
	if(pattern.test(event.target.value.trim())) {
		obj.value = obj.value.replace(pattern,'').trim();
	}
}

function charCount(length, min, max){
	let test = false;
	if(length >= min && length <=max){
		test = true;
		return test;
	}
	return test;
}
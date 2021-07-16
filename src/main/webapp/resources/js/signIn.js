
/* 전역변수 */
const logInBtn = document.getElementById("logInBtn");
let publicIp;

/*************************************************************** */

function sendAccessInfo() {
	/*
	출력 해볼 것: 
	location.href
	location.protocol
	location.port
	location.pathname
	location.search 
	
	이러면 ajax가 계속 server에 요청하는 와중에 작업이 끝나지도 않았는데 
	alert에서 쓰고 있음. 그래서 undefined. 
		getAjax("https://api.ipfy.org", "format =json", "setPublicIp");
	alert(publicIp); */

	
	
	//jsp에서 넘어온 name 값으로 가져옴 
	//jsp name 자체를 servlet에서 인식을함 form 기준이 아니라
	const uCode = document.getElementsByName("uCode")[0];
	const uPassword = document.getElementsByName("uPassword")[0];
	const method = makeInput("hidden", "method",1);
	const privateIp = makeInput("hidden","privateIp",location.host);
	const pupIp  = makeInput("hidden", "publicIp", publicIp);
	
	

	
	let form = makeForm("signIn","post");
	
	form.appendChild(uCode);
	form.appendChild(uPassword);
	form.appendChild(method);
	form.appendChild(privateIp);
	form.appendChild(pupIp);
	

	document.body.appendChild(form);
	
	form.submit();
}

function makeForm (action, method, name = null){
	let form = document.createElement("form");
	
	if(name != null){
		form.setAttribute("name",name);
	}
	
	form.setAttribute("action",action);
	form.setAttribute("method", method);
	
	return form;
}

function getAjax(jobCode,clientData,fn){
   
   //step1
   let ajax = new XMLHttpRequest();
   
   //step2
   ajax.onreadystatechange = function(){
      if(ajax.readyState == 4 && ajax.status == 200){
         window[fn](JSON.parse(ajax.responseText));
      }
   };
   
   //step3
   if(clientData != ""){ jobCode += "?" + clientData;}
   ajax.open("GET",jobCode);
   
   //step4
   ajax.send();
}

function makeInput  (type, name, value)  {
	 let input = document.createElement("input");
	input.setAttribute("type", type);
	input.setAttribute("name", name);
	input.setAttribute("value", value);
 return input;
}

function setPublicIp(data){
	publicIp = data.ip;
}

/*
const hideForm = () => {
	form = document.getElementsByTagName("form");
	form.setAttribute("display", "none");
}
*/







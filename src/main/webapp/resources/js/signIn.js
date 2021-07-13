const logInBtn = document.getElementById("logInBtn");


const sendAccessInfo = () => {
	const uCode = document.getElementsByName("uCode")[0];
	const uPassword = document.getElementsByName("uPassword")[0];
	const uName = document.getElementsByName("uInfo")[0];
	const uPhone = document.getElementsByName("uInfo")[1];
	
	let form = makeForm("signIn","post");
	
	form.appendChild(uCode);
	form.appendChild(uPassword);
	form.appendchild(uName);
	form.appendchild(uPhone);
	
	document.body.appendChild(form);
	
	form.submit();
}

const sendAccessInfo2 = () => {
	//jsp에서 넘어온 name 값으로 가져옴 
	//jsp name 자체를 servlet에서 인식을함 form 기준이 아니라
	const code1 = document.getElementsByName("Code")[0];
	const code2 = document.getElementsByName("Code")[1];
	const uName = document.getElementsByName("uInfo")[0];
	const uPhone = document.getElementsByName("uInfo")[1];
	const date = document.getElementsByName("date")[0];
	
	let form = makeForm("signIn2","post");
	
	form.appendChild(code1);
	form.appendChild(code2);
	form.appendChild(uName);
	form.appendChild(uPhone);
	form.appendChild(date);
	
	console.log(form);
	
	document.body.appendChild(form);
	
	form.submit();
}

const makeForm = (action, method, name = null) => {
	let form = document.createElement("form");
	
	
	
	if(name != null){
		form.setAttribute("name",name);
	}
	
	form.setAttribute("action",action);
	form.setAttribute("method", method);
	
	
	return form;
}

/*
const hideForm = () => {
	form = document.getElementsByTagName("form");
	form.setAttribute("display", "none");
}
*/

const makeInput = () => {
	const uName = document.createElement("input");
	const uPhone = document.createElement("input");
	//const section2 = document.getElementsByClassName("section2");
	
	uName.setAttribute("placeholder","이름");
	uName.setAttribute("name","uInfo");

	
	uPhone.setAttribute("placeholder", "핸드폰번호");
	uPhone.setAttribute("name", "uInfo");
	
	document.body.appendChild(uName);
	document.body.appendChild(uPhone);
}

const init = () => {
	window.addEventListener("load",makeInput);
	logInBtn.addEventListener("click",sendAccessInfo2);
	//window.addEventListener("load",hideForm);
}

init();

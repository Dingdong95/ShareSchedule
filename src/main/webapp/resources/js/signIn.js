const logInBtn = document.getElementById("logInBtn");

const sendAccessInfo = () => {
	//jsp에서 넘어온 name 값으로 가져옴 
	//jsp name 자체를 servlet에서 인식을함 form 기준이 아니라
	const uCode = document.getElementsByName("uCode")[0];
	const uPassword = document.getElementsByName("uPassword")[0];
	
	let form = makeForm("signIn","post");
	
	form.appendChild(uCode);
	form.appendChild(uPassword);

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


const init = () => {
	logInBtn.addEventListener("click",sendAccessInfo);
	//window.addEventListener("load",hideForm);
}

init();

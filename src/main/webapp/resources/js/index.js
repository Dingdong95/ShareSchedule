/**
 * 
 */
const logInBtn = document.getElementById("logInBtn");


const sendAccessInfo = () => {
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

const init = () => {
	logInBtn.addEventListener("click",sendAccessInfo);
}

init();


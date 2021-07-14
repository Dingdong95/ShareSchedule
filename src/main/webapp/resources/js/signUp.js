const dupCheckBtn = document.getElementById("dupCheckBtn");
const signUpBtn = document.getElementById("signUpBtn");

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

function makeForm (action, method, name = null){
	let form = document.createElement("form");
	
	if(name != null){
		form.setAttribute("name",name);
	}
	
	form.setAttribute("action",action);
	form.setAttribute("method", method);
	
	return form;
}
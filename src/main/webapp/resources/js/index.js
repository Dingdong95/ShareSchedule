function logOut() {
	//jsp에서 넘어온 name 값으로 가져옴 
	//jsp name 자체를 servlet에서 인식을함 form 기준이 아니라
	
	let form = makeForm("signOut","post");
	
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


function makeInput  (type, name, value)  {
	 let input = document.createElement("input");
	input.setAttribute("type", type);
	input.setAttribute("name", name);
	input.setAttribute("value", value);
 return input;
}


function getAjax(jobCode, clientData, fn){
   /* Step 1*/
   let ajax = new XMLHttpRequest();
   
   /* Step2 */
   ajax.onreadystatechange = function(){
      if(ajax.readyState == 4 && ajax.status == 200){
         /* Step 5 */
         window[fn](JSON.parse(ajax.responseText));
      }
   }
}



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



function makeForm(action, method, name = null){
	let form = document.createElement("form");
	
	if(name != null){form.setAttribute("name", name);}
	
	form.setAttribute("action", action);
	form.setAttribute("method", method);
	
	return form;
}



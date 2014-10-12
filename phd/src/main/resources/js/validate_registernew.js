function validation(){
	if(!document.getElementById("registernewform:name").value.match(/^[A-Za-z]+$/)){
		window.alert("Input a valid name!");
		return;
	}
	if(!document.getElementById("registernewform:password").value.match(/^[A-Za-z0-9]+$/)){
		window.alert("Input a valid password!");
		return;
	}
	// role
}
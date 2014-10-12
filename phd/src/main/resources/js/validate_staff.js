function validation(){
	if(!document.getElementById("staffform:newpass").value.match(/^[A-Za-z0-9]+$/)){
		window.alert("Input a valid password!");
		return;
	}
	if(document.getElementById("staffform:confpass") != document.getElementById("staffform:newpass")){
		window.alert("Password 1 and 2 are not equal");
		return;
	}
}
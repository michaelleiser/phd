function validation(){
	if(!document.getElementById("staffform:newpass").value.match(/^[A-Za-z0-9]+$/)){
		window.alert("Input a valid password!");
		return false;
	}
	if(document.getElementById("staffform:confpass").value != document.getElementById("staffform:newpass").value){
		window.alert("Password 1 and 2 are not equal");
		return false;
	}
	var pass = document.getElementById("staffform:newpass").value;
	var hash = CryptoJS.SHA1(pass);
	document.getElementById("staffform:hashedpassword").value = hash;
	return true;
}
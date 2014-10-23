function validation(){
	if(document.getElementById("loginform:name").value == ""){
		window.alert("empty name!");
		return false;
	}
	if(document.getElementById("loginform:password").value == ""){
		window.alert("empty password!");
		return false;
	}
	if(document.getElementById("loginform:department").value == ""){
		window.alert("Select a valid department");
		return false;
	}
	var pass = document.getElementById("loginform:password").value;
	var hash = CryptoJS.SHA1(pass);
	document.getElementById("loginform:hashedpassword").value = hash;
		
    if(typeof(Storage) !== "undefined") {
        sessionStorage.password = document.getElementById("loginform:password").value;
    } else {
    	alert("Sorry, your browser does not support web storage...");
    }
    return true;
}
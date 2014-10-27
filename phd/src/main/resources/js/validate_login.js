function validation(){
	var name = document.getElementById("loginform:name").value;
	if(name == ""){
		window.alert("empty name!");
		return false;
	}
	var pass = document.getElementById("loginform:password").value;
	if(pass == ""){
		window.alert("empty password!");
		return false;
	}
	var department = document.getElementById("loginform:department").value;
	if(department == ""){
		window.alert("Select a valid department");
		return false;
	}
	
	hashPassword(pass);
	savePasswordInSessionStorage(pass);

    return true;
}

function hashPassword(pass){
	var hash = CryptoJS.SHA1(pass);
	document.getElementById("loginform:hashedpassword").value = hash;
}

function savePasswordInSessionStorage(pass){
    if(typeof(Storage) !== "undefined") {
        sessionStorage.password = pass;
    } else {
    	alert("Sorry, your browser does not support web storage...");
    }
}
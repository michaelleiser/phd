/**
 * Validate the login credentials and hash the password of the user according to the scheme h(nonce + h(salt + password)).
 * The salt is retrieved from the server according to the username and departmentname.
 * The nonce is generated and retrieved from the server.
 * The password is then stored in the session storage.
 * @returns
 * 			true if login is accepted
 */
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
	
	var salt = document.getElementById("salt").value;
	var nonce = document.getElementById("loginform:nonce").value;
	var hash = hashPassword(nonce + "" + hashPassword(salt + "" + pass));
	document.getElementById("loginform:password").value = hash;
	
	savePasswordInSessionStorage(pass);

    return true;
}

/**
 * Hash the password.
 * @param pass
 * 			to be hashed
 * @returns
 * 			the hashed password
 */
function hashPassword(pass){
	return CryptoJS.SHA256(pass);
}

/**
 * Save the password in the session storage.
 * Alert if session storage is not supported.
 * @param pass
 * 			to be stored
 */
function savePasswordInSessionStorage(pass){
    if(typeof(Storage) !== "undefined") {
        sessionStorage.password = pass;
    } else {
    	alert("Web Storage not supported");
    }
}
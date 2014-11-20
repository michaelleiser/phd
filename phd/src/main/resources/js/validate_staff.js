/**
 * Validate the password, hash the password and encrypt the private key with the new password.
 * @returns
 * 			true if password can be changed
 */
function validation(){
	var newpass = document.getElementById("staffform:newpass").value;
	if(!newpass.match(/^[A-Za-z0-9]+$/)){
		window.alert("Input a valid password!");
		return false;
	}
	var confpass = document.getElementById("staffform:confpass").value;
	if(confpass != newpass){
		window.alert("Password 1 and 2 are not equal");
		return false;
	}
	var hash = hashPassword(newpass);
	document.getElementById("staffform:newpass").value = hash;
	document.getElementById("staffform:confpass").value = " ";
	
	var encryptedPrivateKey = encryptPrivateKey(newpass);
	document.getElementById("staffform:encryptedprivatekey").value = encryptedPrivateKey;
	
//	alert("finish");
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
	return CryptoJS.SHA1(pass);
}

/**
 * Encrypt the private key with the specified password.
 * @param pass
 * 			to encrypt with
 * @returns
 * 			the encrypted private key
 */
function encryptPrivateKey(pass){
	var encryptedPrivatekey = sessionStorage.privateKey;
	return CryptoJS.AES.encrypt(encryptedPrivatekey, pass);
}
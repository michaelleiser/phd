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
	
	/**
	 * Just for doctors
	 * Statisticians don't need a public key, private key and mustn't get the group key
	 */
	if(document.getElementById("loggedinform:role").innerHTML == 1){
		generatePublicKeyAndEncryptedPrivateKey(newpass);
		var crypt = new JSEncrypt();
		crypt.setPublicKey(document.getElementById("staffform:publickey").value);
		var groupKey = sessionStorage.groupKey;
	    var encrypted =  crypt.encrypt(groupKey);
	    document.getElementById("staffform:encryptedgroupkey").value = encrypted;
	}
	
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
 * Generate a public key private key pair and encrypt the private key with the password.
 * @param password
 * 			to encrypt the private key with
 */
function generatePublicKeyAndEncryptedPrivateKey(password){
	var crypt = new JSEncrypt();
	crypt.getKey();
	
	privateKey = crypt.getPrivateKey();
	publicKey = crypt.getPublicKey();
	
	var encryptedPrivateKey = CryptoJS.AES.encrypt(privateKey, password);
	
	document.getElementById("staffform:encryptedprivatekey").value = encryptedPrivateKey;
	document.getElementById("staffform:publickey").value = publicKey;
}

/**
 * Not used anymore!
 * Encrypt the private key with the specified password.
 * @param pass
 * 			to encrypt with
 * @returns
 * 			the encrypted private key
 */
function encryptPrivateKey(pass){
	//var encryptedPrivatekey = sessionStorage.privateKey;
	//return CryptoJS.AES.encrypt(encryptedPrivatekey, pass);
}
/**
 * Validate the password, hash the password and encrypt the private key with the new password.
 * The password is hashed with a salt, which is generated before.
 * @returns
 * 			true if password can be changed
 */
function validation(){
	var newpass = document.getElementById("staffform:newpass").value;
	if(!newpass.match(/^(?=^.{8,}$)(?=.*\d)(?=.*[!@#$%^&*]+)(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$/)){
		window.alert("Password must be longer than 8 characters and must contain upper case, lower case letters, digits and special characters (!@#$%^&*)");
		return false;
	}
	var confpass = document.getElementById("staffform:confpass").value;
	if(confpass != newpass){
		window.alert("Password 1 and 2 are not equal");
		return false;
	}
	
	var salt = generateSalt(128);
	var hash = hashPassword(salt + "" + newpass);
	document.getElementById("staffform:salt").value = salt;
	document.getElementById("staffform:newpass").value = hash;
	document.getElementById("staffform:confpass").value = "x";
	
	/**
	 * Just for doctors
	 * Statisticians don't need a public key, private key and mustn't get the group key
	 */
	if(document.getElementById("role").value == 1){		// 1 is for doctor
		var pksk = generatePublicKeyAndEncryptedPrivateKey(2048, newpass);
		document.getElementById("staffform:publicKey").value = pksk[0];
		document.getElementById("staffform:encryptedPrivateKey").value = pksk[1];
		
		var crypt = new JSEncrypt();
		crypt.setPublicKey(document.getElementById("staffform:publicKey").value);
		var groupKey = sessionStorage.groupKey;
	    var encrypted =  crypt.encrypt(groupKey);
	    document.getElementById("staffform:encryptedGroupKey").value = encrypted;
	}
	
	return true;
}

/**
 * Generates a random hex string with the specified length.
 * @param length
 * 			in bits
 * @returns
 * 			the random value
 */
function generateSalt(length){
	return CryptoJS.lib.WordArray.random(length/8);
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
 * Generate a public key private key pair and encrypt the private key with the password.
 * @param length
 * 			of the public key private key
 * @param password
 * 			to encrypt the private key with
 * @returns
 * 			the public key and the encrypted private key
 */
function generatePublicKeyAndEncryptedPrivateKey(length, password){
	var crypt = new JSEncrypt({default_key_size: length});
	crypt.getKey();
	var privateKey = crypt.getPrivateKey();
	var publicKey = crypt.getPublicKey();
	var encryptedPrivateKey = CryptoJS.AES.encrypt(privateKey, password);
	return [publicKey, encryptedPrivateKey];
}
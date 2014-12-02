/**
 * Validate the password, hash the password and encrypt the private key with the new password.
 * The password is hashed with a salt, which is generated before.
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
	
	var salt = generateSalt(128);
	var hash = hashPassword(salt + "" + newpass);
	document.getElementById("staffform:salt").value = salt;
	document.getElementById("staffform:newpass").value = hash;
	document.getElementById("staffform:confpass").value = "x";
	
	/**
	 * Just for doctors
	 * Statisticians don't need a public key, private key and mustn't get the group key
	 */
	if(document.getElementById("loggedinform:role").innerHTML == 1){
		generatePublicKeyAndEncryptedPrivateKey(2048, newpass);
		var crypt = new JSEncrypt();
		crypt.setPublicKey(document.getElementById("staffform:publickey").value);
		var groupKey = sessionStorage.groupKey;
	    var encrypted =  crypt.encrypt(groupKey);
	    document.getElementById("staffform:encryptedgroupkey").value = encrypted;
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
 * Generates a random value with the specified length.
 * @param length
 * 			in bits
 * @returns
 * 			the random value
 */
function generateRandomValue(length){
//	var groupKey = CryptoJS.lib.WordArray.random(length/8);	// An alternative to window.crypto.getRandomValues()
	var array = new Uint8Array(length/8);
	window.crypto.getRandomValues(array);
	var groupKey = "";
	for (var i = 0; i < array.length; i++) {
		groupKey = groupKey + String.fromCharCode(array[i]);
	}
	return groupKey;
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
 */
function generatePublicKeyAndEncryptedPrivateKey(length, password){
	var crypt = new JSEncrypt({default_key_size: length});
	crypt.getKey();
	privateKey = crypt.getPrivateKey();
	publicKey = crypt.getPublicKey();
	var encryptedPrivateKey = CryptoJS.AES.encrypt(privateKey, password);
	document.getElementById("staffform:encryptedprivatekey").value = encryptedPrivateKey;
	document.getElementById("staffform:publickey").value = publicKey;
}
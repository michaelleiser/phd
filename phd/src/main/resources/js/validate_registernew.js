/**
 * Validate the staff data, hash the password.
 * The password is hashed with a salt, which is generated before.
 * For doctors create a public key private key pair. Encrypt the private key with the password of the user.
 * For new departments create a group key. Encrypt the group key with the private key the user.
 * @returns
 * 			true if new user can be registered
 */
function validation(){
	var name = document.getElementById("registernewform:name").value;
	if(!name.match(/^[A-Za-z0-9@.]+$/)){
		window.alert("Input a valid name");
		return false;
	}
	var newpass = document.getElementById("registernewform:newpass").value;
	if(!newpass.match(/^(?=^.{8,}$)(?=.*\d)(?=.*[!@#$%^&*]+)(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$/)){
		window.alert("Password must be longer than 8 characters and must contain upper case, lower case letters, digits and special characters (!@#$%^&*)");
		return false;
	}
	var confpass = document.getElementById("registernewform:confpass").value;
	if(confpass != newpass){
		window.alert("Password 1 and 2 are not equal");
		return false;
	}
	var role0 = document.getElementById("registernewform:role:0");
	var role1 = document.getElementById("registernewform:role:1");
	if(!(role0.checked || role1.checked)){
		window.alert("Input a valid role");
		return false;
	}
	var department = document.getElementById("registernewform:department").value;
	var departmentname = "";
	if(department == ""){
		departmentname = document.getElementById("registernewform:departmentname").value;
		if(departmentname == ""){
			window.alert("Select a valid department or enter a new departmentname");
			return false;		
		}
	}
	
	var salt = generateSalt(128);
	var hash = hashPassword(salt + "" + newpass);
	document.getElementById("registernewform:salt").value = salt;
	document.getElementById("registernewform:newpass").value = hash;
	document.getElementById("registernewform:confpass").value = "x";
	
	/**
	 * Just for doctors
	 * Statisticians don't need a public key, private key and mustn't get the group key
	 */
	if(role0.checked){
		var pksk = generatePublicKeyAndEncryptedPrivateKey(2048, newpass);
		document.getElementById("registernewform:publicKey").value = pksk[0];
		document.getElementById("registernewform:encryptedPrivateKey").value = pksk[1];
		
		/**
		 * For new departments -> create an encrypted group key
		 * For existing departments -> encrypted group key is generated in activation process
		 */
		var publicKey = document.getElementById("registernewform:publicKey").value;
		if(departmentname != ""){
			var encryptedGroupKey = generateEncryptedGroupKey(256, publicKey);
			document.getElementById("registernewform:encryptedGroupKey").value = encryptedGroupKey;
		}
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

/**
 * Generate a group key and encrypt it with the public key of the user.
 * @param length
 * 			of the group key
 * @param publicKey
 * 			to encrypt the group key with
 * @returns
 * 			the encrypted group key
 */
function generateEncryptedGroupKey(length, publicKey){
	var groupKey = generateRandomValue(length);
	var crypt = new JSEncrypt();
	crypt.getKey();
	crypt.setPublicKey(publicKey);
	var encryptedGroupKey = crypt.encrypt(groupKey);
	return encryptedGroupKey;
}
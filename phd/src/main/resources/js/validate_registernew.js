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
	if(!name.match(/^[A-Za-z]+$/)){
		window.alert("Input a valid name!");
		return false;
	}
	var pass = document.getElementById("registernewform:password").value;
	if(!pass.match(/^[A-Za-z0-9]+$/)){
		window.alert("Input a valid password!");
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
	var hash = hashPassword(salt + "" + pass);
	document.getElementById("registernewform:salt").value = salt;
	document.getElementById("registernewform:password").value = hash;
	
	/**
	 * Just for doctors
	 * Statisticians don't need a public key, private key and mustn't get the group key
	 */
	if(role0.checked){
		generatePublicKeyAndEncryptedPrivateKey(2048, pass);
		
		/**
		 * For new departments -> create an encrypted group key
		 * For existing departments -> encrypted group key is generated in activation process
		 */
		var publicKey = document.getElementById("registernewform:publickey").value;
		if(departmentname != ""){
			generateEncryptedGroupKey(256, publicKey);
		}
	}

//	alert("finish");
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
	document.getElementById("registernewform:encryptedprivatekey").value = encryptedPrivateKey;
	document.getElementById("registernewform:publickey").value = publicKey;
}

/**
 * Generate a group key and encrypt it with the public key of the user.
 * @param length
 * 			of the group key
 * @param publicKey
 * 			to encrypt the group key with
 */
function generateEncryptedGroupKey(length, publicKey){
	var groupKey = generateRandomValue(length);

	var crypt = new JSEncrypt();
	crypt.getKey();
	
	crypt.setPublicKey(publicKey);
	var encryptedGroupKey = crypt.encrypt(groupKey);
	document.getElementById("registernewform:encryptedgroupkey").value = encryptedGroupKey;
}
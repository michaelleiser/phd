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
	
	hashPassword(pass);
	generatePublicKeyAndEncryptedPrivateKey(pass);
	
	/**
	 * for new departments -> creates an encrypted group key
	 * for existing departments -> encrypted group key is generated in activation process
	 */
	var publicKey = document.getElementById("registernewform:publickey").value;
	if(departmentname != ""){
		generateEncryptedGroupKey(publicKey);
	}

//	alert("finish");
	return true;
}

function hashPassword(pass){
	var hash = CryptoJS.SHA1(pass);
	document.getElementById("registernewform:hashedpassword").value = hash;
}

function generatePublicKeyAndEncryptedPrivateKey(pass){
//  var key = CryptoJS.enc.Hex.parse('00000000000000000000000000000000');
//  var iv = CryptoJS.enc.Hex.parse('00000000000000000000000000000000');

	var crypt = new JSEncrypt();
	crypt.getKey();
	
	privateKey = crypt.getPrivateKey();
	publicKey = crypt.getPublicKey();
	
//	var ciphertext = CryptoJS.AES.encrypt(privateKey, key, {iv:iv, mode:CryptoJS.mode.CBC});
	var encryptedPrivateKey = CryptoJS.AES.encrypt(privateKey, pass);
	
	document.getElementById("registernewform:encryptedprivatekey").value = encryptedPrivateKey;
	document.getElementById("registernewform:publickey").value = publicKey;
}

function generateEncryptedGroupKey(publicKey){
	var groupKey = "testgroupkey123";	// TODO just for testing
//	var groupKey = CryptoJS.lib.WordArray.random(128/8);

	var crypt = new JSEncrypt();
	crypt.getKey();
	
	crypt.setPublicKey(publicKey);
	var encryptedGroupKey = crypt.encrypt(groupKey);
	document.getElementById("registernewform:encryptedgroupkey").value = encryptedGroupKey;
}
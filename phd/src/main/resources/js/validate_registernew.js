function validation(){
	if(!document.getElementById("registernewform:name").value.match(/^[A-Za-z]+$/)){
		window.alert("Input a valid name!");
		return false;
	}
	if(!document.getElementById("registernewform:password").value.match(/^[A-Za-z0-9]+$/)){
		window.alert("Input a valid password!");
		return false;
	}
	if(!(document.getElementById("registernewform:role:0").checked || document.getElementById("registernewform:role:1").checked)){
		window.alert("Input a valid role");
		return false;
	}
	if(document.getElementById("registernewform:department").value == ""){
		window.alert("Input a valid department");
		return false;
	}
	
//    var key = CryptoJS.enc.Hex.parse('00000000000000000000000000000000');
//    var iv = CryptoJS.enc.Hex.parse('00000000000000000000000000000000');
	
	var pass = document.getElementById("registernewform:password").value;
	var hash = CryptoJS.SHA1(pass);
	document.getElementById("registernewform:hashedpassword").value = hash;
	
	var crypt = new JSEncrypt();
	crypt.getKey();
	
	privateKey = crypt.getPrivateKey();
	publicKey = crypt.getPublicKey();
	
//	var ciphertext = CryptoJS.AES.encrypt(privateKey, key, {iv:iv, mode:CryptoJS.mode.CBC});
	var ciphertext = CryptoJS.AES.encrypt(privateKey, pass);
	
	document.getElementById("registernewform:privatekey").value = ciphertext;
	document.getElementById("registernewform:publickey").value = publicKey;
	
	alert("finish");
	return true;
}
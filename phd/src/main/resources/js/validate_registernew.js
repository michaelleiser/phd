function validation(){
	if(!document.getElementById("registernewform:name").value.match(/^[A-Za-z]+$/)){
		window.alert("Input a valid name!");
		return;
	}
	if(!document.getElementById("registernewform:password").value.match(/^[A-Za-z0-9]+$/)){
		window.alert("Input a valid password!");
		return;
	}
	// role
	
	var pass = document.getElementById("registernewform:password").value;
	var hash = CryptoJS.SHA1(pass);
	document.getElementById("registernewform:password").value = hash;

	
	// generate public key, encrypted private key
	
	crypt = new JSEncrypt();
	crypt.getKey();
	
	publicKey = crypt.getPublicKey();
	privateKey = crypt.getPrivateKey();
	
	alert(publicKey);
	alert(privateKey);
}
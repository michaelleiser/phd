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
	hashPassword(newpass);
	encryptPrivateKey(newpass);
	
//	alert("finish");
	return true;
}

function hashPassword(pass){
	var hash = CryptoJS.SHA1(pass);
	document.getElementById("staffform:hashedpassword").value = hash;
}

function encryptPrivateKey(pass){
	var encryptedPrivatekey = sessionStorage.privateKey;
	var reEncryptedPrivateKey = CryptoJS.AES.encrypt(encryptedPrivatekey, pass);
	document.getElementById("staffform:encryptedprivatekey").value = reEncryptedPrivateKey;
}
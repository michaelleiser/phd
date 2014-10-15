function testcrypto(){

	alert(document.getElementById("showmsgid:textenc1").value);
	document.getElementById("showmsgid:textenc1").value = "enc11";
	alert(document.getElementById("showmsgid:textenc2").value);
	document.getElementById("showmsgid:textenc2").value = "enc22";
	alert("finished");

	var hash = CryptoJS.SHA1("Message");
	alert(hash);
	
	var encrypted = CryptoJS.AES.encrypt("Message", "Secret Passphrase");
	 
	alert(encrypted); // U2FsdGVkX1+iX5Ey7GqLND5UFUoV0b7rUJ2eEvHkYqA=
	alert(encrypted.key); // 74eb593087a982e2a6f5dded54ecd96d1fd0f3d44a58728cdcd40c55227522223
	alert(encrypted.iv); // 7781157e2629b094f0e3dd48c4d786115
	alert(encrypted.salt); // 7a25f9132ec6a8b34
	alert(encrypted.ciphertext); // 73e54154a15d1beeb509d9e12f1e462a0
}
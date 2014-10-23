function sessionKeys() {
    if(typeof(Storage) !== "undefined") {
    	if(typeof(sessionStorage.privateKey) === "undefined"){
            sessionStorage.privateKey = document.getElementById("sessionkeysid:private").value;
            sessionStorage.publicKey = document.getElementById("sessionkeysid:public").value;
                    
//            var key = CryptoJS.enc.Hex.parse('00000000000000000000000000000000');
//            var iv = CryptoJS.enc.Hex.parse('00000000000000000000000000000000');
          
            var encrypted = document.getElementById("sessionkeysid:private").value;
//            alert(encrypted);
//            var decrypted = CryptoJS.AES.decrypt(encrypted, key, {iv:iv, mode:CryptoJS.mode.CBC});
            var pass = sessionStorage.password;
            var decrypted = CryptoJS.AES.decrypt(encrypted, pass);

//            alert(decrypted);
            var plaintext = decrypted.toString(CryptoJS.enc.Utf8);
//            alert(plaintext);
            
            sessionStorage.privateKey = plaintext;
    	}
    } else {
    	alert("Sorry, your browser does not support web storage...");
    }
}
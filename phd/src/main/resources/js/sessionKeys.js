/**
 * Store the public key, private key and group key in the session storage.
 * The private key is decrypted with the password of the user.
 * The group key is decrypted with the private key of the user.
 * Alert if session storage is not supported.
 */
function getSessionKeys() {
    if(typeof(Storage) !== "undefined") {
    	if(typeof(sessionStorage.privateKey) === "undefined"){
            sessionStorage.publicKey = document.getElementById("sessionkeys:publicKey").value;
            
            var encrypted = document.getElementById("sessionkeys:privateKey").value;
            var pass = sessionStorage.password;
            var decrypted = CryptoJS.AES.decrypt(encrypted, pass);
            var plaintext = decrypted.toString(CryptoJS.enc.Utf8);
            sessionStorage.privateKey = plaintext; 
            
        	var ciphertext = document.getElementById("sessionkeys:groupKey").value;
        	var crypt = new JSEncrypt();
        	crypt.setPrivateKey(sessionStorage.privateKey);
            var decrypted = crypt.decrypt(ciphertext);
            sessionStorage.groupKey = decrypted;
    	}
    } else {
    	alert("Sorry, your browser does not support web storage...");
    }
}
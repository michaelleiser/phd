/**
 * Encrypt the group key with the public key of the user to be activated.
 * @param element
 * 			or row of the user to be activated
 */
function encryptGroupKey(element){
	var elementid = element.id;
	var substr = elementid.substring(0, 26);	// get the prefix part from id-tag of this entry
	var publicKeyOfSelectedStaff = document.getElementById(substr + "publicKey").value;
	
	var crypt = new JSEncrypt();
	crypt.setPublicKey(publicKeyOfSelectedStaff);
	
	var groupKey = sessionStorage.groupKey;

    var encrypted =  crypt.encrypt(groupKey);
    document.getElementById(substr + "encsecret").value = encrypted;
}



// TODO
function renewGroupKey(){
	// TODO update staffs
	// TODO update patients
	//var publicKey = sessionStorage.publicKey;
	//generateEncryptedGroupKey(publicKey);
}

/**
 * Generate a group key and encrypt it with the public key of the user.
 * @param publicKey
 * 			to encrypt the group key with
 */
function generateEncryptedGroupKey(publicKey){
//	var groupKey = CryptoJS.lib.WordArray.random(128/8);	// An alternative to window.crypto.getRandomValues()

	var array = new Uint8Array(128/8);
	window.crypto.getRandomValues(array);
	var groupKey = "";
	for (var i = 0; i < array.length; i++) {
		groupKey = groupKey + String.fromCharCode(array[i]);
	}

	var crypt = new JSEncrypt();
	crypt.getKey();
	
	crypt.setPublicKey(publicKey);
	var encryptedGroupKey = crypt.encrypt(groupKey);
	document.getElementById("renewform:encryptedgroupkey").value = encryptedGroupKey;
	console.log(encryptedGroupKey);
}
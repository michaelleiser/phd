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
    document.getElementById(substr + "encryptedGroupKey").value = encrypted;
}

/**
 * Generate a new group key and encrypt it with the public key of the staff.
 * Encrypt the group key with the public key of all other staffs.
 * Decrypt the patient's personal data with the old group key and encrypt it with the new group key.
 */
function renewGroupKey(){
	var publicKey = sessionStorage.publicKey;
	var oldGroupKey = sessionStorage.groupKey;
	
	var encryptedGroupKey = generateEncryptedGroupKey(256, publicKey);
	document.getElementById("renewform:renewGroupKey").value = encryptedGroupKey;
	
	var newGroupKey = sessionStorage.groupKey;
	
	var newStaff = renewStaff(newGroupKey);
	document.getElementById("renewform:renewStaff").value = newStaff;
	
	var newPatient = renewPatient(oldGroupKey, newGroupKey);
	document.getElementById("renewform:renewPatient").value = newPatient;
}

/**
 * Generate a group key and encrypt it with the public key of the user.
 * Save the new group key in the session storage.
 * @param length
 * 			of the (new) group key
 * @param publicKey
 * 			to encrypt the group key with
 */
function generateEncryptedGroupKey(length, publicKey){
	var array = new Uint8Array(length/8);
	window.crypto.getRandomValues(array);
	var groupKey = "";
	for (var i = 0; i < array.length; i++) {
		groupKey = groupKey + String.fromCharCode(array[i]);
	}
	sessionStorage.groupKey = groupKey;
	var crypt = new JSEncrypt();
	crypt.getKey();
	crypt.setPublicKey(publicKey);
	var encryptedGroupKey = crypt.encrypt(groupKey);
	return encryptedGroupKey;
}

/**
 * Encrypt the group key with the public keys of all the staffs.
 * @param newGroupKey
 * 			of the group
 * @returns
 * 			a json string of all the staff's encrypted group keys
 */
function renewStaff(newGroupKey){
	var obj = {};
	var size = document.getElementById("staffform:stafftable").rows.length;
	if(size == 0 || document.getElementById("staffform:stafftable:0:id") == null){
		return "";
	}
	var crypt = new JSEncrypt();
	for(var i = 0 ; i < size ; i++){
		var id = document.getElementById("staffform:stafftable:"+i+":id").value;
		var pub = document.getElementById("staffform:stafftable:"+i+":publicKey").value;
		var activated = document.getElementById("staffform:stafftable:"+i+":activated").value;
		if((activated == "true") && (pub != "")){
			crypt.setPublicKey(pub);
			var encrypted = crypt.encrypt(newGroupKey);
			obj[id] = encrypted;
		}
	}
	var json = JSON.stringify(obj);	
	return json;
}

/**
 * Decrypt the patient's personal data with the (old) group key.
 * Encrypt the patient's personal data with the (new) group key.
 * @param oldGroupKey
 * 			of the group
 * @param newGroupKey
 * 			of the group
 * @returns
 * 			a json string of all the patient's personal data
 */
function renewPatient(oldGroupKey, newGroupKey){
	var obj = {};
	var size = document.getElementById("patienttable").rows.length;
	if(size == 0){
		return "";
	}
	for(var i = 0 ; i < size ; i++){
		var id = document.getElementById("patientform:id"+i).value;
		var encrypted = document.getElementById("patientform:encryptedData"+i).value;
		var decrypted = CryptoJS.AES.decrypt(encrypted, oldGroupKey);
		var encryptednew = CryptoJS.AES.encrypt(decrypted, newGroupKey);
		obj[id] = ""+encryptednew;
	}
	var json = JSON.stringify(obj);
	return json;
}
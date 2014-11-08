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

//    alert("finish");
}
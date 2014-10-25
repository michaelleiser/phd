function activate(e){
	var elementid = e.id;
	var substr = elementid.substring(0, 26);
	var publicKey = document.getElementById(substr + "publicKey").value;
	
	var crypt = new JSEncrypt();
	crypt.setPublicKey(publicKey);
	
	var groupKey = sessionStorage.groupKey;

    var encrypted =  crypt.encrypt(groupKey);
    document.getElementById(substr + "encsecret").value = encrypted;
    
    alert("finishedactivation");
}
function deactivate(){
	// TODO set to null the encrypted group key of the staff
}
function showMessage(){
	//var enc = document.getElementById("textenc").value;
	alert(document.getElementById("showmsgid:textenc1").value);
	document.getElementById("showmsgid:textenc1").value = "enc11";
	alert(document.getElementById("showmsgid:textenc2").value);
	document.getElementById("showmsgid:textenc2").value = "enc22";
	alert("finished");

	var crypto = require('crypto');

	var hashes = crypto.getHashes();
	console.log(hashes);
	alert(hashes);

}
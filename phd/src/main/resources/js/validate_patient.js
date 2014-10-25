function validation(){
	if(!document.getElementById("patientform:firstname").value.match(/^[A-Za-z]+$/)){
		window.alert("Input a valid firstname!");
		return false;
	}
	if(!document.getElementById("patientform:lastname").value.match(/^[A-Za-z]+$/)){
		window.alert("Input a valid lastname!");
		return false;
	}
	if(!document.getElementById("patientform:street").value.match(/^[A-Za-z]+$/)){
		window.alert("Input a valid street!");
		return false;
	}
	if(!document.getElementById("patientform:nr").value.match(/^[0-9]+$/)){
		window.alert("Input a valid nr!");
		return false;
	}
	if(!document.getElementById("patientform:city").value.match(/^[A-Za-z]+$/)){
		window.alert("Input a valid city!");
		return false;
	}
	if(!document.getElementById("patientform:zip").value.match(/^[0-9]{4}$/)){
		window.alert("Input a valid zip!");
		return false;
	}
	if(!document.getElementById("patientform:telnumber").value.match(/^[0-9]{10}$/)){
		window.alert("Input a valid telnumber!");
		return false;
	}
	if(!(document.getElementById("patientform:gender:0").checked || document.getElementById("patientform:gender:1").checked)){
		window.alert("Input a valid gender");
		return false;
	}
	if(!document.getElementById("patientform:birth").value.match(/^[0-3][0-9].[0-1][0-9].[1-2][0-9]{3}/)){
		window.alert("Input a valid birth!");
		return;
	}
	

	var firstname = document.getElementById("patientform:firstname").value;
	var lastname = document.getElementById("patientform:lastname").value;
	var street = document.getElementById("patientform:street").value;
	var nr = document.getElementById("patientform:nr").value;
	var city = document.getElementById("patientform:city").value;
	var zip = document.getElementById("patientform:zip").value;
	var telnumber = document.getElementById("patientform:telnumber").value;
	var gender;
	if(document.getElementById("patientform:gender:0").checked){
		gender = document.getElementById("patientform:gender:0").value;
	}
	if(document.getElementById("patientform:gender:1").checked){
		gender = document.getElementById("patientform:gender:1").value;
	}
	var birth = document.getElementById("patientform:birth").value;
	
	var myobj = {"firstname":firstname, "lastname":lastname, "street":street, "nr":nr, "city":city, "zip":zip, "telnumber":telnumber, "gender":gender, "birth":birth};
	var json = JSON.stringify(myobj); 
	// var myobj = JSON.parse(json);
	alert(json);
	
	
//	var groupKey = sessionStorage.groupKey;
	var groupKey = "groupkey123";		// TODO ???
	var encrypted = CryptoJS.AES.encrypt(json, groupKey);
	alert(encrypted);

	document.getElementById("patientform:encryptedPersonalData").value = encrypted;

	return true;
}

function personalData(){
	var groupKey = "groupkey123";		// TODO ???
	var encrypted = document.getElementById("patientform:encryptedPersonalData").value;
	alert(encrypted);
	var decrypted = CryptoJS.AES.decrypt(encrypted, groupKey);
    var json = decrypted.toString(CryptoJS.enc.Utf8);
    alert(json);
	var myobj = JSON.parse(json);

	document.getElementById("patientform:firstname1").value = myobj["firstname"];
	document.getElementById("patientform:lastname1").value = myobj["lastname"];
//	document.getElementById("patientform:street").value = myobj["street"];
//	document.getElementById("patientform:nr").value = myobj["nr"];
//	document.getElementById("patientform:city").value = myobj["city"];
//	document.getElementById("patientform:zip").value = myobj["zip"];
//	document.getElementById("patientform:telnumber").value = myobj["telnumber"];
	if(myobj["gender"] == "male"){
		document.getElementById("patientform:gender1:0").checked = true;
	}
	if(myobj["gender"] == "female"){
		document.getElementById("patientform:gender1:1").checked = true;
	}
	document.getElementById("patientform:birth1").value = myobj["birth"];
	
}



function personalData2(){
	var groupKey = "groupkey123";
	var size = document.getElementById("searchform:outputMessage").rows.length - 2;
	for(var i = 0 ; i < size ; i++){
		var encrypted = document.getElementById("searchform:outputMessage:" + i + ":encdata").value;
		var decrypted = CryptoJS.AES.decrypt(encrypted, groupKey);
		var json = decrypted.toString(CryptoJS.enc.Utf8);
		var myobj = JSON.parse(json);
		document.getElementById("searchform:outputMessage:" + i + ":decfirstname").value = myobj["firstname"];
	}
	
}
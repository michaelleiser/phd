function validation(){
	var firstname = document.getElementById("patientform:firstname").value;
	if(!firstname.match(/^[A-Za-z]+$/)){
		window.alert("Input a valid firstname!");
		return false;
	}
	var lastname = document.getElementById("patientform:lastname").value;
	if(!lastname.match(/^[A-Za-z]+$/)){
		window.alert("Input a valid lastname!");
		return false;
	}
	var street = document.getElementById("patientform:street").value;
	if(!street.match(/^[A-Za-z]+$/)){
		window.alert("Input a valid street!");
		return false;
	}
	var nr = document.getElementById("patientform:nr").value;
	if(!nr.match(/^[0-9]+$/)){
		window.alert("Input a valid nr!");
		return false;
	}
	var city = document.getElementById("patientform:city").value;
	if(!city.match(/^[A-Za-z]+$/)){
		window.alert("Input a valid city!");
		return false;
	}
	var zip = document.getElementById("patientform:zip").value;
	if(!zip.match(/^[0-9]{4}$/)){
		window.alert("Input a valid zip!");
		return false;
	}
	var telnumber = document.getElementById("patientform:telnumber").value;
	if(!telnumber.match(/^[0-9]{10}$/)){
		window.alert("Input a valid telnumber!");
		return false;
	}
	
	var gender;
	var gender0 = document.getElementById("patientform:gender:0");
	var gender1 = document.getElementById("patientform:gender:1");
	if(!(gender0.checked || gender1.checked)){
		window.alert("Input a valid gender");
		return false;
	}
	if(gender0.checked){
		gender = gender0.value;
	}
	if(gender1.checked){
		gender = gender1.value;
	}
	var birth = document.getElementById("patientform:birth").value;
	if(!birth.match(/^[0-3][0-9].[0-1][0-9].[1-2][0-9]{3}/)){
		window.alert("Input a valid birth!");
		return false;
	}
	
	var myobj = {"firstname":firstname, "lastname":lastname, "street":street, "nr":nr, "city":city, "zip":zip, "telnumber":telnumber, "gender":gender, "birth":birth};
	var json = JSON.stringify(myobj);	// var myobj = JSON.parse(json);
	encryptPersonalData(json);

	alert("finish");
	return true;
}

function encryptPersonalData(json){
	var groupKey = sessionStorage.groupKey;
	var encrypted = CryptoJS.AES.encrypt(json, groupKey);
	document.getElementById("patientform:encryptedPersonalData").value = encrypted;
}

function personalData(){
	var groupKey = sessionStorage.groupKey;
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
	var groupKey = sessionStorage.groupKey;
	var size = document.getElementById("searchform:outputMessage").rows.length - 2;
	for(var i = 0 ; i < size ; i++){
		var encrypted = document.getElementById("searchform:outputMessage:" + i + ":encdata").value;
		var decrypted = CryptoJS.AES.decrypt(encrypted, groupKey);
		var json = decrypted.toString(CryptoJS.enc.Utf8);
		var myobj = JSON.parse(json);
		document.getElementById("searchform:outputMessage:" + i + ":decfirstname").value = myobj["firstname"];
	}
}
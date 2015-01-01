/**
 * Validate the patient data and encrypt it with the group key.
 * Clear the form, otherwise formular data would be sent in cleartext to the server.
 * @returns
 * 			true if patient can be created
 */
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
	var birthday = document.getElementById("patientform:birthday").value;
	if(!birthday.match(/^[0-3][0-9].[0-1][0-9].[1-2][0-9]{3}/)){
		window.alert("Input a valid birthday!");
		return false;
	}
	
	var myobj = {"firstname":firstname, "lastname":lastname, "street":street, "nr":nr, "city":city, "zip":zip, "telnumber":telnumber, "gender":gender, "birthday":birthday};
	var json = JSON.stringify(myobj);
	var encrypted = encryptPersonalData(json);
	document.getElementById("patientform:encryptedPersonalData").value = encrypted;

	clearForm();

	return true;
}

/**
 * Encrypt the specified data with the group key.
 * @param data
 * 			to be encrypted
 * @returns
 * 			the encrypted data
 */
function encryptPersonalData(data){
	var groupKey = sessionStorage.groupKey;
	return CryptoJS.AES.encrypt(data, groupKey);
}

/**
 * Decrypt the personal data of one patient.
 */
function decryptPersonalData(){
	var groupKey = sessionStorage.groupKey;
	var encrypted = document.getElementById("patientform:encryptedPersonalData").value;
	var decrypted = CryptoJS.AES.decrypt(encrypted, groupKey);
    var json = decrypted.toString(CryptoJS.enc.Utf8);
	var myobj = JSON.parse(json);

	document.getElementById("patientform:firstname").value = myobj["firstname"];
	document.getElementById("patientform:lastname").value = myobj["lastname"];
	document.getElementById("patientform:street").value = myobj["street"];
	document.getElementById("patientform:nr").value = myobj["nr"];
	document.getElementById("patientform:city").value = myobj["city"];
	document.getElementById("patientform:zip").value = myobj["zip"];
	document.getElementById("patientform:telnumber").value = myobj["telnumber"];
	if(myobj["gender"] == "male"){
		document.getElementById("patientform:gender:0").checked = true;
	}
	if(myobj["gender"] == "female"){
		document.getElementById("patientform:gender:1").checked = true;
	}
	document.getElementById("patientform:birthday").value = myobj["birthday"];
}

/**
 * Decrypt a part of the personal data of one patient.
 */
function decryptPersonalDataForFormular(){
	if(document.getElementById("formularform:encryptedPersonalData") == null){
		return;
	}
	var groupKey = sessionStorage.groupKey;
	var encrypted = document.getElementById("formularform:encryptedPersonalData").value;
	var decrypted = CryptoJS.AES.decrypt(encrypted, groupKey);
    var json = decrypted.toString(CryptoJS.enc.Utf8);
	var myobj = JSON.parse(json);
	document.getElementById("firstname").innerHTML = myobj["firstname"];
	document.getElementById("lastname").innerHTML = myobj["lastname"];
	document.getElementById("birthday").innerHTML = myobj["birthday"];
}


var pagenr = 1;
var pagesize = 10;
var size = 0;
var patients;
var visiblerows;
var first = 0;

var worker;
var groupKey;

/**
 * Decrypt the personal data of the patients and display the result set.
 * The decryption procedure is done in a web worker.
 */
function decryptPersonalDataForSearchWebWorker() {
//	size = document.getElementById("patienttable").rows.length - 1;	// -1 because of the header row
//	groupKey = self.sessionStorage.groupKey;
//	patients = new Array();
//	visiblerows = new Array();
//	var encryptedData = [];
//	for(var i = 0 ; i < size ; i++){
//		var encrypted = document.getElementById("searchform:encryptedPersonalData" + i).value;
//		var obj = {row:i, data:encrypted};
//		encryptedData.push(obj);
//	}
//	var data = {size: size, groupKey: groupKey, encryptedData: encryptedData};
//    if(typeof(Worker) !== "undefined") {
//        if(typeof(worker) === "undefined") {
//            worker = new Worker("/phd/javax.faces.resource/worker.js.xhtml?ln=js");
//            worker.postMessage(data);
//        }
//        var j = 0;
//        worker.onmessage = function(evt) {
//        	var obj = evt.data;
//        	var i = obj.row;
//        	var data = obj.data;
//          patients.push({"id":data.id, "firstname":data.firstname, "lastname":data.lastname, "birthday":data.birthday});
//			document.getElementById("searchform:firstname" + i).innerHTML = data.firstname;
//			document.getElementById("searchform:lastname" + i).innerHTML = data.lastname;
//			document.getElementById("searchform:birthday" + i).innerHTML = data.birthday;
//			visiblerows.push(i);
//			j++;
//			if(j == size){
//				display();
//			}
//        };
//    } else {
//    	console.log("Web Worker not supported");    
//    }
////  display();
////	patients.sort();		// TODO alphabetisch sortieren
}

/**
 * Decrypt the personal data of the patients and display the result set.
 */
function decryptPersonalDataForSearch(){
	size = document.getElementById("patienttable").rows.length;	// -1 because of the header row
	groupKey = self.sessionStorage.groupKey;
	patients = new Array();
	visiblerows = new Array();
	for(var i = 0 ; i < size ; i++){
		var encrypted = document.getElementById("searchform:encryptedPersonalData" + i).value;
		var decrypted = CryptoJS.AES.decrypt(encrypted, groupKey);
		if(decrypted != ""){
			var json = decrypted.toString(CryptoJS.enc.Utf8);
			var patient = JSON.parse(json);
			var id = document.getElementById("searchform:id" + i).innerHTML;
			var writeable = "true";
			var owner = "true";
			patients.push({"id":id, "firstname":patient["firstname"], "lastname":patient["lastname"], "birthday":patient["birthday"], "writeable":writeable, "owner":owner});
			document.getElementById("searchform:firstname" + i).innerHTML = patients[i].firstname;
			document.getElementById("searchform:lastname" + i).innerHTML = patients[i].lastname;
			document.getElementById("searchform:birthday" + i).innerHTML = patients[i].birthday;
			visiblerows.push(i);
		}
	}
	display();

	sessionStorage.patients = JSON.stringify(patients);	// TODO
}

/**
 * Filter the patient data with the filter name and display the result set.
 * @returns
 * 			false to avoid reloading the page
 */
function filter(){
	pagenr = 1;
	document.getElementById("searchform:pagenumber").innerHTML = "Page " + pagenr;
	first = 0;
	visiblerows = new Array();
	var filtername = document.getElementById("searchform:filter").value.toLowerCase();
	for(var i = 0 ; i < patients.length ; i++){
		if((patients[i].firstname.toLowerCase().indexOf(filtername) > -1) ||
			(patients[i].lastname.toLowerCase().indexOf(filtername) > -1)	){
			document.getElementById("row" + i).style.display = "inherit";
			visiblerows.push(i);
		}else{
			document.getElementById("row" + i).style.display = "none";
		}
	}
	display();
	return false;
}

/**
 * Display exactly one page of all the visible rows (prefiltered rows).
 */
function display(){
	for(var i = 0 ; i < visiblerows.length ; i++){
		if((i >= first) && (i < (first + pagesize))){
			document.getElementById("row" + visiblerows[i]).style.display = "inherit";
			if(i % 2 == 0){
				document.getElementById("row" + visiblerows[i]).className = "table-odd-row";
			} else {
				document.getElementById("row" + visiblerows[i]).className = "table-even-row";
			}
		} else{
			document.getElementById("row" + visiblerows[i]).style.display = "none";
		}
	}
	if(pagenr == 1){
		document.getElementById("searchform:backward").style.visibility = "hidden";
	} else{
		document.getElementById("searchform:backward").style.visibility = "visible";
		document.getElementById("searchform:backward").value = "<< Page " + (pagenr - 1);
	}
	if(pagenr >= visiblerows.length/pagesize){
		document.getElementById("searchform:forward").style.visibility = "hidden";
	} else{
		document.getElementById("searchform:forward").style.visibility = "visible";
		document.getElementById("searchform:forward").value = "Page " + (pagenr + 1) + " >>";
	}	
}

/**
 * Go one page backward.
 * @returns
 * 			false to avoid reloading the page
 */
function backward(){
	if(pagenr > 1){
		pagenr--;
		first = first - pagesize;
		if(first < 0){
			first = 0;
		}
		document.getElementById("searchform:pagenumber").innerHTML = "Page " + pagenr;
	}
	display();
	return false;
}

/**
 * Go one page forward.
 * @returns
 * 			false to avoid reloading the page
 */
function forward(){
	if(pagenr < visiblerows.length/pagesize){
		pagenr++;
		first = first + pagesize;
		document.getElementById("searchform:pagenumber").innerHTML = "Page " + pagenr;
	}
	display();
	return false;
}

/**
 * Clear the cleartext entries of the patient form. Otherwise the form entries would be sent in cleartext to the server.
 */
function clearForm(){
	document.getElementById("patientform:firstname").value = "x";
	document.getElementById("patientform:lastname").value = "x";
	document.getElementById("patientform:street").value = "x";
	document.getElementById("patientform:nr").value = "x";
	document.getElementById("patientform:city").value = "x";
	document.getElementById("patientform:zip").value = "x";
	document.getElementById("patientform:telnumber").value = "x";
	document.getElementById("patientform:gender:0").checked = true;
	document.getElementById("patientform:birthday").value = "x";
}
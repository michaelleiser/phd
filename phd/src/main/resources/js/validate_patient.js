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
	var json = JSON.stringify(myobj);	// var myobj = JSON.parse(json);
//	alert(json);
	
	encryptPersonalData(json);

//	alert("finish");
	return true;
}

function encryptPersonalData(json){
	var groupKey = sessionStorage.groupKey;
	var encrypted = CryptoJS.AES.encrypt(json, groupKey);
	document.getElementById("patientform:encryptedPersonalData").value = encrypted;
}

function decryptPersonalData(){
	var groupKey = sessionStorage.groupKey;
	var encrypted = document.getElementById("patientform:encryptedPersonalData").value;
//	alert(encrypted);
	var decrypted = CryptoJS.AES.decrypt(encrypted, groupKey);
    var json = decrypted.toString(CryptoJS.enc.Utf8);
//    alert(json);
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



var pagenr = 1;
var pagesize = 10;
var size = 0;
var patients;
var visiblerows;
var first = 0;


var w;
var groupKey;


function start() {
	size = document.getElementById("testtable").rows.length - 1;	// -1 because of the header row
	groupKey = self.sessionStorage.groupKey;
	var patients = [];
	for(var i = 0 ; i < size ; i++){
		var encrypted = document.getElementById("searchform:encdata" + i).value;
		patients.push(encrypted);
	}
	
	var arr = {size: size, groupKey: groupKey, encryptedData: patients};
	
    if(typeof(Worker) !== "undefined") {
        if(typeof(w) == "undefined") {
            w = new Worker("/phd/javax.faces.resource/worker.js.xhtml?ln=js");
            w.postMessage(arr);
        }
        w.onmessage = function(event) {
//        	console.log("Event " + event.data);
//        	console.log("Data" + event.data["firstname"]);
//        	document.getElementById("result").value = event.data["firstname"];
        };
    } else {
    	console.log("Web Worker not supported");    
    }
}

function decryptPersonalDataForSearch(){
	size = document.getElementById("testtable").rows.length - 1;	// -1 because of the header row
	groupKey = self.sessionStorage.groupKey;
	var firstname;
	var lastname;
	var birthday;
	patients = new Array();
	visiblerows = new Array();
	var time1 = new Date().getTime();
	for(var i = 0 ; i < size ; i++){
		var encrypted = document.getElementById("searchform:encdata" + i).value;
		var decrypted = CryptoJS.AES.decrypt(encrypted, groupKey);
		if(decrypted != ""){
			var json = decrypted.toString(CryptoJS.enc.Utf8);
			var myobj = JSON.parse(json);
			patients.push({"firstname":myobj["firstname"], "lastname":myobj["lastname"], "birthday":myobj["birthday"]});
			document.getElementById("searchform:firstname" + i).innerHTML = patients[i].firstname;
			document.getElementById("searchform:lastname" + i).innerHTML = patients[i].lastname;
			document.getElementById("searchform:birthday" + i).innerHTML = patients[i].birthday;
			visiblerows.push(i);
		}
	}
	var time2 = new Date().getTime();
	console.log("TIME direkt: " + (time2 - time1));
//	patients.sort();		// TODO alphabetisch sortieren
	display();
}


function filter(){
	visiblerows = new Array();
	var filtername = document.getElementById("searchform:filter").value.toLowerCase();
//	console.log("FILTERING::" + filtername);
	for(var i = 0 ; i < patients.length ; i++){
		if((patients[i].firstname.toLowerCase().indexOf(filtername) > -1) ||
			(patients[i].lastname.toLowerCase().indexOf(filtername) > -1)	){
			document.getElementById("row" + i).style.display = "inherit";
//			console.log("match: "+ patients[i].firstname  +  patients[i].lastname);
			visiblerows.push(i);
		}else{
			document.getElementById("row" + i).style.display = "none";
//			console.log("dismatch: "+ patients[i].firstname  +  patients[i].lastname);
		}
	}
	display();
	return false;
}

function display(){
	for(var i = 0 ; i < visiblerows.length ; i++){
		if((i >= first) && (i < (first + pagesize))){
			document.getElementById("row" + visiblerows[i]).style.display = "inherit";
		} else{
			document.getElementById("row" + visiblerows[i]).style.display = "none";
		}
	}
	if(pagenr == 1){
		document.getElementById("searchform:backward").style.visibility = "hidden";
	} else{
		document.getElementById("searchform:backward").style.visibility = "visible";
	}
	if(pagenr >= visiblerows.length/pagesize){
		document.getElementById("searchform:forward").style.visibility = "hidden";
	} else{
		document.getElementById("searchform:forward").style.visibility = "visible";
	}
}

function backward(){
	if(pagenr > 1){
		pagenr--;
		first = first - pagesize;
		if(first < 0){
			first = 0;
		}
		document.getElementById("searchform:pagenumber").innerHTML = pagenr;
	}
	display();
	return false;
}

function forward(){
	if(pagenr < visiblerows.length/pagesize){
		pagenr++;
		first = first + pagesize;
		document.getElementById("searchform:pagenumber").innerHTML = pagenr;
	}
	display();
	return false;
}

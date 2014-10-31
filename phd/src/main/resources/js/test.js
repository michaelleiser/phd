var dbname = "test";
var db;
var request;

function indexeddb() {
	alert("start");

	request = indexedDB.open(dbname, 2);
	request.onupgradeneeded = function(){
		console.log("New DB");
		db = this.result;
		if(!db.objectStoreNames.contains("patients")){
			store = db.createObjectStore("patients", {
				keyPath: "key",
				autoIncrement: true
			});
			store.createIndex("firstname", "firstname", {unique:false});
			store.createIndex("lastname", "lastname", {unique:false});
		}
	};

	request.onsuccess = function(evt){
		console.log("Open DB" + evt);
		db = this.result;
	 
		var item = { firstname: "mickey", lastname: "mouse" };

		var trans = db.transaction(["patients"], "readwrite");
		var store = trans.objectStore("patients")
		var request = store.put(item);
	 
//		request.onsuccess = function(evt){	// iterate over all
//			console.log("Save entry " + evt.target.result);
//			var trans = db.transaction(["patients"], "readonly");
//			var store = trans.objectStore("patients");
//			var range = IDBKeyRange.lowerBound(0);
//			var cursorRequest = store.openCursor(range);
//			cursorRequest.onsuccess = function(evt){
//				console.log("Cursor " + evt);
//				var result = evt.target.result;
//				if(result){
//					console.log("Found entry " + result.value);
//					result.continue();
//				}
//			};
//		};
		
		
		request.onsuccess = function(evt){		// get one entry
			console.log("Save entry " + evt.target.result);
			var trans = db.transaction(["patients"]);
			var store = trans.objectStore("patients");
			var request = store.get(1);
			request.onsuccess = function(evt){
				console.log("Found entry " + request.result.firstname + request.result.lastname);
			};
		};
		
		console.log("fertig");
	}
	
	request.onerror = function(evt){
		console.log("error" + evt);
	}
	
	alert("finish");
}




var size = 0;
var patients;

function decryptPersonalDataForSearch(){
	alert("start");
	var groupKey = sessionStorage.groupKey;
	var firstname;
	var lastname;
	var birthday;
	patients = new Array();
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
		}
	}
	alert("fini");
}


function filter(){
	alert("filter");
//	patients_f = new Array();
	var filtername = document.getElementById("searchform:filter").value.toLowerCase();
	for(var i = 0 ; i < patients.length ; i++){
		if((patients[i].firstname.toLowerCase().indexOf(filtername) > -1) ||
			(patients[i].lastname.toLowerCase().indexOf(filtername) > -1)	){
			document.getElementById("row" + i).style.display = "inherit";
			alert("match: "+ patients[i].firstname  +  patients[i].lastname);
//			patients_f.push(patients[i]);
		}else{
			alert("dismatch: "+ patients[i].firstname  +  patients[i].lastname);
			document.getElementById("row" + i).style.display = "none";
		}
	}
	alert("fin");
	return false;
}

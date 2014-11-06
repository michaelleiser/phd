var dbname = "test";
var dbversion = 3;
var db;
var request;

function indexeddb() {
	console.log("start");

	request = indexedDB.open("test1", 1);
	request.onupgradeneeded = function(){
		console.log("New DB");
		db = this.result;
		db.deleteObjectStore("patients");
		if(!db.objectStoreNames.contains("patients")){
			store = db.createObjectStore("patients", {
				keyPath: "key",
				autoIncrement: true
			});
			store.createIndex("id", "id", {unique:true});
			store.createIndex("firstname", "firstname", {unique:false});
			store.createIndex("lastname", "lastname", {unique:false});
		}
	};

	request.onsuccess = function(evt){
		console.log("Open DB" + evt);
		db = this.result;
	 
		var item1 = { id: "1", firstname: "mickey", lastname: "mouse" };
		var item2 = { id: "2", firstname: "mickey", lastname: "maus" };
		var item3 = { id: "3", firstname: "mick", lastname: "myer" };

		var trans = db.transaction(["patients"], "readwrite");
		var store = trans.objectStore("patients")
		var request = store.put(item1);
		request = store.put(item2);
		request = store.put(item3);
	 
		request.onsuccess = function(evt){	// iterate over all
			console.log("Save entry " + evt.target.result);
			var trans = db.transaction(["patients"], "readonly");
			var store = trans.objectStore("patients");
			var range = IDBKeyRange.only("mick");
			var index = store.index("firstname");
			var request = index.openCursor();
			request.onsuccess = function(evt){
				var result = evt.target.result;
				if(result){
					console.log("Found entry " + result.value.id + result.value.firstname + result.value.lastname);
					result.continue();
				} else{
					console.log("no match");
				}
			};
		};
		
		
//		request.onsuccess = function(evt){		// get one entry
//			console.log("Save entry " + evt.target.result);
//			var trans = db.transaction(["patients"]);
//			var store = trans.objectStore("patients");
//			var request = store.get(1);
//			request.onsuccess = function(evt){
//				console.log("Found entry " + request.result.firstname + request.result.lastname);
//			};
//		};
		
		console.log("fertig");
	}
	
	request.onerror = function(evt){
		console.log("error" + evt);
	}
	
	console.log("finish");
}




var size = 0;
var patients;

function decryptPersonalDataForSearch(){
	deletedb();
	initdb();
//	filter();
//	deletedb();
}


function deletedb(){
	request = indexedDB.deleteDatabase(dbname);
	request.onsuccess = function(){
		console.log("Success deleted");
	}
	request.onerror = function(){
		console.log("error deleted");
	}
	request.onblocked = function(){
		console.log("blocked deleted");
	}
}



function filter(){
	
	var element = document.getElementById("result");
	while(element.firstChild){
		element.removeChild(element.firstChild);
	}
	
	console.log("filter start");
	var filtername = document.getElementById("searchform:filter").value.toLowerCase();

	request = indexedDB.open(dbname, dbversion);
	request.onsuccess = function(evt){
		var table = document.createElement("table");
		db = this.result;
		var trans = db.transaction(["patients"], "readonly");
		var store = trans.objectStore("patients");
		var index = store.index("firstname");
		var request = index.get(filtername);
		request.onsuccess = function(evt){
//			console.log("Found entry " + request.result.firstname + request.result.lastname);
			var result = evt.target.result;
			if(result){
				var row = document.createElement("tr");
				for(var field in result){
					var col = document.createElement("td");
					col.innerHTML = result[field];
					row.appendChild(col);
					console.log("resfil:: " + field + "=" + result[field]);
				}
				table.appendChild(row);
//				result.continue();
			}else{
				console.log("no match");
			}
		};
		
		element.appendChild(table);
	}
	console.log("filter end");
	return false;
}



function initdb() {
	
	var element = document.getElementById("result");
	
	console.log("init start");
	var groupKey = sessionStorage.groupKey;
	size = document.getElementById("testtable").rows.length - 1;	// -1 because of the header row
	
	request = indexedDB.open(dbname, dbversion);
	request.onupgradeneeded = function(){
		console.log("New DB");
		db = this.result;
		if(!db.objectStoreNames.contains("patients")){
			store = db.createObjectStore("patients", {
				keyPath: "key",
				autoIncrement: true
			});
			store.createIndex("id", "id", {unique:true});
			store.createIndex("firstname", "firstname", {unique:false});
			store.createIndex("lastname", "lastname", {unique:false});
		}
	};

	request.onsuccess = function(evt){
		console.log("Open DB" + evt);
		db = this.result;
		var trans = db.transaction(["patients"], "readwrite");
		var store = trans.objectStore("patients")
		var time1 = new Date().getTime();
		for(var i = 0 ; i < size ; i++){
			var encrypted = document.getElementById("searchform:encdata" + i).value;
			var decrypted = CryptoJS.AES.decrypt(encrypted, groupKey);
			if(decrypted != ""){
				var json = decrypted.toString(CryptoJS.enc.Utf8);
				var myobj = JSON.parse(json);
				var item = { id: document.getElementById("searchform:id"+i).innerHTML, firstname: myobj["firstname"], lastname: myobj["lastname"] };
				var request = store.put(item);
				console.log("put " + item);
			}
		}
		var time2 = new Date().getTime();
		alert("TIME: " + (time2 - time1));
		
		var table = document.createElement("table");
		
		var trans1 = db.transaction(["patients"], "readonly");
		var store1 = trans1.objectStore("patients");
		var range1 = IDBKeyRange.lowerBound(0);
		var cursorRequest1 = store1.openCursor(range1);
		cursorRequest1.onsuccess = function(evt){;
			var result = evt.target.result;
			if(result){
				var row = document.createElement("tr");
				var col1 = document.createElement("td");
				col1.innerHTML = result.value.id;
				row.appendChild(col1);
				var col2 = document.createElement("td");
				col2.innerHTML = result.value.firstname;
				row.appendChild(col2);
				var col3 = document.createElement("td");
				col3.innerHTML = result.value.lastname;
				row.appendChild(col3);
				table.appendChild(row);
				
				console.log("Found entry " + result.value.id + result.value.firstname + result.value.lastname);
				result.continue();
			}
		};
		element.appendChild(table);
	}
	
	request.onerror = function(evt){
		console.log("error" + evt);
	}
	
	console.log("init end");
}
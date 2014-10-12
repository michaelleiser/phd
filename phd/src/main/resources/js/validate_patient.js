function validation(){
	if(!document.getElementById("patientform:firstname").value.match(/^[A-Za-z]+$/)){
		window.alert("Input a valid firstname!");
		return;
	}
	if(!document.getElementById("patientform:lastname").value.match(/^[A-Za-z]+$/)){
		window.alert("Input a valid lastname!");
		return;
	}
	if(!document.getElementById("patientform:street").value.match(/^[A-Za-z]+$/)){
		window.alert("Input a valid street!");
		return;
	}
	if(!document.getElementById("patientform:nr").value.match(/^[0-9]+$/)){
		window.alert("Input a valid nr!");
		return;
	}
	if(!document.getElementById("patientform:city").value.match(/^[A-Za-z]+$/)){
		window.alert("Input a valid city!");
		return;
	}
	if(!document.getElementById("patientform:zip").value.match(/^[A-Za-z]+$/)){
		window.alert("Input a valid zip!");
		return;
	}
	if(!document.getElementById("patientform:telnumber").value.match(/^[0-9]{10}$/)){
		window.alert("Input a valid telnumber!");
		return;
	}
//	if(!document.getElementById("patientform:gender").value.match(/^[A-Za-z]+$/)){
//		window.alert("Input a valid gender!");
//		return;
//	}
//	if(!document.getElementById("patientform:birth").value.match(/^[A-Za-z]+$/)){
//		window.alert("Input a valid birth!");
//		return;
//	}
}
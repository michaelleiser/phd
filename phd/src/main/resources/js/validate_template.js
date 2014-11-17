/**
 * 
 */
function validation(){
	
	
	var s = document.getElementById("formularform:string").value;
	
	if(!s.match(/^[A-Za-z0-9]+$/)){
		
		window.alert("Input a valid Answer!");
		return false;
	}

	

	alert("finish");
	return true;
}

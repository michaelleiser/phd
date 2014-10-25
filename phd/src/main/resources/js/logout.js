function destroyKeys() {
	var i = sessionStorage.length - 1;
	for( ; i >= 0 ; i--){
		var key = sessionStorage.key(i);
		sessionStorage.removeItem(key);
	}
}
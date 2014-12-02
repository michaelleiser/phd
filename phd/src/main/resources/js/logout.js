/**
 * Clear the session storage of the user, which could contain sensitive data.
 */
function clearSessionStorage() {
	var i = sessionStorage.length - 1;
	for( ; i >= 0 ; i--){
		var key = sessionStorage.key(i);
		sessionStorage.removeItem(key);
	}
}
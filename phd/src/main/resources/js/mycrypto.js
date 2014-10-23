function testsha(){
	var plaintext = document.getElementById("shaid:testsha").value;
	alert(plaintext);
    var hashed = CryptoJS.SHA1(plaintext);
    alert(hashed);
    document.getElementById("shaid:testshahash").value = hashed;
    alert("finishedhashing");
}
function testaesencrypt(){
	var plaintext = document.getElementById("aesid:testaesencrypt").value;
	alert(plaintext);

    var key = CryptoJS.enc.Hex.parse('00000000000000000000000000000000');
    var iv = CryptoJS.enc.Hex.parse('00000000000000000000000000000000');
	
    var encrypted = CryptoJS.AES.encrypt(plaintext, key, {iv:iv, mode:CryptoJS.mode.CBC});
    alert(encrypted);
    var ciphertext = encrypted.ciphertext.toString(CryptoJS.enc.Base64);
    alert(ciphertext);
    document.getElementById("aesid:testaesdecrypt").value = encrypted;
    
    alert("finishedencrypt");
}
function testaesdecrypt(){
	var ciphertext = document.getElementById("aesid:testaesdecrypt").value;
	alert(ciphertext);

    var key = CryptoJS.enc.Hex.parse('00000000000000000000000000000000');
    var iv = CryptoJS.enc.Hex.parse('00000000000000000000000000000000');
	
    var decrypted = CryptoJS.AES.decrypt(ciphertext, key, {iv:iv, mode:CryptoJS.mode.CBC});
    var plaintext = decrypted.toString(CryptoJS.enc.Utf8);
    alert(plaintext);
    document.getElementById("aesid:testaesencrypt").value = plaintext;
    
    alert("finisheddecrypt");
}

function testrsaencrypt(){
	var plaintext = document.getElementById("rsaid:testrsaencrypt").value;
	alert(plaintext);

	var crypt = new JSEncrypt();
	crypt.setPublicKey("-----BEGIN PUBLIC KEY-----MIGeMA0GCSqGSIb3DQEBAQUAA4GMADCBiAKBgH9UnJIIIwEkwxEFbmGbx/zJjhJGjl21yyNx+SXE9SAwM9ci4TjXhFxrSvm+nZetLKRsa90awF2oGa0Tb/+94Ibab+1T1DetZLvJZ55EOOv0oL6sN8y5JkKcPbHimMxYOsZQvXv98ZS29hxnSW+0kDbFeyDg0DeNKglTos7a9fEtAgMBAAE=-----END PUBLIC KEY-----");

    var encrypted =  crypt.encrypt(plaintext);;
    alert(encrypted);
    document.getElementById("rsaid:testrsadecrypt").value = encrypted;
    
    alert("finishedencrypt");
}
function testrsadecrypt(){
	var ciphertext = document.getElementById("rsaid:testrsadecrypt").value;
	alert(ciphertext);

	var crypt = new JSEncrypt();
	crypt.setPrivateKey("-----BEGIN RSA PRIVATE KEY-----MIICWwIBAAKBgH9UnJIIIwEkwxEFbmGbx/zJjhJGjl21yyNx+SXE9SAwM9ci4TjXhFxrSvm+nZetLKRsa90awF2oGa0Tb/+94Ibab+1T1DetZLvJZ55EOOv0oL6sN8y5JkKcPbHimMxYOsZQvXv98ZS29hxnSW+0kDbFeyDg0DeNKglTos7a9fEtAgMBAAECgYB+wGSn/g+SeNzx2b06z09yddXGnZPk9y5Dl5ZUIc99/l3soF0hL3Ekau4U1MqeDZauCvwI+zNZSBhfMUVBiOuzAv6d/y3RjzWPMNCNFWg5RMPwPqLf/uOHcIYe3E811SCsAqIeccJriFck/e8ei6zHtws02eFR8DSCEb3eJUMKJQJBAN5kNjOuBBcJGx5MISUQzBpG0mWsmr5+AY2i6+oAPBB/hLOdLxHbPALmO2C4w4e0jtQmDnZX7VJFLQALdzBdfacCQQCSkrjDQJx9CCBk6lAsDMZyo2SAc0GS/HRqImbv6WrxEzeGHXmRAM9tCc2lAF9I29C81j9ije+GIrTNfNMDkX0LAkBAdQUst2wiDRaaEnCs4JB3sT08EOoM+lKS7by45vaBxaP9tzo0T7m2EWr2Xrb1d0TKgnlAnb2AUeJ4F9qk3wQTAkEAiyJx9w6nPdzv+zudnSMHYh0OMwy1i5TP4UwE0OqPVIa8ZzglhOry+/uZVW70BFJ9ZDQweRpo67iYTpOWL0X1HwJAHZZFTuCTOE5MOOpy96ZhkoyQV1/pl2bS/yzNXhOby0mKjWybnU9vfyExn1JKDXBBwz+EI5d8thSdSRbCQ9PjBg==-----END RSA PRIVATE KEY-----");

	
    var decrypted = crypt.decrypt(ciphertext);
    document.getElementById("rsaid:testrsaencrypt").value = decrypted;
    
    alert("finisheddecrypt");
}




function receive() {       
        var key = CryptoJS.enc.Hex.parse('00000000000000000000000000000000');
        var iv = CryptoJS.enc.Hex.parse('00000000000000000000000000000000');   
        var encrypted = document.getElementById("receiveid:encrypteddata").value;
        var decrypted = CryptoJS.AES.decrypt(encrypted, key, {iv:iv, mode:CryptoJS.mode.CBC});
        var plaintext = decrypted.toString(CryptoJS.enc.Utf8);
        document.getElementById("receiveid:decrypteddata").value = plaintext;  
        alert("finished");
}

function send() {       
    var key = CryptoJS.enc.Hex.parse('00000000000000000000000000000000');
    var iv = CryptoJS.enc.Hex.parse('00000000000000000000000000000000');   
    var plain = document.getElementById("sendid:plain").value;
    var encrypted = CryptoJS.AES.encrypt(plain, key, {iv:iv, mode:CryptoJS.mode.CBC});
    var cipher = encrypted.ciphertext.toString(CryptoJS.enc.Base64);
    document.getElementById("sendid:cipher").value = cipher;  
    alert("finished");

}

// http://stackoverflow.com/questions/3745666/how-to-convert-from-hex-to-ascii-in-javascript
function hex2a(hexx) {
    var hex = hexx.toString();//force conversion
    var str = '';
    for (var i = 0; i < hex.length; i += 2)
        str += String.fromCharCode(parseInt(hex.substr(i, 2), 16));
    return str;
}

function a2hex(str) {
	  var arr = [];
	  for (var i = 0, l = str.length; i < l; i ++) {
	    var hex = Number(str.charCodeAt(i)).toString(16);
	    arr.push(hex);
	  }
	  return arr.join('');
	}

//http://phpjs.org/functions/base64_encode/
function base64_encode(data) {
	  //  discuss at: http://phpjs.org/functions/base64_encode/
	  // original by: Tyler Akins (http://rumkin.com)
	  // improved by: Bayron Guevara
	  // improved by: Thunder.m
	  // improved by: Kevin van Zonneveld (http://kevin.vanzonneveld.net)
	  // improved by: Kevin van Zonneveld (http://kevin.vanzonneveld.net)
	  // improved by: Rafał Kukawski (http://kukawski.pl)
	  // bugfixed by: Pellentesque Malesuada
	  //   example 1: base64_encode('Kevin van Zonneveld');
	  //   returns 1: 'S2V2aW4gdmFuIFpvbm5ldmVsZA=='
	  //   example 2: base64_encode('a');
	  //   returns 2: 'YQ=='

	  var b64 = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=';
	  var o1, o2, o3, h1, h2, h3, h4, bits, i = 0,
	    ac = 0,
	    enc = '',
	    tmp_arr = [];

	  if (!data) {
	    return data;
	  }

	  do { // pack three octets into four hexets
	    o1 = data.charCodeAt(i++);
	    o2 = data.charCodeAt(i++);
	    o3 = data.charCodeAt(i++);

	    bits = o1 << 16 | o2 << 8 | o3;

	    h1 = bits >> 18 & 0x3f;
	    h2 = bits >> 12 & 0x3f;
	    h3 = bits >> 6 & 0x3f;
	    h4 = bits & 0x3f;

	    // use hexets to index into b64, and append result to encoded string
	    tmp_arr[ac++] = b64.charAt(h1) + b64.charAt(h2) + b64.charAt(h3) + b64.charAt(h4);
	  } while (i < data.length);

	  enc = tmp_arr.join('');

	  var r = data.length % 3;

	  return (r ? enc.slice(0, r - 3) : enc) + '==='.slice(r || 3);
	}

// http://phpjs.org/functions/base64_decode/
function base64_decode(data) {
	  //  discuss at: http://phpjs.org/functions/base64_decode/
	  // original by: Tyler Akins (http://rumkin.com)
	  // improved by: Thunder.m
	  // improved by: Kevin van Zonneveld (http://kevin.vanzonneveld.net)
	  // improved by: Kevin van Zonneveld (http://kevin.vanzonneveld.net)
	  //    input by: Aman Gupta
	  //    input by: Brett Zamir (http://brett-zamir.me)
	  // bugfixed by: Onno Marsman
	  // bugfixed by: Pellentesque Malesuada
	  // bugfixed by: Kevin van Zonneveld (http://kevin.vanzonneveld.net)
	  //   example 1: base64_decode('S2V2aW4gdmFuIFpvbm5ldmVsZA==');
	  //   returns 1: 'Kevin van Zonneveld'
	  //   example 2: base64_decode('YQ===');
	  //   returns 2: 'a'

	  var b64 = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=';
	  var o1, o2, o3, h1, h2, h3, h4, bits, i = 0,
	    ac = 0,
	    dec = '',
	    tmp_arr = [];

	  if (!data) {
	    return data;
	  }

	  data += '';

	  do { // unpack four hexets into three octets using index points in b64
	    h1 = b64.indexOf(data.charAt(i++));
	    h2 = b64.indexOf(data.charAt(i++));
	    h3 = b64.indexOf(data.charAt(i++));
	    h4 = b64.indexOf(data.charAt(i++));

	    bits = h1 << 18 | h2 << 12 | h3 << 6 | h4;

	    o1 = bits >> 16 & 0xff;
	    o2 = bits >> 8 & 0xff;
	    o3 = bits & 0xff;

	    if (h3 == 64) {
	      tmp_arr[ac++] = String.fromCharCode(o1);
	    } else if (h4 == 64) {
	      tmp_arr[ac++] = String.fromCharCode(o1, o2);
	    } else {
	      tmp_arr[ac++] = String.fromCharCode(o1, o2, o3);
	    }
	  } while (i < data.length);

	  dec = tmp_arr.join('');

	  return dec.replace(/\0+$/, '');
	}

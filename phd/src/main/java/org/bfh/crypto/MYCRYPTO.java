package org.bfh.crypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

public class MYCRYPTO {

	public MYCRYPTO(){
		
	}
	
	public static String SHA1(String s){
		Security.addProvider(new BouncyCastleProvider());
		byte[] input = s.getBytes();
		byte[] digest = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1", "BC");
			md.update(input);
			digest = md.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		}
		return new String(Hex.encode(digest));
	}
	
	public String AESencode(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	public String AESdecode(String s) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String RSAencode(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	public String RSAdecode(String s) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void generateRSAKeys(){
		// TODO Auto-generated method stub
	}

}

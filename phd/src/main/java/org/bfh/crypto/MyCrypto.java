package org.bfh.crypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

/**
 * A crypto helper class which implements some hash functions and some encryption / decryption algorithms.
 * 
 * @author leism3, koblt1
 */
public class MyCrypto {

	public MyCrypto(){
		
	}
	
	/**
	 * @param s
	 * 			the string
	 * @return
	 * 			the SHA-256 digest of the string
	 */
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
	
	/**
	 * @param s
	 * 			the string
	 * @return
	 * 			the SHA-256 digest of the string
	 */
	public static String SHA256(String s){
		Security.addProvider(new BouncyCastleProvider());
		byte[] input = s.getBytes();
		byte[] digest = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256", "BC");
			md.update(input);
			digest = md.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		}
		return new String(Hex.encode(digest));
	}
	
	/**
	 * @param s
	 * 			the string
	 * @return
	 * 			the SHA-512 digest of the string
	 */
	public static String SHA512(String s){
		Security.addProvider(new BouncyCastleProvider());
		byte[] input = s.getBytes();
		byte[] digest = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512", "BC");
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

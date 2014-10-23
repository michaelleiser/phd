/*
 * Copyright (C) 2011 www.itcsolutions.eu
 *
 * This file is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation; either version 2.1, or (at your
 * option) any later version.
 *
 * This file is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 *
 */

/**
 *
 * @author Catalin - www.itcsolutions.eu
 * @version 2011
 *
 */
package org.bfh.crypto;

/**
 *
 * @author Catalin
 */
//
//import java.io.ByteArrayInputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//
//import javax.crypto.BadPaddingException;
//import javax.crypto.IllegalBlockSizeException;
//import javax.crypto.ShortBufferException;
//
//import org.apache.commons.codec.binary.Base64;
//import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
//import org.bouncycastle.crypto.BlockCipher;
//import org.bouncycastle.crypto.DataLengthException;
//import org.bouncycastle.crypto.InvalidCipherTextException;
//import org.bouncycastle.crypto.engines.AESEngine;
//import org.bouncycastle.crypto.modes.CBCBlockCipher;
//import org.bouncycastle.crypto.paddings.PKCS7Padding;
//import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
//import org.bouncycastle.crypto.params.KeyParameter;
//import org.bouncycastle.crypto.params.ParametersWithIV;
//import org.bouncycastle.util.encoders.UrlBase64;
//
//import sun.misc.BASE64Encoder;


/**
 * Implementation of AES
 * Bouncy Castle API installed as a library
 * CBC mode for encryption and decryption
 * @author Catalin Boja
 */

public class AES {
//    PaddedBufferedBlockCipher encryptCipher = null;
//    PaddedBufferedBlockCipher decryptCipher = null;
//
//    // Buffer used to transport the bytes from one stream to another
//    byte[] buf = new byte[16];              //input buffer
//    byte[] obuf = new byte[512];            //output buffer
//    // The key
//    byte[] key = null;
//    // The initialization vector needed by the CBC mode
//    byte[] IV =  null;
//
//    // The default block size
//    public static int blockSize = 16;
//
//    public AES(){
//        //default 192 bit key
//    	key = new byte[blockSize];
//        //default IV vector with all bytes to 0
//        IV = new byte[blockSize];
//    }
//
//    public void InitCiphers(){
//        //create the ciphers
//        // AES block cipher in CBC mode with padding
//    	
//    	BlockCipher AESCipher = new AESEngine();
//    	
//        encryptCipher = new PaddedBufferedBlockCipher(
//                new CBCBlockCipher(AESCipher));
//
//        decryptCipher =  new PaddedBufferedBlockCipher(
//                new CBCBlockCipher(AESCipher));
//
//        //create the IV parameter
//        ParametersWithIV parameterIV =
//                new ParametersWithIV(new KeyParameter(key),IV);
//
//        encryptCipher.init(true, parameterIV);
//        decryptCipher.init(false, parameterIV);
//    }
//
//    public void ResetCiphers() {
//        if(encryptCipher!=null)
//            encryptCipher.reset();
//        if(decryptCipher!=null)
//            decryptCipher.reset();
//    }
//
//public String CBCEncrypt(String message)
//throws ShortBufferException, 
//        IllegalBlockSizeException,
//        BadPaddingException,
//        DataLengthException,
//        IllegalStateException,
//        InvalidCipherTextException,
//        IOException
//{
//	String enc = null;
//	InputStream in = new ByteArrayInputStream(message.getBytes());
//	OutputStream out = new ByteArrayOutputStream();
//
//    int noBytesRead = 0;        //number of bytes read from input
//    int noBytesProcessed = 0;   //number of bytes processed
//
//    while ((noBytesRead = in.read(buf)) >= 0) {
//            //System.out.println(noBytesRead +" bytes read");
//
//        noBytesProcessed =
//                encryptCipher.processBytes(buf, 0, noBytesRead, obuf, 0);
//            //System.out.println(noBytesProcessed +" bytes processed");
//        out.write(obuf, 0, noBytesProcessed);
//    }
//
//     //System.out.println(noBytesRead +" bytes read");
//     noBytesProcessed = encryptCipher.doFinal(obuf, 0);
//
//     //System.out.println(noBytesProcessed +" bytes processed");
//     out.write(obuf, 0, noBytesProcessed);
//
//    out.flush();
//
//    in.close();
//    out.close();
//
//    return enc;
//}
//    public String CBCDecrypt(String cipher)
//    throws ShortBufferException, 
//            IllegalBlockSizeException,
//            BadPaddingException,
//            DataLengthException,
//            IllegalStateException,
//            InvalidCipherTextException,
//            IOException
//    {
//        // Bytes read from in will be decrypted
//        // Read in the decrypted bytes from in InputStream and and
//        //      write them in cleartext to out OutputStream
//
//        // get the IV from the file
//        // DO NOT FORGET TO reinit the cipher with the IV
//        //in.read(IV,0,IV.length);
//        //this.InitCiphers();
//
//    	String dec = null;
//    	InputStream in = new ByteArrayInputStream(cipher.getBytes());
//    	OutputStream out = new ByteArrayOutputStream();
//    	
//        int noBytesRead = 0;        //number of bytes read from input
//        int noBytesProcessed = 0;   //number of bytes processed
//
//        while ((noBytesRead = in.read(buf)) >= 0) {
//                //System.out.println(noBytesRead +" bytes read");
//                noBytesProcessed =
//                        decryptCipher.processBytes(buf, 0, noBytesRead, obuf, 0);
//                //System.out.println(noBytesProcessed +" bytes processed");
//                out.write(obuf, 0, noBytesProcessed);
//        }
//        //System.out.println(noBytesRead +" bytes read");
//        noBytesProcessed = decryptCipher.doFinal(obuf, 0);
//        //System.out.println(noBytesProcessed +" bytes processed");
//        out.write(obuf, 0, noBytesProcessed);
//
//        out.flush();
//
//        in.close();
//        out.close();
//;
//        return dec;
//    }
//    
//    
//    
//    public static void main(String[] args) throws FileNotFoundException, IOException {
//        try {
//
//            AES bc = new AES();
//            bc.InitCiphers();
// 
//            String message = "This is encrypted session key ;)";
//            String encrypted = "X5+TCfyhqAdmOWL6gIejzsrLTgTOJq7Sg1QVNFhrOOkttqPe5utYqKk6jrhpME4h";
//            //encryption
//            String enc = bc.CBCEncrypt(message);
//            System.out.println(">" + enc);
//            //decryption
//            String dec = bc.CBCDecrypt(enc);
//            System.out.println(">" + dec);
// 
//        } catch (ShortBufferException ex) {
//        	ex.printStackTrace();
//        } catch (IllegalBlockSizeException ex) {
//        	ex.printStackTrace();
//        } catch (BadPaddingException ex) {
//        	ex.printStackTrace();
//        } catch (DataLengthException ex) {
//        	ex.printStackTrace();
//        } catch (IllegalStateException ex) {
//        	ex.printStackTrace();
//        } catch (Exception ex) {
//        	ex.printStackTrace();
//        }
// 
//        System.out.println("Test done !");
//        
//    }
}

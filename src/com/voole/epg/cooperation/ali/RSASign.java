package com.voole.epg.cooperation.ali;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;

import android.util.Base64;


public class RSASign {
	
	public static final String SIGN_ALGORITHMS = "SHA1WithRSA";


	/// private RSASignature signature = new RSASignature();
	
	public static String sign(String content, String privateKey, String charset )
	{
		try 
        {
        	PKCS8EncodedKeySpec priPKCS8 	= new PKCS8EncodedKeySpec( Base64.decode(privateKey,Base64.DEFAULT ) ); 
        	KeyFactory keyf 				= KeyFactory.getInstance("RSA");
        	PrivateKey priKey 				= keyf.generatePrivate(priPKCS8);

            java.security.Signature signature = java.security.Signature
                .getInstance(SIGN_ALGORITHMS);

            signature.initSign(priKey);
            signature.update( content.getBytes(charset) );

            byte[] signed = signature.sign();
            
            return Base64.encodeToString (signed,Base64.DEFAULT);           
         
        }
        catch (Exception e) 
        {
        	e.printStackTrace();
        }
        
        return null;
		

	}

}


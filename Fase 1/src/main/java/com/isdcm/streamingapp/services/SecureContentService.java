package com.isdcm.streamingapp.services;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.KeyGenerator;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class SecureContentService {

    private static Key getSecretKey() throws NoSuchAlgorithmException {

        KeyGenerator keyGen=KeyGenerator.getInstance("AES");
        return keyGen.generateKey();
    }

    private static void encryptDecryptFile(String pathEntrada, String pathSortida, Key secretKey, Integer encryptMode)
            throws Exception {

        Cipher cipher=Cipher.getInstance("AES");

        cipher.init(encryptMode, secretKey);

        CipherInputStream cipt=new CipherInputStream(new FileInputStream(new File(pathEntrada)), cipher);
        FileOutputStream fip=new FileOutputStream(new File(pathSortida));

        int i;
        while((i=cipt.read())!=-1) {
            fip.write(i);
        }
    }

    public static void testEncriptionDeciption(){

        String imageFile = "C:\\Users\\Public\\image.jpg";
        String encryptedImage = "C:\\Users\\Public/imageEncrypted.jpg";
        String decryptedImage = "C:\\Users\\Public/imageDecrypted.jpg";

        try {

            System.out.println("Testing content encryption and decryption");

            Key key = getSecretKey();

            encryptDecryptFile(imageFile,encryptedImage,key, Cipher.ENCRYPT_MODE);
            encryptDecryptFile(encryptedImage,decryptedImage,key, Cipher.DECRYPT_MODE);

        } catch (Exception e){
            System.out.println("error " + e);
        }
    }

}

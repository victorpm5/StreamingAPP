package com.isdcm.streamingapp.services;


import org.apache.xml.security.encryption.XMLCipher;
import org.apache.xml.security.utils.EncryptionConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

public class SecureXMLService {


    private static SecretKey getSecretKey() throws NoSuchAlgorithmException {

        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        return keyGenerator.generateKey();
    }

    public static Document encryptXML(Document document, SecretKey secretKey) throws Exception {

        Element rootElement = document.getDocumentElement();

        XMLCipher xmlCipher = XMLCipher.getInstance(XMLCipher.AES_128);
        xmlCipher.init(XMLCipher.ENCRYPT_MODE, secretKey);

        xmlCipher.doFinal(document, rootElement, true);

        return document;
    }

    public static Document decriptXML(Document document, SecretKey secretKey) throws Exception {

        Element encryptedDataElement = (Element) document
                .getElementsByTagNameNS(EncryptionConstants.EncryptionSpecNS, EncryptionConstants._TAG_ENCRYPTEDDATA).item(0);

        XMLCipher xmlCipher = XMLCipher.getInstance();

        xmlCipher.init(XMLCipher.DECRYPT_MODE, secretKey);
        xmlCipher.doFinal(document, encryptedDataElement);

        return document;
    }

    public static void testEncriptionDeciption(){

        String xmlFile = "C:\\Users\\Public\\test.xml";
        String encryptedFile = "C:\\Users\\Public/encrypted.xml";
        String decryptedFile = "C:\\Users\\Public/decrypted.xml";

        try {

            org.apache.xml.security.Init.init();
            SecretKey secretKey = getSecretKey();

            Document document = DocumentService.getDocument(xmlFile);
            Document encryptedDoc = SecureXMLService.encryptXML(document, secretKey);
            DocumentService.saveDocumentTo(encryptedDoc, encryptedFile);

            Document decryptedDoc = SecureXMLService.decriptXML(encryptedDoc, secretKey);
            DocumentService.saveDocumentTo(decryptedDoc,decryptedFile);

        } catch (Exception e){
            System.out.println("error " + e);
        }
    }
}

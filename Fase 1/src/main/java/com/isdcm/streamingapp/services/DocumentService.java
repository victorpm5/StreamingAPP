package com.isdcm.streamingapp.services;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;

public class DocumentService {

    public static Document getDocument(String xmlFile) throws Exception {

        DocumentBuilderFactory builder = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = builder.newDocumentBuilder();

        return docBuilder.parse(xmlFile);
    }

    public static void saveDocumentTo(Document document, String fileName) throws Exception {

        File encryptionFile = new File(fileName);
        FileOutputStream fOutStream = new FileOutputStream(encryptionFile);

        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(fOutStream);
        transformer.transform(source, result);

        fOutStream.close();
    }

}

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.wso2.balana.Balana;
import org.wso2.balana.Indenter;
import org.wso2.balana.PDP;
import org.wso2.balana.PDPConfig;
import org.wso2.balana.ctx.ResponseCtx;
import org.wso2.balana.ctx.xacml3.Result;
import org.wso2.balana.finder.impl.FileBasedPolicyFinderModule;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.Iterator;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introdueix un nombre del 1 al 3 per seleccionar una Policy");
        int policyID = scanner.nextInt();
        System.out.println("Introdueix un nombre del 1 al 5 per seleccionar una Request");
        int requestID = scanner.nextInt();

        String policyPath = "C:\\projects\\Universitat\\isdcm\\StreamingAPP\\balanaAuth\\src\\resource\\policy\\XACMLPolicy" + policyID + ".xml";
        System.setProperty(FileBasedPolicyFinderModule.POLICY_DIR_PROPERTY, policyPath);
        Balana balana = Balana.getInstance();

        PDPConfig pdpConfig = balana.getPdpConfig();
        PDP pdp = new PDP(new PDPConfig(pdpConfig.getAttributeFinder(), pdpConfig.getPolicyFinder(), null, true));

        String requestFile = "C:\\projects\\Universitat\\isdcm\\StreamingAPP\\balanaAuth\\src\\resource\\requests\\XACMLRequest" + requestID + ".xml";
        String response = pdp.evaluate(getXMLFromFilePath(requestFile));

        System.out.println("Resultat:");
        System.out.println(response);
    }

    private static String getXMLFromFilePath(String path) throws Exception {
        DocumentBuilderFactory builder = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = builder.newDocumentBuilder();
        Document document = docBuilder.parse(path);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        StringWriter stringWriter = new StringWriter();
        transformer.transform(new DOMSource(document), new StreamResult(stringWriter));

        return stringWriter.getBuffer().toString();
    }
}
package no1.springboot.adityo;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

@Endpoint
public class SampleServiceEndpoint {
    private static final String NAMESPACE_URI = "http://www.oracle.com/external/services/sampleservice/request/v1.0";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "sampleservicerq")
    @ResponsePayload
    public Element handleSampleServiceRequest(@RequestPayload Element request) {
        // Extract the necessary data from the request
        String serviceId = getTextContent(request, "service_id");
        String orderType = getTextContent(request, "order_type");
        String type = getTextContent(request, "type");
        String trxId = getTextContent(request, "trx_id");

        // Process the request and generate the response
        String errorCode = "0000";
        String errorMsg = "Success";

        // Create the response envelope
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document responseDocument = builder.newDocument();
            Element rootElement = responseDocument.createElementNS("http://schemas.xmlsoap.org/soap/envelope/", "soapenv:Envelope");
            responseDocument.appendChild(rootElement);

            Element bodyElement = responseDocument.createElement("soapenv:Body");
            rootElement.appendChild(bodyElement);

            Element responseElement = responseDocument.createElementNS("http://www.oracle.com/external/services/sampleservice/response/v1.0", "tns:sampleservicers");
            bodyElement.appendChild(responseElement);

            Element errorCodeElement = responseDocument.createElement("tns:error_code");
            errorCodeElement.setTextContent(errorCode);
            responseElement.appendChild(errorCodeElement);

            Element errorMsgElement = responseDocument.createElement("tns:error_msg");
            errorMsgElement.setTextContent(errorMsg);
            responseElement.appendChild(errorMsgElement);

            Element trxIdElement = responseDocument.createElement("tns:trx_id");
            trxIdElement.setTextContent(trxId);
            responseElement.appendChild(trxIdElement);

            return responseDocument.getDocumentElement();
        } catch (ParserConfigurationException e) {
            // Handle the exception
        }
        return null;
    }
    private String generateSampleResponse() {
        String responseXml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">";
        responseXml += "<soap:Header/>";
        responseXml += "<soapenv:Body>";
        responseXml += "<tns:sampleservicers xmlns:tns=\"http://www.oracle.com/external/services/sampleservice/response/v1.0 xmlns:xsi=http://www.w3.org/2001/XMLSchema-instance\">";
        responseXml += "<tns:error_code>0000</tns:error_code>";
        responseXml += "<tns:error_msg>Success</tns:error_msg>";
        responseXml += "<tns:trx_id>c6714ec0-b379-11e9-889b-7f7167f4f72d</tns:trx_id>";
        responseXml += "</tns:sampleservicers>";
        responseXml += "</soapenv:Body>";
        responseXml += "</soapenv:Envelope>";
        return responseXml;
    }

    private String getTextContent(Element parentElement, String childElementName) {
        NodeList nodeList = parentElement.getElementsByTagNameNS(NAMESPACE_URI, childElementName);
        if (((NodeList) nodeList).getLength() > 0) {
            Node childNode = nodeList.item(0);
            return childNode.getTextContent();
        }
        return null;
    }
}

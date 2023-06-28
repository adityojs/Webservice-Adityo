no1springbootadityo = belum dilakukan Bug
no1springbootadityoafter = setelah dilakukan Bug

# Webservice-Adityo
http://localhost:8080/external/services/ws/sample-service

server.address=localhost
server.port=8080

# mysql
spring.datasource.url=jdbc:mysql://localhost:3306/SampleService
spring.datasource.username=root
spring.datasource.password=root

spring.ws.servlet.mapping-url-pattern=/external/services/ws/sample-service

Request :

<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/">
    <soap:Header xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
        <orac:authenticationheader xmlns:orac="http://www.oracle.com">
            <orac:username>admin</orac:username>
            <orac:password>admin</orac:password>
        </orac:authenticationheader>
    </soap:Header>
    <soapenv:Body>
        <v1:sampleservicerq xmlns:v1="http://www.oracle.com/external/services/sampleservice/request/v1.0">
            <v1:service_id>1234567890</v1:service_id>
            <v1:order_type>CH</v1:order_type>
            <v1:type>PO</v1:type>
            <v1:trx_id>c6714ec0-b379-11e9-889b-7f7167f4f72d</v1:trx_id>
        </v1:sampleservicerq>
    </soapenv:Body>
</soapenv:Envelope>

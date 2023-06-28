package no1.springboot.adityoafter;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;

import org.springframework.context.annotation.Bean;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.server.endpoint.adapter.method.MethodArgumentResolver;
import org.springframework.ws.server.endpoint.adapter.method.MethodReturnValueHandler;
import org.springframework.ws.soap.server.endpoint.interceptor.SoapEnvelopeLoggingInterceptor;
import org.springframework.ws.soap.server.endpoint.mapping.SoapActionAnnotationMethodEndpointMapping;
import org.springframework.ws.soap.server.endpoint.mapping.SoapActionEndpointMapping;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.transport.http.WsdlDefinitionHandlerAdapter;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;
import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.Wsdl11Definition;


import java.util.Collections;
import java.util.List;

@Configuration
@EnableWs
public class WebServiceConfig extends WsConfigurerAdapter {
    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/external/services/ws/*");
    }

    @Bean(name = "sample-service")
    public Wsdl11Definition defaultWsdl11Definition() {
        SimpleWsdl11Definition wsdl11Definition = new SimpleWsdl11Definition();
        wsdl11Definition.setWsdl(new ClassPathResource("sample-service.wsdl"));
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema sampleServiceSchema() {
        return new SimpleXsdSchema(new ClassPathResource("sample-service.xsd"));
    }

    @Bean
    public WsdlDefinitionHandlerAdapter wsdlDefinitionHandlerAdapter() {
        return new WsdlDefinitionHandlerAdapter();
    }

    @Bean
    public SoapActionAnnotationMethodEndpointMapping soapActionAnnotationMethodEndpointMapping() {
        return new SoapActionAnnotationMethodEndpointMapping();
    }

    @Bean
    public SoapVersionAnnotationMethodEndpointMapping soapVersionAnnotationMethodEndpointMapping() {
        return new SoapVersionAnnotationMethodEndpointMapping();
    }

    @Bean
    public SoapUriEndpointMapping soapUriEndpointMapping() {
        return new SoapUriEndpointMapping();
    }

    @Bean
    public SoapMethodEndpointMapping soapMethodEndpointMapping() {
        return new SoapMethodEndpointMapping();
    }

    @Bean
    public SoapActionEndpointMapping soapActionEndpointMapping() {
        return new SoapActionEndpointMapping();
    }

    @Bean
    public SoapMethodArgumentResolver soapMethodArgumentResolver() {
        return new SoapMethodArgumentResolver();
    }

    @Bean
    public SoapMethodReturnValueHandler soapMethodReturnValueHandler() {
        return new SoapMethodReturnValueHandler();
    }

    @Bean
    public SoapEnvelopeLoggingInterceptor soapEnvelopeLoggingInterceptor() {
        return new SoapEnvelopeLoggingInterceptor();
    }

    @Bean
    public SoapHeaderInterceptor soapHeaderInterceptor() {
        return new SoapHeaderInterceptor();
    }

    @Bean
    public PayloadLoggingInterceptor payloadLoggingInterceptor() {
        return new PayloadLoggingInterceptor();
    }

    @Bean
    public SoapFaultMappingExceptionResolver soapFaultMappingExceptionResolver() {
        SoapFaultMappingExceptionResolver exceptionResolver = new SoapFaultMappingExceptionResolver();
        exceptionResolver.setDetailElementResolvers(Collections.singletonList(detailElementResolver()));
        return exceptionResolver;
    }

    @Bean
    public DetailElementResolver detailElementResolver() {
        return new SoapHeaderElementResolver();
    }

    @Override
    public void addInterceptors(List<EndpointInterceptor> interceptors) {
        interceptors.add(soapEnvelopeLoggingInterceptor());
        interceptors.add( soapHeaderInterceptor());
        interceptors.add( payloadLoggingInterceptor());
    }

    @Override
    public void addArgumentResolvers(List<MethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add( soapMethodArgumentResolver());
    }

    @Override
    public void addReturnValueHandlers(List<MethodReturnValueHandler> returnValueHandlers) {
        returnValueHandlers.add( soapMethodReturnValueHandler());
    }
}

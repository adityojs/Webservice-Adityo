package no1.springboot.adityo;

import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.EndpointInterceptor;

public class SoapHeaderInterceptor implements EndpointInterceptor {
    @Override
    public boolean handleRequest(MessageContext messageContext, Object o) throws Exception {
        return false;
    }

    @Override
    public boolean handleResponse(MessageContext messageContext, Object o) throws Exception {
        return false;
    }

    @Override
    public boolean handleFault(MessageContext messageContext, Object o) throws Exception {
        return false;
    }

    @Override
    public void afterCompletion(MessageContext messageContext, Object o, Exception e) throws Exception {

    }
}

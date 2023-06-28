package no1.springboot.adityoafter;

import org.springframework.core.MethodParameter;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.endpoint.adapter.method.MethodReturnValueHandler;

public class SoapMethodReturnValueHandler implements MethodReturnValueHandler {
    @Override
    public boolean supportsReturnType(MethodParameter methodParameter) {
        return false;
    }

    @Override
    public void handleReturnValue(MessageContext messageContext, MethodParameter methodParameter, Object o) throws Exception {

    }
}

package springboot.adityono1.no1springbootadityo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Controller
@RequestMapping("/external/services/ws/sample-service")
public class SampleServiceEndpoint {
    @PostMapping
    @ResponsePayload
    public SampleServiceRs processRequest(@RequestPayload SampleServiceRq request) {
        // Process the request and create a response
        SampleServiceRs response = new SampleServiceRs();
        response.setErrorCode("0000");
        response.setErrorMsg("Success");
        response.setTrxId(request.getTrxId());

        return response;
    }
}

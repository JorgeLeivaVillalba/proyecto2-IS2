package com.example.proyecto1;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ConsumerRoute extends RouteBuilder {

    @Override
    public void configure() {
        from("activemq:queue:Leiva-ITAU-IN")
            .log("ITAU proceso la transferencia: ${body}");

        from("activemq:queue:Leiva-ATLAS-IN")
            .log("ATLAS proceso la transferencia: ${body}");

        from("activemq:queue:Leiva-FAMILIAR-IN")
            .log("FAMILIAR proceso la transferencia: ${body}");
    }
}
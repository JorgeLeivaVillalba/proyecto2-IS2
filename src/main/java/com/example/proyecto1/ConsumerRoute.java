package com.example.proyecto1;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ConsumerRoute extends RouteBuilder {

    @Override
    public void configure() {
        from("activemq:queue:Leiva-ITAU-IN")
            .bean(ITAUConsumer.class, "procesar");

        from("activemq:queue:Leiva-ATLAS-IN")
            .bean(ATLASConsumer.class, "procesar");

        from("activemq:queue:Leiva-FAMILIAR-IN")
            .bean(FAMILIARConsumer.class, "procesar");
    }
}
package com.example.proyecto1;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//Ruta del consumidor

@Component
public class ConsumerRoute extends RouteBuilder {

    @Override
    public void configure() {
        from("activemq:queue:Leiva-ITAU-IN")
            .multicast() // Multicast para enviar al bean MessageStoreBean
            .to("bean:ITAUConsumer?method=procesar", "bean:messageStoreBean?method=store");

        from("activemq:queue:Leiva-ATLAS-IN")
            .multicast()
            .to("bean:ATLASConsumer?method=procesar", "bean:messageStoreBean?method=store");

        from("activemq:queue:Leiva-FAMILIAR-IN")
            .multicast()
            .to("bean:FAMILIARConsumer?method=procesar", "bean:messageStoreBean?method=store");
    }
}
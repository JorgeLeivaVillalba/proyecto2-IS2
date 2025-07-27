package com.example.proyecto1;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class TransferRoute extends RouteBuilder {

    private final TransferenciaGenerator generator = new TransferenciaGenerator();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure() {
        from("timer:transferTimer?period=3000")
            .process(exchange -> {
                Transferencia transferencia = generator.generar();
                if (transferencia.getMonto() < 20000000) {
                    String json = objectMapper.writeValueAsString(transferencia);
                    exchange.getIn().setBody(json);
                    exchange.getIn().setHeader("bancoDestino", transferencia.getBanco_destino());
                    exchange.getIn().setHeader("permitido", true);
                } else {
                    // Rechazo: monto >= 20.000.000
                    String errorJson = String.format("{\"id_transaccion\": \"%s\", \"mensaje\": \"El monto supera el máximo permitido\"}", transferencia.getIdTransaccion());
                    exchange.getIn().setBody(errorJson);
                    exchange.getIn().setHeader("permitido", false);
                }
            })
            .choice()
                .when(header("permitido").isEqualTo(true))
                    .toD("activemq:queue:Leiva-${header.bancoDestino}-IN")
                    .log("\n========================================\n"
                        + "TRANSFERENCIA ENVIADA\n"
                        + "Cola destino: Leiva-${header.bancoDestino}-IN\n"
                        + "Datos: ${body}\n"
                        + "========================================\n")
                .otherwise()
                    .log("\n========================================\n"
                        + "TRANSFERENCIA RECHAZADA\n"
                        + "Datos: ${body}\n"
                        + "========================================\n");
    }
}
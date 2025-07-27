package com.example.proyecto1;

import org.springframework.stereotype.Component;

@Component
public class ATLASConsumer {
    public void procesar(String body) {
        try {
            com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
            java.util.Map<?,?> map = mapper.readValue(body, java.util.Map.class);
            String fechaTransferencia = (String) map.get("timestamp");
            String idTransaccion = (String) map.get("idTransaccion");
            String fechaActual = java.time.LocalDate.now().toString();
            String fechaTransferenciaSoloFecha = fechaTransferencia != null ? fechaTransferencia.split(" ")[0] : "";
            String mensaje;
            if (fechaActual.equals(fechaTransferenciaSoloFecha)) {
                mensaje = "Transferencia procesada exitosamente";
            } else {
                mensaje = "Mensaje caducado";
            }
            String resultado = String.format("{\"id_transaccion\": \"%s\", \"mensaje\": \"%s\"}", idTransaccion, mensaje);
            System.out.println("ATLAS resultado validación: " + resultado);
        } catch (Exception e) {
            System.out.println("ATLAS error al procesar: " + e.getMessage());
        }
    }
}

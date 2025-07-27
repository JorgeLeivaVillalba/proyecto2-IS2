package com.example.proyecto1;

import java.util.Random;

public class TransferenciaGenerator {
    private final String[] bancos = {"ITAU", "ATLAS", "FAMILIAR"};
    private final Random random = new Random();

    public Transferencia generar() {
        String bancoOrigen = bancos[random.nextInt(bancos.length)];
        String bancoDestino;
        do {
            bancoDestino = bancos[random.nextInt(bancos.length)];
        } while (bancoDestino.equals(bancoOrigen));

        Transferencia transferencia = new Transferencia();
        transferencia.setCuenta(String.valueOf(1000 + random.nextInt(4001)));
        // 20% de probabilidad de monto > 20 millones
        if (random.nextDouble() < 0.2) {
            // monto entre 20,000,001 y 25,000,000
            transferencia.setMonto(20000001 + random.nextInt(5000000));
        } else {
            // monto entre 1,000 y 4,999,999
            transferencia.setMonto(1000 + random.nextInt(4998999));
        }
        transferencia.setBanco_origen(bancoOrigen);
        transferencia.setBanco_destino(bancoDestino);
        // Formatear timestamp como yyyy-MM-dd HH:mm:ss
        java.time.LocalDateTime now = java.time.LocalDateTime.now();
        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        transferencia.setTimestamp(now.format(formatter)); // timestamp legible
        transferencia.setIdTransaccion(java.util.UUID.randomUUID().toString()); // ID de transacción

        return transferencia;
    }
}
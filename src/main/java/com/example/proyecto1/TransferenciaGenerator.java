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
        transferencia.setMonto(1000 + random.nextInt(4001));
        transferencia.setBanco_origen(bancoOrigen);
        transferencia.setBanco_destino(bancoDestino);
        transferencia.setTimestamp(System.currentTimeMillis()); //timestamp
        transferencia.setIdTransaccion(java.util.UUID.randomUUID().toString()); // ID de transacción

        return transferencia;
    }
}
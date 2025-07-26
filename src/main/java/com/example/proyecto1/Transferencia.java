package com.example.proyecto1;

public class Transferencia {
    private String cuenta;
    private int monto;
    private String banco_origen;
    private String banco_destino;
    private long timestamp;
    private String idTransaccion;

    public String getCuenta() {
        return cuenta;
    }
    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }
    public int getMonto() {
        return monto;
    }
    public void setMonto(int monto) {
        this.monto = monto;
    }
    public String getBanco_origen() {
        return banco_origen;
    }
    public void setBanco_origen(String banco_origen) {
        this.banco_origen = banco_origen;
    }
    public String getBanco_destino() {
        return banco_destino;
    }
    public void setBanco_destino(String banco_destino) {
        this.banco_destino = banco_destino;
    }
    public long getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
    public String getIdTransaccion() {
        return idTransaccion;
    }
    public void setIdTransaccion(String idTransaccion) {
        this.idTransaccion = idTransaccion;
    }
}
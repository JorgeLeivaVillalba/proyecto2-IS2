# Proyecto 2

**Forma de entrega:** Subir al Canvas el link al repositorio GitHub

## Objetivo

Mejorar sistema desarrollado en la Tarea 1 aplicando patrones de integración.

## Requisitos

### Ajuste del JSON enviado

El JSON debe incluir la fecha de transacción y un id de transacción:

```json
{
  "cuenta": "123456",
  "monto": 500000,
  "banco_origen": "ATLAS",
  "banco_destino": "ITAU",
  "fecha": "01/01/2022",
  "id_transaccion": 123215
}
```

### Validación de monto

El pipeline debe controlar que los montos de transferencia sean menores a **20.000.000** para poder encolar al broker ActiveMQ.  
En caso de ser mayor o igual a **20.000.000**, el pipeline debe rechazar la transacción con el siguiente mensaje (además del número de transacción):

```json
{
  "id_transaccion": 123,
  "mensaje": "El monto supera máximo permitido"
}
```

---

## Cambios en Bean consumidores

A diferencia de la tarea 1 que pedía imprimir los mensajes de transferencia, los bean consumidores de la petición de transferencia deberán leer el mensaje de transferencia, validarlo e imprimir el resultado de la validación.

**La validación a realizar:**  
La fecha de transferencia debe ser la misma que la fecha actual, caso contrario rechazar la transacción.

### Ejemplo de validación exitosa

```json
{
  "id_transaccion": 123,
  "mensaje": "Transferencia procesada exitosamente"
}
```

### Ejemplo de validación no exitosa

```json
{
  "id_transaccion": 123,
  "mensaje": "Mensaje caducado"
}
```

---

## Aplicar 2 patrones EIP a elección

| Patrón EIP             | Aplicación en Proyecto 2                                                                 |
|------------------------|------------------------------------------------------------------------------------------|
| Dead Letter Channel    | Capturar errores de procesamiento en Beans y redirigir mensajes fallidos a una cola de errores. |
| Multicast              | Enviar el mensaje simultáneamente a la cola del banco, a un log de auditoría y a una cola de respaldo. |
| Splitter               | Dividir un lote de transferencias (por ejemplo, un arreglo JSON) en mensajes individuales. |
| Idempotent Receiver    | Evitar procesar múltiples veces una transacción con el mismo id_transaccion.              |
| Wire Tap               | Clonar el mensaje para enviarlo a un log o auditoría sin afectar la ruta principal.       |
| Message Store          | Almacenar los mensajes procesados para trazabilidad, auditoría o reenvío futuro.          |

---
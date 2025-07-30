package com.example.proyecto1;

import org.springframework.stereotype.Component;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

//Este bean es un MessageStore que guarda los mensajes en un archivo log.txt

@Component
public class MessageStoreBean {
    private static final String LOG_FILE = "log.txt";

    public void store(String body) {
        try (FileWriter fw = new FileWriter(LOG_FILE, true); PrintWriter pw = new PrintWriter(fw)) {
            pw.println(body);
        } catch (IOException e) {
            System.err.println("Error al guardar mensaje en log.txt: " + e.getMessage());
        }
    }
}

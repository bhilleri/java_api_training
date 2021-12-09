package fr.lernejo.navy_battle.services.service;

import com.sun.net.httpserver.HttpExchange;
import fr.lernejo.navy_battle.services.IService;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Web-service permettant de répondre Hello à un client
 */
public final class ServicePing implements IService {
    public final String returnValue = "OK";
    @Override
    public void handler(final HttpExchange exchange) throws IOException {
        final String body = getBody();
        exchange.sendResponseHeaders(200, body.length());
        try (OutputStream os = exchange.getResponseBody()) { // (1)
            os.write(body.getBytes());
        }
    }
    public String getBody(){
        return returnValue;
    }
}

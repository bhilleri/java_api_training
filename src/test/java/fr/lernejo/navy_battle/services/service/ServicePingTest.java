package fr.lernejo.navy_battle.services.service;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import fr.lernejo.navy_battle.services.json_properties.StartJsonProperty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.stubbing.Answer;

import java.io.*;
import java.net.URI;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ServicePingTest {
    @Mock
    HttpExchange exchange = mock(HttpExchange.class);
    @Test
    void handlerWork() {
        try {
            ServicePing servicePing = new ServicePing();
            String body = servicePing.getBody();
            doNothing().when(exchange).sendResponseHeaders(200, body.length());
            Answer<OutputStream> outputAnswer = invocation -> {
                return new ByteArrayOutputStream(body.length());
            };
            when(exchange.getResponseBody()).thenAnswer(outputAnswer);
            servicePing.handler(exchange);
        }catch (IOException e)
        {
            Assertions.fail(e.toString());
        }
    }
    @Test
    public void getBodyTest()
    {
        ServicePing servicePing = new ServicePing();
        String body = servicePing.getBody();
        Assertions.assertEquals(servicePing.returnValue, body);
    }
}

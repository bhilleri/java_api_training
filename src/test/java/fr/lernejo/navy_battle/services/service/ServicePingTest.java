package fr.lernejo.navy_battle.services.service;

import com.sun.net.httpserver.HttpExchange;
import org.junit.jupiter.api.Assertions;

import java.net.URI;
import java.net.http.HttpRequest;

import static org.junit.jupiter.api.Assertions.*;

class ServicePingTest {
    @org.junit.jupiter.api.Test
    public void getBodyTest()
    {
        ServicePing servicePing = new ServicePing();
        String body = servicePing.getBody();
        Assertions.assertEquals(servicePing.returnValue, body);
    }
}

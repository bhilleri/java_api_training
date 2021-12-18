package fr.lernejo.navy_battle.clients.client;

import com.google.gson.Gson;
import fr.lernejo.navy_battle.Controller;
import fr.lernejo.navy_battle.services.json_properties.StartJsonProperty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.net.http.HttpRequest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ClientStartTest {
    @Mock
    private Controller controller = mock(Controller.class);

    @Test
    public void TestConnect()
    {
        ClientStart clientStart = new ClientStart(controller);
    }

    @Test
    public void RequestBuildTest(){
        String address = "http://localhost:3000";
        String body = "helloWorld";
        ClientStart clientStart = new ClientStart(controller);
        HttpRequest request = clientStart.RequestBuilder(address, body);
        Assertions.assertEquals(address + "/api/game/start", request.uri().toString());
        Assertions.assertEquals("application/json", request.headers().firstValue("Accept").get());
        Assertions.assertEquals("application/json", request.headers().firstValue("Content-Type").get());
    }

    @Test
    public void BodyBuilder(){
        try {
            int port = 25000;
            ClientStart clientStart = new ClientStart(controller);
            String body = clientStart.BodyBuilder(port);
            Gson gson =  new Gson();
            StartJsonProperty result = gson.fromJson(body, StartJsonProperty.class);
            Assertions.assertEquals("1", result.id);
            Assertions.assertEquals("http://" + InetAddress.getLocalHost().getHostAddress()+ ":" + port, result.url);
            Assertions.assertEquals("hello", result.message);
        } catch (UnknownHostException e) {
            Assertions.fail(e);
        }
    }
}

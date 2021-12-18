package fr.lernejo.navy_battle.services.service;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import fr.lernejo.navy_battle.Controller;
import fr.lernejo.navy_battle.clients.ClientManager;
import fr.lernejo.navy_battle.services.json_properties.StartJsonProperty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.DoNotMock;
import org.mockito.Mock;
import org.mockito.internal.matchers.Null;
import org.mockito.internal.stubbing.answers.DoesNothing;
import org.mockito.stubbing.Answer;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ServiceStartTest {
    private String port = "2530";
    @Mock
    private Controller controller = mock(Controller.class);
    @Mock
    private HttpExchange httpExchange = mock(HttpExchange.class);
    @Mock
    private ClientManager clientManager = mock(ClientManager.class);
    private int TestedPort;
    @BeforeEach
    public void Initialize(){
        TestedPort = 0;
    }
    @Test
    void handlerWork() {
        try {
            String id ="2";
            String uri = "http://localhost:9856";
            String message = "Hello";
            Answer <InputStream> inputAnswer = invocation->{
                Gson gson = new Gson();
                StartJsonProperty startJson = new StartJsonProperty(id, uri, message);
                return new ByteArrayInputStream(gson.toJson(startJson).getBytes(StandardCharsets.UTF_8));
            };
            InetSocketAddress inetSocketAddress =new InetSocketAddress(Integer.valueOf(port));
            when(httpExchange.getLocalAddress()).thenReturn(inetSocketAddress);
            when(controller.getIClientManager()).thenReturn(clientManager);
            doNothing().when(clientManager).setAddress(uri);
            when(httpExchange.getRequestBody()).thenAnswer(inputAnswer);
            when(httpExchange.getRequestMethod()).thenReturn("POST");
            ServiceStart serviceStart = new ServiceStart(controller);
            String body = serviceStart.getBody(port);
            doNothing().when(httpExchange).sendResponseHeaders(202, body.length());
            Answer <OutputStream> outputAnswer = invocation->{
                return new ByteArrayOutputStream(body.length());
            };
            when(httpExchange.getResponseBody()).thenAnswer(outputAnswer);
            serviceStart.handler(httpExchange);
        }catch (IOException e)
        {
            Assertions.fail("Impossible de générer le body");
        }
    }

    @Test
    void handlerFail() {
        try {
            String id ="2";
            String uri = "http://localhost:9856";
            String message = "Hello";
            Answer <InputStream> inputAnswer = invocation->{
                Gson gson = new Gson();
                StartJsonProperty startJson = new StartJsonProperty(id, uri, message);
                return new ByteArrayInputStream(gson.toJson(startJson).getBytes(StandardCharsets.UTF_8));
            };
            InetSocketAddress inetSocketAddress =new InetSocketAddress(Integer.valueOf(port));
            when(httpExchange.getLocalAddress()).thenReturn(inetSocketAddress);
            when(controller.getIClientManager()).thenReturn(clientManager);
            doNothing().when(clientManager).setAddress(uri);
            when(httpExchange.getRequestBody()).thenThrow(com.google.gson.JsonSyntaxException.class);
            ServiceStart serviceStart = new ServiceStart(controller);
            String body = serviceStart.getBody(port);
            doNothing().when(httpExchange).sendResponseHeaders(400, body.length());
            Answer <OutputStream> outputAnswer = invocation->{
                return new ByteArrayOutputStream(body.length());
            };
            when(httpExchange.getResponseBody()).thenAnswer(outputAnswer);
            serviceStart.handler(httpExchange);
        }catch (IOException e)
        {
            Assertions.fail("Impossible de générer le body");
        }
    }

    @Test
    void getBody() throws UnknownHostException {
        ServiceStart serviceStart = new ServiceStart(controller);
        String resultJson = serviceStart.getBody(port);
        Gson gson = new Gson();
        StartJsonProperty resultObject = gson.fromJson(resultJson, StartJsonProperty.class);
        Assertions.assertEquals("2", resultObject.id);
        Assertions.assertEquals("http://" + InetAddress.getLocalHost().getHostAddress() + ":" + port, resultObject.url);
        Assertions.assertEquals("Bonjour", resultObject.message);
    }

    @Test
    void testRequestTrue() {
        String id ="2";
        String uri = "http://localhost:9856";
        String message = "Hello";
        final String addressInController;
        Answer <InputStream> inputAnswer = invocation->{
          Gson gson = new Gson();
          StartJsonProperty startJson = new StartJsonProperty(id, uri, message);
          return new ByteArrayInputStream(gson.toJson(startJson).getBytes(StandardCharsets.UTF_8));
        };
        when(controller.getIClientManager()).thenReturn(clientManager);
        doNothing().when(clientManager).setAddress(uri);
        ServiceStart serviceStart = new ServiceStart(controller);
        when(httpExchange.getRequestBody()).thenAnswer(inputAnswer);
        when(httpExchange.getRequestMethod()).thenReturn("POST");
        Assertions.assertTrue(serviceStart.TestRequest(httpExchange));
    }

    @Test
    void testRequestFalseWithException() {
        String id ="2";
        String uri = "http://localhost:9856";
        String message = "Hello";
        final String addressInController;
        ServiceStart serviceStart = new ServiceStart(controller);
        when(httpExchange.getRequestBody()).thenThrow(com.google.gson.JsonSyntaxException.class);
        Assertions.assertFalse(serviceStart.TestRequest(httpExchange));
    }
    @Test
    void testRequestFalseWithGet() {
        String id ="2";
        String uri = "http://localhost:9856";
        String message = "Hello";
        final String addressInController;
        Answer <InputStream> inputAnswer = invocation->{
            Gson gson = new Gson();
            StartJsonProperty startJson = new StartJsonProperty(id, uri, message);
            return new ByteArrayInputStream(gson.toJson(startJson).getBytes(StandardCharsets.UTF_8));
        };
        when(controller.getIClientManager()).thenReturn(clientManager);
        doNothing().when(clientManager).setAddress(uri);
        ServiceStart serviceStart = new ServiceStart(controller);
        when(httpExchange.getRequestBody()).thenAnswer(inputAnswer);
        when(httpExchange.getRequestMethod()).thenReturn("GET");
        Assertions.assertFalse(serviceStart.TestRequest(httpExchange));
    }
}

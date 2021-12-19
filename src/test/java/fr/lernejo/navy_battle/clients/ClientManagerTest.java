package fr.lernejo.navy_battle.clients;

import fr.lernejo.navy_battle.Controller;
import fr.lernejo.navy_battle.IController;
import fr.lernejo.navy_battle.clients.client.*;
import fr.lernejo.navy_battle.services.service.ServiceStart;
import org.assertj.core.api.Assert;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.internal.stubbing.answers.DoesNothing;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.UncheckedIOException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientManagerTest {

    @Spy
    IController controller = mock(Controller.class);


    @Mock
    private ClientFire clientFire;
    @Mock
    private ClientStart clientStart;
    private Map <String, Client> listClient;

    @BeforeEach
    public void initialize(){
        listClient = new HashMap<>();
        listClient.put("ClientStart", clientStart);
        listClient.put("ClientFire", clientFire);
    }


    @Test
    void connectSucceed() {
        try {
            ClientManager clientManager = new ClientManager(controller, listClient, "http://localhost:8000");
            when(clientStart.Connect("http://localhost:5200")).thenReturn(true);
            clientManager.setAddress("http://localhost:5200");
            boolean returnValue = clientManager.connect();
            Assertions.assertTrue(returnValue);
        } catch (UnknownHostException e) {
            Assertions.fail();
        }
    }

    @Test
    void connectFailed() throws UnknownHostException {
            ClientManager clientManager = new ClientManager(controller, listClient, "http://localhost:5200");
            when(clientStart.Connect("http://localhost:5200")).thenThrow(UnknownHostException.class);
            Assertions.assertThrowsExactly(UnknownHostException.class, ()-> clientManager.connect());
    }

    @Test
    void connectNoAddress() {
        try {
            ClientManager clientManager = new ClientManager(controller, listClient);
            Assertions.assertFalse(clientManager.connect());
        } catch (UnknownHostException e) {
            Assertions.fail();
        }
    }

    @Test
    void ReplaceAddress() {
        ClientManager clientManager = new ClientManager(controller, listClient, "http://localhost:5200");
        String NewAddress = "http://localhost:5201";
        clientManager.setAddress(NewAddress);
        Assertions.assertEquals(NewAddress, clientManager.getAddress());
    }
    @Test
    void GetSetAddress() {
        ClientManager clientManager = new ClientManager(controller, listClient);
        String NewAddress = "http://localhost:5201";
        clientManager.setAddress(NewAddress);
        Assertions.assertEquals(NewAddress, clientManager.getAddress());
    }
    @Test
    void GetAddressAbsent() {
        ClientManager clientManager = new ClientManager(controller, listClient);
        Assertions.assertEquals("", clientManager.getAddress());
    }

}

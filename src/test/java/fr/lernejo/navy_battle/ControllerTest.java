package fr.lernejo.navy_battle;

import fr.lernejo.navy_battle.game.Game;
import org.junit.jupiter.api.*;
import org.mockito.Mock;

import java.io.IOException;

class ControllerTest {
    private Controller controller1;
    private Controller controller2;

    private String address = "http://localhost:26856";
    @Mock
    Controller controller3;

    @Test
    void getIClientManager() throws IOException {
        int port1 = 26000;
        int port2 = 26001;
        controller1 = new Controller(port1);
        controller2 = new Controller(port2, address);
        Assertions.assertEquals(address, controller2.getIClientManager().getAddress());
    }

    @Test
    void getGame() throws IOException {
        int port1 = 26002;
        int port2 = 26003;
        controller1 = new Controller(port1);
        controller2 = new Controller(port2, address);
        Assertions.assertInstanceOf(Game.class, controller1.getGame());
        Assertions.assertInstanceOf(Game.class, controller2.getGame());
    }

    @Test
    void getServer() throws IOException {
        int port1 = 26004;
        int port2 = 26005;
        controller1 = new Controller(port1);
        controller2 = new Controller(port2, address);
        Assertions.assertEquals(port1, controller1.getServer().getAddress().getPort());
        Assertions.assertEquals(port2, controller2.getServer().getAddress().getPort());
    }

    @Test
    void startGameWithConnexion() throws IOException {
        int port1 = 26006;
        int port2 = 26007;
        controller1 = new Controller(port1);
        controller2 = new Controller(port2, address);
    }

    @Test
    void startGame() throws IOException {
        int port1 = 26008;
        int port2 = 26009;
        controller1 = new Controller(port1);
        controller2 = new Controller(port2, address);
    }

    @Test
    void startServer() throws IOException {
        int port1 = 26010;
        int port2 = 26011;
        controller1 = new Controller(port1);
        controller2 = new Controller(port2, address);
    }

}

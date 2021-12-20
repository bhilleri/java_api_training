package fr.lernejo.navy_battle.services.service;

import com.sun.net.httpserver.HttpExchange;
import fr.lernejo.navy_battle.IController;
import fr.lernejo.navy_battle.enumeration.Consequence;
import fr.lernejo.navy_battle.game.IGame;
import fr.lernejo.navy_battle.game.point.Point;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ServiceFireTest {
    @Mock
    IController controller = mock(IController.class);
    @Mock
    IGame game = mock(IGame.class);
    @Mock
    HttpExchange httpExchange = mock(HttpExchange.class);
    private ServiceFire serviceFire;
    @BeforeEach
    public void initialize(){
        serviceFire = new ServiceFire(controller);
    }
    @Test
    void handler() throws URISyntaxException, IOException {
        URI uri = new URI("http://localhost:25000/api/game/fire?cell=B10");
        Consequence expectedConsequence = Consequence.sunk;
        when(httpExchange.getRequestURI()).thenReturn(uri);
        when(controller.getGame()).thenReturn(game);
        when(game.receiveShoot(new Point(1,9))).thenReturn(Consequence.sunk);
        when(httpExchange.getRequestMethod()).thenReturn("GET");

        when(controller.getGame()).thenReturn(game);
        when(game.GetIfLost()).thenReturn(false);
        String expectedResult ="{\"consequence\":\"sunk\",\"shipLeft\":true}";
        doNothing().when(httpExchange).sendResponseHeaders(202, expectedResult.length());
        when(httpExchange.getResponseBody()).thenReturn(OutputStream.nullOutputStream());
        serviceFire.handler(httpExchange);
        // En cas de succès, "{\"consequence\":\"sunk\",\"shipLeft\":true}" aura été écris dans le buffer
        // sinon le test ne pourra pas se terminer
    }

    @Test
    void handlerError() throws URISyntaxException, IOException {
        URI uri = new URI("http://localhost:25000/api/game/fire?cell=");
        Consequence expectedConsequence = Consequence.sunk;
        when(httpExchange.getRequestURI()).thenReturn(uri);
        when(controller.getGame()).thenReturn(game);
        when(game.receiveShoot(new Point(1,9))).thenReturn(Consequence.sunk);
        when(httpExchange.getRequestMethod()).thenReturn("GET");

        when(controller.getGame()).thenReturn(game);
        when(game.GetIfLost()).thenReturn(false);
        String expectedResult ="BadRequest";
        doNothing().when(httpExchange).sendResponseHeaders(202, expectedResult.length());
        when(httpExchange.getResponseBody()).thenReturn(OutputStream.nullOutputStream());
        serviceFire.handler(httpExchange);
        // En cas de succès, "{\"consequence\":\"sunk\",\"shipLeft\":true}" aura été écris dans le buffer
        // sinon le test ne pourra pas se terminer
    }

    @Test
    void readRequestGetSunk() throws URISyntaxException {
        URI uri = new URI("http://localhost:25000/api/game/fire?cell=B10");
        Consequence expectedConsequence = Consequence.sunk;
        when(httpExchange.getRequestURI()).thenReturn(uri);
        when(controller.getGame()).thenReturn(game);
        when(game.receiveShoot(new Point(1,9))).thenReturn(Consequence.sunk);
        when(httpExchange.getRequestMethod()).thenReturn("GET");
        Consequence consequence = serviceFire.ReadRequest(httpExchange);
        Assertions.assertEquals(expectedConsequence, consequence);
    }

    @Test
    void readRequestGetHint() throws URISyntaxException {
        URI uri = new URI("http://localhost:25000/api/game/fire?cell=B10");
        Consequence expectedConsequence = Consequence.hint;
        when(httpExchange.getRequestURI()).thenReturn(uri);
        when(controller.getGame()).thenReturn(game);
        when(game.receiveShoot(new Point(1,9))).thenReturn(Consequence.hint);
        when(httpExchange.getRequestMethod()).thenReturn("GET");
        Consequence consequence = serviceFire.ReadRequest(httpExchange);
        Assertions.assertEquals(expectedConsequence, consequence);
    }

    @Test
    void readRequestGetMiss() throws URISyntaxException {
        URI uri = new URI("http://localhost:25000/api/game/fire?cell=B10");
        Consequence expectedConsequence = Consequence.miss;
        when(httpExchange.getRequestURI()).thenReturn(uri);
        when(controller.getGame()).thenReturn(game);
        when(game.receiveShoot(new Point(1,9))).thenReturn(Consequence.miss);
        when(httpExchange.getRequestMethod()).thenReturn("GET");
        Consequence consequence = serviceFire.ReadRequest(httpExchange);
        Assertions.assertEquals(expectedConsequence, consequence);
    }

    @Test
    void readRequestArrayIndexOutOfBoundsException() throws URISyntaxException {
        URI uri = new URI("http://localhost:25000/api/game/fire?cell");
        Consequence expectedConsequence = Consequence.error;
        when(httpExchange.getRequestURI()).thenReturn(uri);
        when(controller.getGame()).thenReturn(game);
        when(game.receiveShoot(new Point(1,9))).thenReturn(Consequence.miss);
        when(httpExchange.getRequestMethod()).thenReturn("GET");
        Consequence consequence = serviceFire.ReadRequest(httpExchange);
        Assertions.assertEquals(expectedConsequence, consequence);
    }

    @Test
    void readRequestNullPointerException() throws URISyntaxException {
        URI uri = new URI("http://localhost:25000/api/game/fire");
        Consequence expectedConsequence = Consequence.error;
        when(httpExchange.getRequestURI()).thenReturn(uri);
        when(controller.getGame()).thenReturn(game);
        when(game.receiveShoot(new Point(1,9))).thenReturn(Consequence.miss);
        when(httpExchange.getRequestMethod()).thenReturn("GET");
        Consequence consequence = serviceFire.ReadRequest(httpExchange);
        Assertions.assertEquals(expectedConsequence, consequence);
    }

    @Test
    void readRequestPost() throws URISyntaxException {
        URI uri = new URI("http://localhost:25000/api/game/fire?cell=B10");
        Consequence expectedConsequence = Consequence.error;
        when(httpExchange.getRequestURI()).thenReturn(uri);
        when(controller.getGame()).thenReturn(game);
        when(game.receiveShoot(new Point(1,9))).thenReturn(Consequence.miss);
        when(httpExchange.getRequestMethod()).thenReturn("POST");
        Consequence consequence = serviceFire.ReadRequest(httpExchange);
        Assertions.assertEquals(expectedConsequence, consequence);
    }


    @Test
    void getBodyTrue() {
        Consequence consequence = Consequence.miss;
        when(controller.getGame()).thenReturn(game);
        when(game.GetIfLost()).thenReturn(false);
        String expectedResult ="{\"consequence\":\"miss\",\"shipLeft\":true}";
        String result = serviceFire.getBody(consequence);
        Assertions.assertEquals(expectedResult, result);
    }
    @Test
    void getBodyError() {
        Consequence consequence = Consequence.error;
        when(controller.getGame()).thenReturn(game);
        when(game.GetIfLost()).thenReturn(false);
        String expectedResult ="BadRequest";
        String result = serviceFire.getBody(consequence);
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void fireResponseJsonPropertyBuilderMiss() {
        Consequence consequence = Consequence.miss;
        when(controller.getGame()).thenReturn(game);
        when(game.GetIfLost()).thenReturn(false);
        String expectedResult ="{\"consequence\":\"miss\",\"shipLeft\":true}";
        String result = serviceFire.FireResponseJsonPropertyBuilder(consequence);
        Assertions.assertEquals(expectedResult, result);
    }
    @Test
    void fireResponseJsonPropertyBuilderSunk() {
        Consequence consequence = Consequence.sunk;
        when(controller.getGame()).thenReturn(game);
        when(game.GetIfLost()).thenReturn(false);
        String expectedResult ="{\"consequence\":\"sunk\",\"shipLeft\":true}";
        String result = serviceFire.FireResponseJsonPropertyBuilder(consequence);
        Assertions.assertEquals(expectedResult, result);
    }
    @Test
    void fireResponseJsonPropertyBuilderMissHint() {
        Consequence consequence = Consequence.hint;
        when(controller.getGame()).thenReturn(game);
        when(game.GetIfLost()).thenReturn(false);
        String expectedResult ="{\"consequence\":\"hint\",\"shipLeft\":true}";
        String result = serviceFire.FireResponseJsonPropertyBuilder(consequence);
        Assertions.assertEquals(expectedResult, result);
    }
    @Test
    void fireResponseJsonPropertyBuilderShipLeftFalse() {
        Consequence consequence = Consequence.sunk;
        when(controller.getGame()).thenReturn(game);
        when(game.GetIfLost()).thenReturn(true);
        String expectedResult ="{\"consequence\":\"sunk\",\"shipLeft\":false}";
        String result = serviceFire.FireResponseJsonPropertyBuilder(consequence);
        Assertions.assertEquals(expectedResult, result);
    }
}

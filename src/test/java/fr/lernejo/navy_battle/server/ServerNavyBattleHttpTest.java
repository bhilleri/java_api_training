package fr.lernejo.navy_battle.server;

import fr.lernejo.navy_battle.Controller;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.IOException;

class ServerNavyBattleHttpTest {
    @Mock
    Controller controller;
    @org.junit.jupiter.api.Test
    public void configureServerTestPort()
    {
        try {
            ServerNavyBattleHttp serverNavybattleHttp = new ServerNavyBattleHttp(20110, controller);
            Assertions.assertEquals(20110, serverNavybattleHttp.getPort());;
        } catch (IOException e) {
            Assertions.fail("impossible de définir ServerNavyBattleHttp ");
        }
    }

    @org.junit.jupiter.api.Test
    public void configureServerMaximumPoolSize()
    {
        try {
            ServerNavyBattleHttp serverNavybattleHttp = new ServerNavyBattleHttp(20111, controller);
            Assertions.assertEquals(1, serverNavybattleHttp.getMaximumPoolSize());
        } catch (IOException e) {
            Assertions.fail("impossible de définir ServerNavyBattleHttp ");
        }
    }
    @Test
    public void TestGetAddress(){
        try {
            int port = 20112;
            ServerNavyBattleHttp serverNavybattleHttp = new ServerNavyBattleHttp(port, controller);
            Assertions.assertEquals(port, serverNavybattleHttp.getAddress().getPort());
        } catch (IOException e) {
            Assertions.fail("impossible de définir ServerNavyBattleHttp ");
        }
    }
}

package fr.lernejo.navy_battle.server;

import org.junit.jupiter.api.Assertions;

import java.io.IOException;

class ServerNavyBattleHttpTest {
    @org.junit.jupiter.api.Test
    public void configureServerTestPort()
    {

        try {
            ServerNavyBattleHttp serverNavybattleHttp = new ServerNavyBattleHttp(20110);
            Assertions.assertEquals(20110, serverNavybattleHttp.getPort());;
        } catch (IOException e) {
            Assertions.fail("impossible de définir ServerNavyBattleHttp ");
        }
    }

    @org.junit.jupiter.api.Test
    public void configureServerMaximumPoolSize()
    {

        try {
            ServerNavyBattleHttp serverNavybattleHttp = new ServerNavyBattleHttp(20111);
            Assertions.assertEquals(1, serverNavybattleHttp.getMaximumPoolSize());
        } catch (IOException e) {
            Assertions.fail("impossible de définir ServerNavyBattleHttp ");
        }
    }
}

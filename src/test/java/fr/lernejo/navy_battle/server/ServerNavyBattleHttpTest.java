package fr.lernejo.navy_battle.server;

import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import static org.junit.jupiter.api.Assertions.*;

class ServerNavyBattleHttpTest {
    @org.junit.jupiter.api.Test
    public void configureServerTestPort()
    {
        ServerNavyBattleHttp serverNavybattleHttp = new ServerNavyBattleHttp();
        try {
            serverNavybattleHttp.configureServer(20110);
            Assertions.assertEquals(20110, serverNavybattleHttp.getPort());;
        } catch (IOException e) {
            Assertions.fail("impossible de définir ServerNavyBattleHttp ");
        }
    }

    @org.junit.jupiter.api.Test
    public void configureServerMaximumPoolSize()
    {
        ServerNavyBattleHttp serverNavybattleHttp = new ServerNavyBattleHttp();
        try {
            serverNavybattleHttp.configureServer(20111);
            Assertions.assertEquals(1, serverNavybattleHttp.getMaximumPoolSize());
        } catch (IOException e) {
            Assertions.fail("impossible de définir ServerNavyBattleHttp ");
        }
    }
}

package fr.lernejo.navy_battle;

import fr.lernejo.navy_battle.server.IServerNavyBattle;
import fr.lernejo.navy_battle.server.ServerNavyBattleHttp;

import java.io.IOException;

/**
 * Classe contenant le Main
 */
public final class Launcher {
    /**
     * Point d'entrée dans le programme
     */
    public static void main(String [] args)
    {
        IServerNavyBattle server= new ServerNavyBattleHttp();
        try {
            server.configureServer(22222);
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.startServer();
    }
}

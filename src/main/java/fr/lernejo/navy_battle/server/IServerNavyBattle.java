package fr.lernejo.navy_battle.server;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;

/**
 * Interface du serveur permettant la communication avec l'autre joueur
 */
public interface IServerNavyBattle  {
    /**
     * Initialise la configuration du service
     * @param port numéro du port
     * @throws IOException
     */
    public void configureServer(int port) throws IOException;

    /**
     * Démarre le serveur
     */
    public void startServer();
}

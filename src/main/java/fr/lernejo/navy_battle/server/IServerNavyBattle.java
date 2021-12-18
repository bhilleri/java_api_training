package fr.lernejo.navy_battle.server;

import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;

/**
 * Interface du serveur permettant la communication avec l'autre joueur
 */
public interface IServerNavyBattle  {

    /**
     * Démarre le serveur
     */
    public void startServer();

    /**
     *
     * @return L'adresse du serveur
     */
    public InetSocketAddress getAddress();
}

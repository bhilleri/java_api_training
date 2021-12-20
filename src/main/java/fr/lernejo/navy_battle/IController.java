package fr.lernejo.navy_battle;

import fr.lernejo.navy_battle.game.IGame;
import fr.lernejo.navy_battle.clients.IClientManager;
import fr.lernejo.navy_battle.server.IServerNavyBattle;

import java.net.UnknownHostException;

/**
 * Permet aux différents composants de l'application de pouvoir communiquer entre eux
 */
public interface IController {
    /**
     *
     * @return Accède à la classe qui possède tous les clients
     */
    public IClientManager getIClientManager();

    /**
     *
     * @return Accède à la classe qui gère le jeu
     */
    public IGame getGame();

    /**
     *
     * @return Accède au serveur
     */
    public IServerNavyBattle getServer();

    /**
     * Lance la partie pour le joueur connaissant l'adresse de l'autre
     * @throws UnknownHostException
     * @throws InterruptedException
     */
    public void StartGameWithConnexion() throws UnknownHostException, InterruptedException;

    /**
     * Lance la partie pour le joueur ne connaissant pas l'adresse de l'autre
     * @throws InterruptedException
     * @throws UnknownHostException
     */
    public void StartGameWithoutConnexion() throws InterruptedException, UnknownHostException;

    /**
     * Lance la partie (appelle une méthode de la classe Game)
     * @throws UnknownHostException
     * @throws InterruptedException
     */
    public void startGame() throws UnknownHostException, InterruptedException;

    /**
     * Démarre le serveur
     */
    public void StartServer();
}

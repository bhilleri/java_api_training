package fr.lernejo.navy_battle.Init;

/**
 * permet de lancer la partie pour le joueur connaissant l'IP de l'autre joueur
 */
public interface IInitConnectPlayer {
    /**
     * Lance la partie
     * @param port
     * @param uri
     * @return
     */
    public boolean launch(int port, String uri);
}

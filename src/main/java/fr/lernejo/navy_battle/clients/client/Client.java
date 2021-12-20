package fr.lernejo.navy_battle.clients.client;

import fr.lernejo.navy_battle.IController;

/**
 * Le client se connecte au serveur de l'autre instance pour faire des requêtes
 */
public class Client{
    protected final IController controller;
    public  Client(IController controller){
        this.controller = controller;
    }
}

package fr.lernejo.navy_battle.clients.client;

import fr.lernejo.navy_battle.IController;

public class Client{
    protected final IController controller;
    public  Client(IController controller){
        this.controller = controller;
    }
}

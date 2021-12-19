package fr.lernejo.navy_battle.clients;

import fr.lernejo.navy_battle.clients.client.ClientFire;
import fr.lernejo.navy_battle.clients.client.ClientStart;
import fr.lernejo.navy_battle.clients.client.IClientFire;
import fr.lernejo.navy_battle.clients.client.IClientStart;
import fr.lernejo.navy_battle.enumeration.Consequence;
import fr.lernejo.navy_battle.game.point.IPoint;

import java.net.UnknownHostException;
import java.util.List;

public interface IClientManager{
    public Consequence Fire(IPoint cell) throws UnknownHostException;
    public boolean connect() throws UnknownHostException;
    public void setAddress(String address);
    public String getAddress();
}

package fr.lernejo.navy_battle.clients.client;

import fr.lernejo.navy_battle.enumeration.Consequence;
import fr.lernejo.navy_battle.game.point.IPoint;

import java.net.UnknownHostException;

public interface IClientFire {
    public Consequence Connect(String address, IPoint cell) throws UnknownHostException;
}

package fr.lernejo.navy_battle.clients.client;

import java.net.URL;
import java.net.UnknownHostException;

public interface IClientStart {
    public boolean Connect(String address) throws UnknownHostException;
}

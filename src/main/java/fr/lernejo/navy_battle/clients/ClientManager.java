package fr.lernejo.navy_battle.clients;

import fr.lernejo.navy_battle.IController;
import fr.lernejo.navy_battle.clients.client.*;
import fr.lernejo.navy_battle.enumeration.Consequence;
import fr.lernejo.navy_battle.game.point.IPoint;
import fr.lernejo.navy_battle.game.point.Point;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientManager implements IClientManager {
    private final IController controller;
    private final IClientStart clientStart;
    private final IClientFire clientFire;
    private final List<String> address;
    private final int A;
    public ClientManager(IController controller, Map <String, Client> clientMap, String address)
    {
        this.controller = controller;
        this.clientFire = (IClientFire) clientMap.get("ClientFire");
        this.clientStart = (IClientStart) clientMap.get("ClientStart");
        this.address = new ArrayList<>();
        this.address.add(address);
        A =2;
    }
    public ClientManager(IController controller, Map <String, Client> clientMap)
    {
        this.controller = controller;
        this.clientFire = (IClientFire) clientMap.get("ClientFire");
        this.clientStart = (IClientStart) clientMap.get("ClientStart");
        this.address = new ArrayList<>();
        A = 2;
    }
    @Override
    public Consequence Fire(IPoint cell) throws UnknownHostException {
        return this.clientFire.Connect(this.address.get(0), cell);
    }

    @Override
    public boolean connect() throws UnknownHostException {
        if(this.address.size() >=1)
            return this.clientStart.Connect(this.address.get(0));
        return false;
    }

    @Override
    public void setAddress(String address) {
        System.out.println("uri reçu :" + address);
        if(this.address.size() == 0)
        {
            this.address.add(address);
        }
        else
        {
            this.address.set(0, address);
        }
    }

    @Override
    public String getAddress() {
        if(this.address.size()>=1)
            return this.address.get(0);
        return "";
    }
}

package fr.lernejo.navy_battle;

import fr.lernejo.navy_battle.Game.IGame;
import fr.lernejo.navy_battle.clients.IClientManager;
import fr.lernejo.navy_battle.server.IServerNavyBattle;

import java.net.URL;
import java.net.UnknownHostException;

public interface IController {
    public IClientManager getIClientManager();
    public IGame getGame();
    public IServerNavyBattle getServer();
    public void StartGameWithConnexion() throws UnknownHostException;
    public void startGame();
    public void StartServer();
}

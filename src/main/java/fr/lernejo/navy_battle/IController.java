package fr.lernejo.navy_battle;

import fr.lernejo.navy_battle.game.IGame;
import fr.lernejo.navy_battle.clients.IClientManager;
import fr.lernejo.navy_battle.server.IServerNavyBattle;

import java.net.UnknownHostException;

public interface IController {
    public IClientManager getIClientManager();
    public IGame getGame();
    public IServerNavyBattle getServer();
    public void StartGameWithConnexion() throws UnknownHostException;
    public void StartGameWithoutConnexion() throws InterruptedException, UnknownHostException;
    public void startGame() throws UnknownHostException;
    public void StartServer();
}

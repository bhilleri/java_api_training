package fr.lernejo.navy_battle;
import fr.lernejo.navy_battle.game.Game;
import fr.lernejo.navy_battle.clients.ClientManager;
import fr.lernejo.navy_battle.clients.IClientManager;
import fr.lernejo.navy_battle.game.IGame;
import fr.lernejo.navy_battle.clients.ListClients;
import fr.lernejo.navy_battle.server.IServerNavyBattle;
import fr.lernejo.navy_battle.server.ServerNavyBattleHttp;
import java.io.IOException;
import java.net.UnknownHostException;

public class Controller implements  IController {
    private final IServerNavyBattle server;
    private final IGame game;
    private final IClientManager clientManager;

    public Controller(int myPort) throws IOException {
        this.server = new ServerNavyBattleHttp(myPort, this);
        this.game = new Game(this);
        this.clientManager = new ClientManager(this, (new ListClients(this).getClientList()));
    }

    public Controller(int myPort, String address) throws IOException {
        this.server = new ServerNavyBattleHttp(myPort, this);
        this.game = new Game(this);
        this.clientManager = new ClientManager(this, (new ListClients(this).getClientList()), address);
    }

    @Override
    public IClientManager getIClientManager() {
        return clientManager;
    }

    @Override
    public IGame getGame() {
        return game;
    }

    @Override
    public IServerNavyBattle getServer() {
        return server;
    }

    @Override
    public void StartGameWithConnexion() throws UnknownHostException {
        startGame();
        this.getIClientManager().connect();

    }

    @Override
    public void startGame() {
        game.Initialize();
    }

    @Override
    public void StartServer() {
        this.server.startServer();
    }
}

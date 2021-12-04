package fr.lernejo.navy_battle.server;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import fr.lernejo.navy_battle.services.IService;
import fr.lernejo.navy_battle.services.ListServices;

/**
 * Serveur Http de l'application permettant la communication avec l'autre joueur
 */
public class ServerNavyBattleHttp implements IServerNavyBattle {
    private HttpServer server;

    @Override
    public void configureServer(int port) throws IOException {
        InetSocketAddress inetSocketAddress = new InetSocketAddress(port);
            server = HttpServer.
                create(inetSocketAddress, 0);

            server.setExecutor(Executors.newFixedThreadPool(1));
            ListServices serviceList = new ListServices();
            serviceList.getAllService().forEach((String url, IService service) ->{
                server.createContext(url, service::handler);
            });
    }

    public int getPort() {return this.server.getAddress().getPort();}
    public int getMaximumPoolSize(){return ((ThreadPoolExecutor) this.server.getExecutor()).getMaximumPoolSize();}

    @Override
    public void startServer() {
        server.start();
    }
}

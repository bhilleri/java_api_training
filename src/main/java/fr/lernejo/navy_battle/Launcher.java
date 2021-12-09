package fr.lernejo.navy_battle;

import com.google.gson.Gson;
import com.google.gson.annotations.JsonAdapter;
import fr.lernejo.navy_battle.server.IServerNavyBattle;
import fr.lernejo.navy_battle.server.ServerNavyBattleHttp;
import fr.lernejo.navy_battle.services.json_properties.StartJsonProperty;
import fr.lernejo.navy_battle.services.json_properties.StartJsonSchema;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Classe contenant le Main
 */
public class Launcher {
    /**
     * Point d'entrée dans le programme
     */
    public static void main(String [] args)
    {
        final int myPort = Integer.parseInt(args[0]);
        try {
            final IServerNavyBattle server= new ServerNavyBattleHttp(myPort);
            server.startServer();
            if( args.length >= 2)
            {
                final String address= args[1];
                final Gson gson = new Gson();
                final String body = gson.toJson(new StartJsonProperty(
                    "1",
                    "http://" + InetAddress.getLocalHost().getHostAddress()+ ":" + myPort,
                    "hello"
                ));
                System.out.println("Send : " + body);
                final HttpRequest requetePost = HttpRequest.newBuilder()
                    .uri(URI.create(address+ "/api/game/start"))
                    .setHeader("Accept", "application/json")
                    .setHeader("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();
                final HttpClient client = HttpClient.newHttpClient();
                client.sendAsync(requetePost, HttpResponse.BodyHandlers.ofString())
                    .thenAccept((b) ->{
                        System.out.println("Receive : " + b.body());
                    })
                    .join();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package fr.lernejo.navy_battle.clients.client;

import com.google.gson.Gson;
import fr.lernejo.navy_battle.IController;
import fr.lernejo.navy_battle.enumeration.Consequence;
import fr.lernejo.navy_battle.game.point.IPoint;
import fr.lernejo.navy_battle.services.json_properties.FirerResponseJsonProperty;
import fr.lernejo.navy_battle.services.json_properties.StartJsonProperty;

import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClientFire extends Client implements IClientFire{
    public ClientFire(IController controller){
        super(controller);

    }

    public Consequence Connect(String address, IPoint cell) throws UnknownHostException
    {
        System.out.println("Fire on " + cell.toString());
        final int port = this.controller.getServer().getAddress().getPort();
        final HttpRequest requetePost = RequestBuilder(address, cell);
        final HttpClient client = HttpClient.newHttpClient();

        client.sendAsync(requetePost, HttpResponse.BodyHandlers.ofString())
            .thenAccept((b) ->{
                System.out.println("Receive : " + b.body());
            })
            .join();
        return Consequence.miss;
    }

    public HttpRequest RequestBuilder(String address, IPoint cell)
    {
        final HttpRequest requetePost = HttpRequest.newBuilder()
            .uri(URI.create(address+ "/api/game/fire?" + "cell=" + cell.toString()))
            .build();
        return requetePost;
    }

}

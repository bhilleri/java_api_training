package fr.lernejo.navy_battle.clients.client;


import fr.lernejo.navy_battle.IController;
import fr.lernejo.navy_battle.enumeration.Consequence;
import fr.lernejo.navy_battle.game.point.IPoint;
import fr.lernejo.navy_battle.services.json_properties.FirerResponseJsonProperty;
import fr.lernejo.navy_battle.services.tools.DeserializeFireResponseJsonProperty;
import java.net.URI;
import java.net.UnknownHostException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class ClientFire extends Client implements IClientFire{
    public ClientFire(IController controller){
        super(controller);

    }

    public Consequence Connect(String address, IPoint cell) throws UnknownHostException
    {
        List<Consequence> consequenceList =new ArrayList<>();
        final int port = this.controller.getServer().getAddress().getPort();
        final HttpRequest requetePost = RequestBuilder(address, cell);
        final HttpClient client = HttpClient.newHttpClient();
        client.sendAsync(requetePost, HttpResponse.BodyHandlers.ofString())
            .thenAccept((b) ->{
                final DeserializeFireResponseJsonProperty deserializeFireResponseJsonProperty = new DeserializeFireResponseJsonProperty();
                FirerResponseJsonProperty responseJsonProperty = deserializeFireResponseJsonProperty.deserializationFromString(b.body());
                consequenceList.add(responseJsonProperty.consequence);
                if(responseJsonProperty.shipLeft == false)
                    this.controller.getGame().SetVictory();
            })
            .join();
        return consequenceList.get(0);
    }

    public HttpRequest RequestBuilder(String address, IPoint cell)
    {
        final HttpRequest requetePost = HttpRequest.newBuilder()
            .uri(URI.create(address+ "/api/game/fire?" + "cell=" + cell.toString()))
            .setHeader("Accept", "application/json")
            .setHeader("Content-Type", "application/json")
            .build();
        return requetePost;
    }

}

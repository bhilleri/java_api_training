package fr.lernejo.navy_battle.clients.client;

import com.google.gson.Gson;
import fr.lernejo.navy_battle.IController;
import fr.lernejo.navy_battle.services.json_properties.StartJsonProperty;

import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClientStart extends Client implements IClientStart{
    public ClientStart(IController controller){
        super(controller);
    }

    @Override
    public boolean Connect(String address ) throws UnknownHostException
    {
        final int port = this.controller.getServer().getAddress().getPort();
        final String body = BodyBuilder(port);
        //System.out.println("Send : " + body);
        final HttpRequest requetePost = RequestBuilder(address, body);
        final HttpClient client = HttpClient.newHttpClient();
        client.sendAsync(requetePost, HttpResponse.BodyHandlers.ofString())
            .thenAccept((b) ->{
                //System.out.println("Receive : " + b.body());
            })
            .join();
        return true;
    }

    public HttpRequest RequestBuilder(String address, String body)
    {
        final HttpRequest requetePost = HttpRequest.newBuilder()
            .uri(URI.create(address+ "/api/game/start"))
            .setHeader("Accept", "application/json")
            .setHeader("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(body))
            .build();
        return requetePost;
    }
    public String BodyBuilder(int port) throws UnknownHostException {
        final Gson gson = new Gson();
        final String body = gson.toJson(new StartJsonProperty(
            "1",
            "http://" + InetAddress.getLocalHost().getHostAddress()+ ":" + port ,
            "hello"
        ));
        return body;
    }
}

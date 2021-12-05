package fr.lernejo.navy_battle.services.service;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import fr.lernejo.navy_battle.services.IService;
import fr.lernejo.navy_battle.services.json_properties.StartJsonProperty;
import fr.lernejo.navy_battle.services.json_properties.StartJsonSchema;
import fr.lernejo.navy_battle.services.tools.DeserializationStartJsonSchema;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

/**
 * Service permettant aux deux joueurs de se connaître
 */
public class ServiceStart implements IService {
    private static InetSocketAddress inetSocketAddress;
    public String BadRequestError = "error 400 : Bad request";
    @Override
    public void handler(final HttpExchange exchange) throws IOException {
        final String body;
        if (TestRequest(exchange))
        {
            body = getBody(String.valueOf(exchange.getLocalAddress().getPort()));
            exchange.sendResponseHeaders(200, body.length());
        }
        else
        {
            body = BadRequestError;
            exchange.sendResponseHeaders(400, body.length());
        }
        try (OutputStream os = exchange.getResponseBody()) { // (1)
                System.out.println("send : " + body);
                os.write(body.getBytes());
        }
    }

    /**
     * Rédige le contenu de la réponse
     * @param port Numéro du port du serveur
     * @return Text Contenant la réponse qui doit être renvoyée.
     * @throws UnknownHostException
     */
    public String getBody(String port) throws UnknownHostException {
        final Gson gson = new Gson();
        final StartJsonSchema startJsonSchema = new StartJsonSchema( new StartJsonProperty(
            "2",
            "http://" + InetAddress.getLocalHost().getHostAddress() + ":" + port,
            "Bonjour"));
        return gson.toJson(startJsonSchema);
    }

    /**
     * Test la validité de la requête envoyée au serveur
     * @param exchange
     * @return Vrai si la requête est conforme, sinon renvoie faux
     */
    public boolean TestRequest(final HttpExchange exchange){
        try{
            DeserializationStartJsonSchema deserialization= new DeserializationStartJsonSchema();
            StartJsonSchema request = deserialization.deserialization(exchange.getRequestBody());
            return request.isCorrect() && exchange.getRequestMethod().equals("POST");
        }
        catch (com.google.gson.JsonSyntaxException | IOException| NullPointerException e)
        {
            return false;
        }
    }
}

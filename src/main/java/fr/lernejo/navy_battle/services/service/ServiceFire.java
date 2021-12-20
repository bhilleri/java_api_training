package fr.lernejo.navy_battle.services.service;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import fr.lernejo.navy_battle.enumeration.Consequence;
import fr.lernejo.navy_battle.IController;
import fr.lernejo.navy_battle.game.point.Point;
import fr.lernejo.navy_battle.services.IService;
import fr.lernejo.navy_battle.services.json_properties.FirerResponseJsonProperty;

import java.io.IOException;
import java.io.OutputStream;

public class ServiceFire implements IService {
    final IController controller;
    public ServiceFire(IController controller){this.controller = controller;}
    @Override
    public void handler(HttpExchange exchange) throws IOException {
        final Consequence consequence = ReadRequest(exchange);
        final String body = getBody(consequence);
        if(!consequence.equals(Consequence.error))
        {
            exchange.sendResponseHeaders(202, body.length());
        }
        else
        {
            exchange.sendResponseHeaders(400, body.length());
        }
        try (OutputStream os = exchange.getResponseBody()) { // (1)
            //System.out.println("send : " + body);
            os.write(body.getBytes());
        }
        controller.getGame().SetTrueReadyTOShoot();
    }
    public Consequence ReadRequest(HttpExchange exchange){
        try {
            String cell = exchange.getRequestURI().getQuery().split("=")[1];
            int column = (int) (cell.charAt(0)) - 65;
            int row = Integer.valueOf(cell.substring(1)) - 1;
            if (exchange.getRequestMethod().equals("GET"))
                if (column >= 0 && column <= 10)
                    if (row >= 0 && row <= 10)
                        return controller.getGame().receiveShoot(new Point(column, row));
            return Consequence.error;
        }catch (ArrayIndexOutOfBoundsException | NullPointerException e){
            return Consequence.error;
        }
    }
    public String getBody(Consequence consequence)
    {
        if(!consequence.equals(Consequence.error))
        {
            return FireResponseJsonPropertyBuilder(consequence);
        }
        else
        {
            return "BadRequest";
        }
    }

    public String FireResponseJsonPropertyBuilder(Consequence consequence){
        final Gson gson = new Gson();
        final FirerResponseJsonProperty firerResponseJsonProperty;
        firerResponseJsonProperty = new FirerResponseJsonProperty(consequence, !this.controller.getGame().GetIfLost());
        if(this.controller.getGame().GetIfLost()) {
            controller.getGame().SetDefeat();
        }
        return gson.toJson(firerResponseJsonProperty);
    }
}

package fr.lernejo.navy_battle.services.tools;

import com.google.gson.Gson;
import fr.lernejo.navy_battle.services.json_properties.FirerResponseJsonProperty;
import fr.lernejo.navy_battle.services.json_properties.StartJsonProperty;

import java.io.IOException;
import java.io.InputStream;

public class DeserializeFireResponseJsonProperty {
    public FirerResponseJsonProperty deserialization(InputStream requestBody) throws IOException {
        ReadRequest readRequest = new ReadRequest();
        Gson gson = new Gson();
        return gson.fromJson(readRequest.read(requestBody), FirerResponseJsonProperty.class);
    }
}

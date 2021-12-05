package fr.lernejo.navy_battle.services.tools;

import com.google.gson.Gson;
import fr.lernejo.navy_battle.services.json_properties.StartJsonSchema;
import java.io.IOException;
import java.io.InputStream;

/**
 * Classe permettant de traduire une requête envoyé au serveur en une instance de StartJsonSchema
 */
public class DeserializationStartJsonSchema {
    /**
     * Traduit un flux json en une instance de StartJsonSchema
     * @param requestBody Le flux
     * @return L'instance de StartJsonSchema générée
     * @throws IOException, Exception soulevée en cas d'érreur dans la lecture du flux
     */
    public StartJsonSchema deserialization(InputStream requestBody) throws IOException {
        ReadRequest readRequest = new ReadRequest();
        Gson gson = new Gson();
        return gson.fromJson(readRequest.read(requestBody), StartJsonSchema.class);
    }
}

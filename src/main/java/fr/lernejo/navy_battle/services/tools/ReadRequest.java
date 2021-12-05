package fr.lernejo.navy_battle.services.tools;

import java.io.IOException;
import java.io.InputStream;

/**
 * Classe permettant de convertir un flux en String
 */
public class ReadRequest {
    /**
     * convertit un flux en json en String
     * @param requestBody le flux à lire
     * @return Le string
     * @throws IOException, exception levée en cas d'érreur dans la lecture du flux
     */
    public String read(InputStream requestBody) throws IOException {
        final StringBuilder  body = new StringBuilder();
        for (final byte b : requestBody.readAllBytes()) {
            body.append((char) b);
        }
        System.out.println("receive : " + body.toString());
        return body.toString();
    }
}

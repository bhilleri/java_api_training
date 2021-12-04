package fr.lernejo.navy_battle.services;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

/**
 * Interface d'un web-service. Permet de pouvoir définir plusieurs web-service en suivant le même modèle
 */
public interface IService {
    /**
     * Retourne le handler du service permettant la réalisation d'une action suite à la reception d'une requête
     * @param exchange Contient la requête du client et permet de lui répondre
     * @throws IOException
     */
    public void handler(HttpExchange exchange) throws IOException;
}

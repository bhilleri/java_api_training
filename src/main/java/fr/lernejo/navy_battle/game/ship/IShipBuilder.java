package fr.lernejo.navy_battle.game.ship;

import java.util.List;

/**
 * Stocke la liste des navires à générer au début de la partie
 */
public interface IShipBuilder {
    /**
     *
     * @return La liste des navires à initialisés au début de la partie
     */
    public List<IShip> GetBoat();
}

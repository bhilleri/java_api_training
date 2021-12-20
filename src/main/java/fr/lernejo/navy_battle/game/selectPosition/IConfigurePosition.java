package fr.lernejo.navy_battle.game.selectPosition;

import fr.lernejo.navy_battle.game.ship.IShip;

import java.util.List;

/**
 * Configure la position des navires
 */
public interface IConfigurePosition {
    /**
     * Détermine la position des navires en fonction de ce que luis propose l'utilisateur
     * La méthode peut demander à l'utilisateur de ressaisir la position d'un navire
     * @param boatList la liste des navires à placés
     */
    public void PositionAllBoat(List<IShip> boatList);
}

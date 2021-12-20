package fr.lernejo.navy_battle.game.board.shoot;

import fr.lernejo.navy_battle.enumeration.Consequence;
import fr.lernejo.navy_battle.game.point.IPoint;

import java.util.List;

/**
 * Gère la répercussion du feu ennemie
 */
public interface IShootFromEnemy {
    /**
     * Répercute l'éffet du tir adverse sur les navires du joueur
     * @param targetedPoint point ciblé par l'adverse
     * @return effet lié au tir
     */
    public Consequence shoot(IPoint targetedPoint);

    /**
     * Vérifie si un navire a été détruit
     * @return La liste des navires détruits depuis le dernier appel de la méthode
     * Normallement au plus un navire est renvoyé
     */
    public List<String> UpdateDestroyedShip();
}

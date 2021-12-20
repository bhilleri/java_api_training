package fr.lernejo.navy_battle.game.board;

import fr.lernejo.navy_battle.enumeration.Consequence;
import fr.lernejo.navy_battle.game.point.IPoint;

/**
 * Gère le plateau de jeu du joueur
 */
public interface IBoard {
    /**
     * initialise la position des Navires
     */
    public void InitializeShip();

    /**
     * Répercute un tir adverse sur les navires du joueur (transmet la demande à IShootFromEnemy)
     * @param point point ciblé par l'adverse
     * @return Conséquence du tir
     */
    public Consequence EnemyShoot(IPoint point);

    /**
     * Vérifie que le joueur a encore des navires en état
     * @return true si le joueur a perdu et false si le joueur a encore des navires
     */
    public boolean GetIfLost();
}

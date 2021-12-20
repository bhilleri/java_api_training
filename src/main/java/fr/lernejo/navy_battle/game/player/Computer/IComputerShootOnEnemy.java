package fr.lernejo.navy_battle.game.player.Computer;

import fr.lernejo.navy_battle.game.point.IPoint;

/**
 * Interface permettant à l'utilisateur computeur de choisir où tirer
 */
public interface IComputerShootOnEnemy {
    /**
     * Séléctionne le point où tirer
     * @return le point choisi pour le tir
     */
    public IPoint shoot();
}

package fr.lernejo.navy_battle.game.player.Computer;

import fr.lernejo.navy_battle.game.point.IPoint;

import java.util.List;

/**
 * Interface de player Computer luis permettant de saisir la position des navires
 */
public interface IComputerSetBoatPosition {
    /**
     * Détermine la position du navire
     * @param size taille du navire à placer
     * @return position du navire
     */
    public List<IPoint> PositionABoat(int size);
}

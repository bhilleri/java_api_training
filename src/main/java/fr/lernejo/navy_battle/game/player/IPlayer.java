package fr.lernejo.navy_battle.game.player;

import fr.lernejo.navy_battle.enumeration.Consequence;
import fr.lernejo.navy_battle.game.point.IPoint;

import java.util.List;

/**
 * Interface décrivant les intéractions possibles entre le joueur et l'application
 */
public interface IPlayer {
    /**
     * Demande à l'utilisateur de placer un navire
     * @param size taille du navire à placer
     * @return position du navire séléctionnée par l'utilisateur
     */
    public List<IPoint> PositionABoat(int size);

    /**
     * Demande à l'utilisateur de séléctionner le point où il souhaite tirer
     * @return Le point où l'utilisateur souhaite tirer
     */
    public IPoint Shoot();

    /**
     * Informe l'utilisateur du résultat d'un tir adverse
     * @param point le point ciblé par l'adverse
     * @param consequence les conséquances du tir adverse
     * @return le message affiché à l'utilisateur (test unitaire)
     */
    public String InformEnemySHoot(IPoint point, Consequence consequence);

    /**
     * Informe l'utilisateur de la perte d'un navire
     * @param shipName
     * @return message affiché à l'utilisateur (test unitaire)
     */
    public List<String> InformShipLost(List<String> shipName);

    /**
     * Informe le joueur des conséquances de l'un de ces tirs
     * @param point point ciblé par le joueur
     * @param consequence conséquance de son tir
     * @return
     */
    public String InformConsequenceOfShoot(IPoint point, Consequence consequence);

    /**
     * Informe l'utilisateur qu'il a gagné
     * @return
     */
    public String InformVictory();

    /**
     * Informe l'utilisateur qu'il a perdu
     * @return
     */
    public String InformDefeat();

    /**
     * Informe l'utilisateur que la position de l'un de ces navires a été validée
     * @param name nom du navire
     * @param pointList position du navire
     * @return
     */
    public String InformPlacement(String name, List<IPoint> pointList);
}

package fr.lernejo.navy_battle.game.ship;

import fr.lernejo.navy_battle.enumeration.Consequence;
import fr.lernejo.navy_battle.game.point.IPoint;

import java.util.List;

/**
 * Interface d'un navire
 */
public interface IShip {
    /**
     * Détermine les coordonnées des différentes parties du navire
     * @param pointList
     * @return true si la position est accepté et false si la position du navire est déjà affectée
     */
    public boolean SetPosition(List<IPoint> pointList);

    /**
     * Reçoit un tir adverse
     * @param targetedPoint position du tir
     * @return la conséquance du tir
     */
    public Consequence hit(IPoint targetedPoint);
    public boolean IsPresentAtAPoint(IPoint testedPoint);
    public String getName();
    public List<IPoint> GetPosition();
    public int GetSize();
    public boolean IsDestroy();
}

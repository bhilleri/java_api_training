package fr.lernejo.navy_battle.game.ship;

import fr.lernejo.navy_battle.enumeration.Consequence;
import fr.lernejo.navy_battle.game.point.IPoint;

import java.util.List;

public interface IShip {
    public boolean SetPosition(List<IPoint> pointList);
    public Consequence hit(IPoint targetedPoint);
    public boolean IsPresentAtAPoint(IPoint testedPoint);
    public String getName();
    public List<IPoint> GetPosition();
    public int GetSize();
    public boolean IsDestroy();
}

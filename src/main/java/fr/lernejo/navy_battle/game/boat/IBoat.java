package fr.lernejo.navy_battle.game.boat;

import fr.lernejo.navy_battle.enumeration.Consequence;
import fr.lernejo.navy_battle.game.point.IPoint;
import fr.lernejo.navy_battle.game.point.Point;

import java.util.List;

public interface IBoat {
    public boolean SetPosition(List<IPoint> pointList);
    public Consequence hit(IPoint targetedPoint);
    public boolean IsPresentAtAPoint(IPoint testedPoint);
    public String getName();
    public List<IPoint> GetPosition();
    public int GetSize();
}

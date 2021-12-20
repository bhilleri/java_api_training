package fr.lernejo.navy_battle.game.player;

import fr.lernejo.navy_battle.enumeration.Consequence;
import fr.lernejo.navy_battle.game.point.IPoint;

import java.util.List;

public interface IPlayer {
    public List<IPoint> PositionABoat(int size);
    public IPoint Shoot();
    public String InformEnemySHoot(IPoint point, Consequence consequence);
    public List<String> InformShipLost(List<String> shipName);
    public String InformConsequenceOfShoot(IPoint point, Consequence consequence);
    public String InformVictory();
    public String InformDefeat();
    public String InformPlacement(String name, List<IPoint> pointList);
}

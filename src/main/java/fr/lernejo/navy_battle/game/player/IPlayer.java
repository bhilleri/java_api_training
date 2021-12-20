package fr.lernejo.navy_battle.game.player;

import fr.lernejo.navy_battle.enumeration.Consequence;
import fr.lernejo.navy_battle.game.point.IPoint;

import java.util.List;

public interface IPlayer {
    public List<IPoint> PositionABoat(int size);
    public IPoint Shoot();
    public void InformEnemySHoot(IPoint point, Consequence consequence);
    public void InformShipLost(List<String> shipName);
    public void InformConsequenceOfShoot(IPoint point, Consequence consequence);
    public String InformVictory();
    public String InformDefeat();
}

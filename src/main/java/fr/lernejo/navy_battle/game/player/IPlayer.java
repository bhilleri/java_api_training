package fr.lernejo.navy_battle.game.player;

import fr.lernejo.navy_battle.game.point.IPoint;

import java.util.List;

public interface IPlayer {
    public List<IPoint> PositionABoat(int size);
    public IPoint Fire();
}
